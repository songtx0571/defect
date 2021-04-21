package com.howei;

import com.howei.pojo.Users;
import com.howei.service.DefectService;
import com.howei.service.EmployeeService;
import com.howei.service.EquipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DefectApplicationTests {

    @Autowired
    private DefectService defectService;

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    EmployeeService employeeService;

    @Test
    void contextLoads() {
    }

    @Test
    void text1(){
        String type="1";
        Map souMap=new HashMap();
        souMap.put("department",17);
        souMap.put("type",type);
        List<Map<String,Object>> list=equipmentService.getEquMap(souMap);
        for (Map<String,Object> map:list){
            System.out.println(map.get("text")+"    "+map.get("id"));
        }
    }

    @Test
    void text2(){
        Map souMap=new HashMap();
        souMap.put("department",17);
        List<Map<String,Object>> list=employeeService.getEmpMap(souMap);
        for (Map<String,Object> map:list){
            System.out.println(map.get("text")+"    "+map.get("id"));
        }
    }

    @Test
    void text3() throws ParseException {
        String beginTime="2021-04-12 03:20";
        String endTime="2021-04-12 06:40";
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long bt=sdf.parse(beginTime).getTime();
        long et=sdf.parse(endTime).getTime();
        long diff=(et-bt);
        // 计算差多少小时
        long hour = diff / nh;
        // 计算差多少分钟
        long min = diff  % nh / nm;
        BigDecimal bd = new BigDecimal(hour+(Double.valueOf(min)/60));
        double result=bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(result);
    }


}
