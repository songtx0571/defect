package com.howei;

import com.howei.pojo.Users;
import com.howei.service.DefectService;
import com.howei.service.EmployeeService;
import com.howei.service.EquipmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


}
