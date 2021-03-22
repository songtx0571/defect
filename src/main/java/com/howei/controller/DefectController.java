package com.howei.controller;

import com.alibaba.fastjson.JSON;
import com.howei.pojo.Defect;
import com.howei.pojo.Users;
import com.howei.service.DefectService;
import com.howei.service.EmployeeService;
import com.howei.service.EquipmentService;
import com.howei.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static com.howei.util.Image.ImageToBase64ByLocal;

/**
 * 缺陷
 */
@RestController
@RequestMapping("defect")
public class DefectController {

    @Autowired
    private DefectService defectService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    EmployeeService employeeService;

    public Users getPrincipal(){
        Subject subject=SecurityUtils.getSubject();
        Users users=(Users) subject.getPrincipal();
        return users;
    }

    /**
     * 获取当前登录人信息
     * @return
     */
    @RequestMapping("/getLoginUserInfo")
    public Map getLoginUserInfo(){
        Subject subject=SecurityUtils.getSubject();
        Users users=(Users) subject.getPrincipal();
        Map<String,Object> map=new HashMap<>();
        if(users!=null){
            map.put("id",users.getEmployeeId());
            map.put("userName",users.getUserName());
            map.put("userNumber",users.getUserNumber());
            map.put("departmentId",users.getDepartmentId());
        }
        return map;
    }

    /**
     * 跳转缺陷单页面
     * @return
     */
    @RequestMapping("/toDefect")
    public ModelAndView toDefect(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("defect");
        return modelAndView;
    }

    /**
     * 获取用户Map
     * @return
     */
    public Map<Integer,String> getUsersMap(){
        Map<Integer,String> map=employeeService.getUsersMap();
        return map;
    }

    /**
     * 查询缺陷单列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getDefectList",method = RequestMethod.GET)
    public Result getDefectList(HttpServletRequest request){
        String type=request.getParameter("type");//缺陷状态
        String sysId=request.getParameter("sysId");//系统
        String equipmentId=request.getParameter("equipmentId");//设备
        String page=request.getParameter("page");
        String limit=request.getParameter("limit");
        int rows=Page.getOffSet(page,limit);
        Users users=this.getPrincipal();
        Map empMap=getUsersMap();//加载用户Map
        Map map=new HashMap();
        if(type!=null&&!"".equals(type)){
            if(!type.equals(0)){//全部缺陷: 忽略状态
                map.put("type",type);
            }
        }
        if(sysId!=null&&!"".equals(sysId)){
            map.put("sysId",sysId);
        }
        if(equipmentId!=null&&!"".equals(equipmentId)){
            map.put("equipmentId",equipmentId);
        }
        if(users!=null){
            map.put("departmentId",users.getDepartmentId());
        }
        List<Defect> total=defectService.getDefectList(map);
        int count=0;
        count=total!=null ? total.size():0;
        map.put("pageSize",limit);
        map.put("page",rows);
        List<Defect> list=defectService.getDefectList(map);
        for (Defect defect:list){
            String[] strs=(defect.getEmpIds()!=null&&(!defect.getEmpIds().equals("")))? defect.getEmpIds().split(","):null;
            if(strs!=null){
                String empIdsName="";
                for (String str:strs){
                    empIdsName+=empMap.get(Integer.parseInt(str))+",";
                }
                empIdsName=empIdsName.equals("")||empIdsName==null ? "":empIdsName.substring(0,empIdsName.length()-1);
                defect.setEmpIdsName(empIdsName);
            }
            if(defect.getaPlc()!=null){
                defect.setaPlc64(ImageToBase64ByLocal("/home/defect/img/"+defect.getaPlc()));
            }
            if(defect.getbPlc()!=null){
                defect.setbPlc64(ImageToBase64ByLocal("/home/defect/img/"+defect.getbPlc()));
            }
        }
        Result result=new Result(count,list,0,"success");
        return result;
    }

    /**
     * 添加
     * @param defect
     * @return
     */
    @RequestMapping(value = "/addDefect",method = RequestMethod.POST)
    public String addDefect(@RequestBody Defect defect){
        Users users=this.getPrincipal();
        defect.setCreated(DateFormat.getYMDHMS(new Date()));
        defect.setCompany(1);
        defect.setType(1);//未认领
        defect.setYear(DateFormat.getYMD());
        if(users!=null){
            defect.setDepartmentId(users.getDepartmentId());
            defect.setCreatedBy(users.getEmployeeId());
        }else{
            return JSON.toJSONString(Type.NOUSER);//用户验证过期
        }
        int result=defectService.addDefect(defect);
        if(result>=0){
            defect.setNumber("P"+defect.getId());
            defectService.updDefect(defect);
            return JSON.toJSONString(Type.SUCCESS);
        }
        return JSON.toJSONString(Type.ERROR);
    }

