package com.howei.controller;

import com.alibaba.fastjson.JSON;
import com.howei.pojo.Company;
import com.howei.pojo.Defect;
import com.howei.pojo.Users;
import com.howei.service.CompanyService;
import com.howei.service.DefectService;
import com.howei.service.EmployeeService;
import com.howei.service.EquipmentService;
import com.howei.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static com.howei.util.Image.ImageToBase64ByLocal;
import static org.apache.shiro.authz.annotation.Logical.AND;
import static org.apache.shiro.authz.annotation.Logical.OR;

/**
 * 缺陷
 */
@RestController
@RequestMapping("defect/defect")
public class DefectController {

    @Autowired
    private DefectService defectService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CompanyService companyService;

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
    public Result getDefectList(HttpServletRequest request) throws ParseException{
        String type=request.getParameter("type");//缺陷状态
        String sysId=request.getParameter("sysId");//系统
        String equipmentId=request.getParameter("equipmentId");//设备
        String departmentId=request.getParameter("departmentId");//部门
        String page=request.getParameter("page");
        String limit=request.getParameter("limit");
        int rows=Page.getOffSet(page,limit);

        Users users=this.getPrincipal();
        if(users==null){
            Result result=new Result(0,new ArrayList<>(),0,"NoUser");
            return result;
        }
        Subject subject=SecurityUtils.getSubject();;

        //加载用户Map
        Map empMap=getUsersMap();

        Map map=new HashMap();
        if(type!=null&&!"".equals(type)){
            if(!type.equals("0")){//全部缺陷: 忽略状态
                if(!subject.isPermitted("缺陷管理员")){
                    map.put("departmentId",users.getDepartmentId());
                }
                map.put("type",type);
            }
        }else{
            map.put("type1",4);
        }
        if(sysId!=null && !"".equals(sysId) && !sysId.equals("-1")){
            map.put("sysId",sysId);
        }
        if(equipmentId!=null&&!"".equals(equipmentId) && !equipmentId.equals("-1")){
            map.put("equipmentId",equipmentId);
        }
        if(users!=null){
            if(!subject.isPermitted("缺陷管理员")){
                map.put("departmentId",users.getDepartmentId());
            }
        }
        if(departmentId!=null && !departmentId.equals("") && !departmentId.equals("-1")){
            map.put("departmentId",departmentId);
        }

        List<Defect> total=defectService.getDefectList(map);
        int count=0;
        count=total!=null ? total.size():0;
        map.put("pageSize",limit);
        map.put("page",rows);
        List<Defect> list=defectService.getDefectList(map);

        Iterator<Defect> iterator = list.iterator();
        while(iterator.hasNext()) {
            Defect defect = iterator.next();
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
                String aPlc64=ImageToBase64ByLocal("/home/defect/img/"+defect.getaPlc());
                if(aPlc64.equals("")){
                    defect.setaPlc64("");
                }else {
                    defect.setaPlc64(aPlc64);
                }
            }
            if(defect.getbPlc()!=null){
                String bPlc64=ImageToBase64ByLocal("/home/defect/img/"+defect.getbPlc());
                if(bPlc64.equals("")){
                    defect.setbPlc64("");
                }else{
                    defect.setbPlc64(bPlc64);
                }
            }
            if(Double.valueOf(defect.getRealExecuteTime())==null){
                String realETime=defect.getRealETime();//实际结束时间
                String planedTime=defect.getPlanedTime();//计划完成时间
                String realSTime=defect.getRealSTime();//实际开始时间
                if(realETime!=null&&planedTime!=null&&realSTime!=null){
                    double diff1=DateFormat.getBothNH(realSTime,planedTime);
                    double diff2=DateFormat.getBothNH(realSTime,realETime);
                    if(diff1<=diff2){
                        defect.setRealExecuteTime(diff1);
                    }else{
                        defect.setRealExecuteTime(diff2);
                    }
                }
            }
        }
        Result result=new Result(count,list,0,"success");
        return result;
    }

    @RequestMapping("/getPermission")
    public String getPermission(String permissionName){
        Subject subject=SecurityUtils.getSubject();
        boolean bool=subject.isPermitted(permissionName);
        if(bool){
            return JSON.toJSONString("true");
        }else{
            return JSON.toJSONString("false");
        }
    }

    /**
     * 添加缺陷单
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
        //获取部门首字母
        Company company=companyService.getCompanyById(users.getDepartmentId()+"");
        if(company!=null && company.getCodeName().equals("")){
            return JSON.toJSONString(Type.NoDepNumber);//当前部门无编号
        }

        int result=defectService.addDefect(defect);
        //设置缺陷单编号
        if(result>=0){
            int count=defectService.getDefectCountByDep(users.getDepartmentId());
            int insId=count;
            if(company.getId()==19){//浦江项目部从819开始
                insId=819+count;
            }
            if(insId<10){
                defect.setNumber(company.getCodeName()+"000"+insId);
            } else if(insId>=10 && insId<100){
                defect.setNumber(company.getCodeName()+"00"+insId);
            } else if(insId>=100 && insId<10000){
                defect.setNumber(company.getCodeName()+"0"+insId);
            } else {
                defect.setNumber(company.getCodeName()+insId);
            }
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
    public synchronized String updDefect(@RequestBody Defect defect) throws ParseException {
        Integer id=defect.getId();
        Integer type=defect.getType();
        Users users=this.getPrincipal();
        if(users==null){
            return JSON.toJSONString(Type.NOUSER);//用户验证过期,重新登录
        }
        if(id!=null){
            Defect defect1=defectService.getDefectById(id);
            if(defect1!=null){
                //判断当前登录人是否有权限修改这条记录
                if(String.valueOf(defect1.getEmpIds()).indexOf(String.valueOf(users.getEmployeeId()))<0){
                    return JSON.toJSONString(Type.NOPERMISSION);
                }
            }
            //判断记录是否被修改，驳回
            if(defect1.getRealETime()!=null && !defect1.getRealETime().trim().equals("")){
                return JSON.toJSONString(Type.REJECT);
            }
            if(type.equals(2)){      //'消缺中'状态修改为'已完成'状态
                defect.setType(3);//已消缺
                defect.setCompleter(users.getEmployeeId());
                String realETime=DateFormat.getYMDHMS(new Date());//实际结束时间
                defect.setRealETime(realETime);
                String planedTime=defect.getPlanedTime();//计划完成时间
                String realSTime=defect.getRealSTime();//实际开始时间
                double diff1=DateFormat.getBothNH(realSTime,planedTime);
                double diff2=DateFormat.getBothNH(realSTime,realETime);
                if(diff1<=diff2){
                    defect.setRealExecuteTime(diff1);
                }else{
                    defect.setRealExecuteTime(diff2);
                }
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
    public synchronized Map<String,String> startExecution(Integer id,String type,String delayETime,Integer delayReason){
        Map<String,String> map=new HashMap<>();
        Users users=this.getPrincipal();
        if(users==null){
            map.put("msg","noUser");//重新登录
            return map;
        }
        if(id!=null){
            Defect defect=defectService.getDefectById(id);
            if(defect!=null){
                if(type!=null&&type.equals("6")){
                    if(String.valueOf(defect.getEmpIds()).indexOf(String.valueOf(users.getEmployeeId()))>-1){
                        defect.setType(6);//延期中
                        defect.setDelaySTime(DateFormat.getYMDHMS(new Date()));//延期开始时间
                        defect.setDelayETime(delayETime);//延期结束时间
                        defect.setDelayReason(delayReason);//延期理由
                        defectService.updDefect(defect);
                        map.put("msg","success");
                        return map;
                    }else{
                        //无权限
                        map.put("msg","noPermission");
                    }
                }else{
                    if(String.valueOf(defect.getEmpIds()).indexOf(String.valueOf(users.getEmployeeId()))>-1){
                        defect.setType(2);//消缺中
                        defect.setRealSTime(DateFormat.getYMDHMS(new Date()));
                        defectService.updDefect(defect);
                        map.put("msg","success");
                        return map;
                    }else{
                        //无权限
                        map.put("msg","noPermission");
                    }
                }
            }
        }else{
            map.put("msg","error");//id为空
        }
        return map;
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
    public synchronized String claim(String empIds,Integer id,String planedTime,String type,String delayETime,Integer delayReason){
        Subject subject=SecurityUtils.getSubject();
        if(!subject.isPermitted("缺陷检修班长")){
            return JSON.toJSONString(Type.NOPERMISSION);//无权限
        }
        if(id!=null){
            Users users=this.getPrincipal();
            Defect defect=defectService.getDefectById(id);
            //type=6
            if(type!=null&&type.equals("6")){
                defect.setType(6);//延期中
                defect.setDelayBy(users.getEmployeeId());//申请延期人
                defect.setDelaySTime(DateFormat.getYMDHMS(new Date()));//延期开始时间
                defect.setDelayETime(delayETime);//延期结束时间
                defect.setDelayReason(delayReason);//延期理由
                //设置认领数据为空
            }else{
                if(empIds!=null){
                    defect.setEmpIds(empIds);
                    defect.setPlanedTime(planedTime);//计划完成时间
                    defect.setOrderReceivingTime(DateFormat.getYMDHMS(new Date()));//接单时间
                    defect.setType(5);//已认领
                    //设置延期为空
                    defect.setDelayBy(users.getEmployeeId());//申请延期人
                    if(users==null){
                        JSON.toJSONString(Type.NOUSER);//用户验证过期
                    }
                    defect.setClaimant(users.getEmployeeId());
                }else{
                    JSON.toJSONString(Type.FORMAT);//格式错误
                }
            }
            defectService.updDefect(defect);
            return JSON.toJSONString(Type.SUCCESS);
        }
        return JSON.toJSONString(Type.FORMAT);
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
                defect.setType(4);//已完成
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
        Subject subject=SecurityUtils.getSubject();

        Map souMap=new HashMap();
        if(!subject.isPermitted("缺陷管理员")) {
            if (users != null) {
                souMap.put("department", users.getDepartmentId());
            }
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
            souMap.put("departmentId",users.getDepartmentId());
        }
        souMap.put("state",1);
        List<Map<String,Object>> list=employeeService.getEmpMap(souMap);
        return list;
    }

    /**
     * 部门下拉框
     * @return
     */
    @RequestMapping("/getDepMap")
    @RequiresPermissions(value = {"缺陷管理员"},logical = AND)
    public List<Map<String,String>> getDepMap(){
        Subject subject=SecurityUtils.getSubject();
        List<Map<String,String>> list=new ArrayList<>();
        if(subject.isPermitted("缺陷管理员")){
            list=companyService.getDepartmentList("1");
        }
        return list;
    }

    /**
     * 根据设备获取历史缺陷
     * @param sysId
     * @param euqipmentId
     * @return
     */
    @RequestMapping("/getDefectHistiryByEqu")
    public List<Map<String,String>> getDefectHistiryByEqu(Integer sysId,Integer euqipmentId){
        Users users=this.getPrincipal();
        Subject subject=SecurityUtils.getSubject();;
        List<Map<String,String>> result=new ArrayList<>();
        if(sysId==-1&&euqipmentId==-1){
            return null;
        }
        if(users==null){
            Map<String,String> map=new HashMap<>();
            map.put("msg","noUser");
            result.add(map);
            return result;
        }
        Map map=new HashMap();
        map.put("equipmentId",euqipmentId);
        map.put("sysId",sysId);
        if(!subject.isPermitted("缺陷管理员")){
            map.put("departmentId",users.getDepartmentId());
        }
        result=defectService.getDefectHistiryByEqu(map);
        return result;
    }

    /**
     * 获取缺陷
     * @param id
     * @return
     */
    @RequestMapping("/getDefectById")
    public Defect getDefectById(Integer id){
        if(id!=null){
            Defect defect = defectService.getDefectById(id);
            //加载用户Map
            Map empMap=getUsersMap();
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
                String aPlc64=ImageToBase64ByLocal("/home/defect/img/"+defect.getaPlc());
                if(aPlc64.equals("")){
                    defect.setaPlc64("");
                }else {
                    defect.setaPlc64(aPlc64);
                }
            }
            if(defect.getbPlc()!=null){
                String bPlc64=ImageToBase64ByLocal("/home/defect/img/"+defect.getbPlc());
                if(bPlc64.equals("")){
                    defect.setbPlc64("");
                }else{
                    defect.setbPlc64(bPlc64);
                }
            }
            return defect;
        }
        return null;
    }


}