    /**
     * 修改:消缺反馈
     * @param defect
     * @return
     */
    @RequestMapping(value = "/updDefect",method = RequestMethod.PUT)
    public synchronized String updDefect(@RequestBody Defect defect){
        Integer id=defect.getId();
        Integer type=defect.getType();
        Users users=this.getPrincipal();
        if(users==null){
            return JSON.toJSONString(Type.NOUSER);//用户验证过期,重新登录
        }
        if(id!=null){
            Defect defect1=defectService.getDefectById(id);
            //判断记录是否被修改，驳回
            if(defect1.getRealETime()!=null && !defect1.getRealETime().trim().equals("")){
                return JSON.toJSONString(Type.REJECT);
            }
            if(type.equals(2)){      //'消缺中'状态修改为'已完成'状态
                defect.setType(3);//已消缺
                defect.setCompleter(users.getEmployeeId());
                defect.setRealETime(DateFormat.getYMDHMS(new Date()));
                defectService.updDefect(defect);
                return JSON.toJSONString(Type.SUCCESS);
            }
        }
        return JSON.toJSONString(Type.ERROR);
    }

    /**
     * 开始执行
     * @param id
     * @return
     */
    @RequestMapping(value = "/startExecution",method = RequestMethod.PUT)
    public synchronized String startExecution(Integer id){
        if(id!=null){
            Defect defect=defectService.getDefectById(id);
            if(defect!=null){
                defect.setRealSTime(DateFormat.getYMDHMS(new Date()));
                defectService.updDefect(defect);
                return JSON.toJSONString(Type.SUCCESS);
            }
        }
        return JSON.toJSONString(Type.ERROR);
    }

    /**
     * 把图片保存到服务器
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("imgUpload")
    public synchronized JsonResult imgUpload(@RequestParam("file") MultipartFile file) throws IOException {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMessage(ImgUploadUtil.upload(file, "/home/defect/img/"));
        return jsonResult;
    }

    /**
     * 认领缺陷单(权限:班长)
     * @param empIds   分配的执行人员
     * @param id    缺陷单Id
     * @param planedTime    计划完成时间
     * @return
     */
    @RequestMapping("claim")
    public synchronized String claim(String empIds,Integer id,String planedTime){
        if(id!=null){
            Users users=this.getPrincipal();
            Defect defect=defectService.getDefectById(id);
            if(empIds!=null){
                defect.setEmpIds(empIds);
                defect.setPlanedTime(planedTime);//计划完成时间
                defect.setOrderReceivingTime(DateFormat.getYMDHMS(new Date()));//接单时间
                defect.setType(2);
                if(users==null){
                    JSON.toJSONString(Type.NOUSER);//用户验证过期
                }
                defect.setClaimant(users.getEmployeeId());
            }else{
                JSON.toJSONString(Type.FORMAT);//格式错误
            }
            defectService.updDefect(defect);
        }
        return JSON.toJSONString(Type.SUCCESS);
    }

    /**
     * 值班确认
     * @param id
     * @return
     */
    @RequestMapping(value = "/dutyConfirmation",method = RequestMethod.PUT)
    public String dutyConfirmation(Integer id){
        Users users=this.getPrincipal();
        if(users==null){
            return JSON.toJSONString(Type.NOUSER);
        }
        if(id!=null){
            Defect defect=defectService.getDefectById(id);
            if(defect!=null){
                if(defect.getConfirmer1Time()!=null){
                    return JSON.toJSONString(Type.REJECT);//已完成不可再确认
                }
                defect.setType(4);
                defect.setConfirmer1(users.getEmployeeId());
                defect.setConfirmer1Time(DateFormat.getYMDHMS(new Date()));
                defectService.updDefect(defect);
                return JSON.toJSONString(Type.SUCCESS);
            }
        }
        return JSON.toJSONString(Type.FORMAT);
    }

    /**---------------------------------------------------- 下拉框 ----------------------------------------------*/

    /**
     * 获取设备/系统
     * @param request
     * @return
     */
    @RequestMapping("/getEquMap")
    public List<Map<String,Object>> getEquMap(HttpServletRequest request){
        String type=request.getParameter("type");
        Users users=this.getPrincipal();

        Map souMap=new HashMap();
        if(users!=null){
            souMap.put("department",users.getDepartmentId());
        }
        souMap.put("type",type);
        List<Map<String,Object>> list=equipmentService.getEquMap(souMap);
        return list;
    }

    /**
     * 人员下拉框
     * @return
     */
    @RequestMapping("/getEmpMap")
    public List<Map<String,Object>> getEmpMap(){
        Users users=this.getPrincipal();

        Map souMap=new HashMap();
        if(users!=null){
            souMap.put("department",users.getDepartmentId());
        }
        List<Map<String,Object>> list=employeeService.getEmpMap(souMap);
        return list;
    }

}
