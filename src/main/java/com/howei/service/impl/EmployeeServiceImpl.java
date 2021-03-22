package com.howei.service.impl;

import com.howei.mapper.EmployeeMapper;
import com.howei.pojo.Employee;
import com.howei.pojo.Equipment;
import com.howei.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Map<String, Object>> getEmpMap(Map souMap) {
        List<Employee> list=employeeMapper.getEmpMap(souMap);
        List<Map<String,Object>> result=new ArrayList<>();
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                Employee employee = list.get(i);
                Map<String, Object> eMap = new HashMap<>();
                eMap.put("text", employee.getName());
                eMap.put("id", employee.getId());
                result.add(eMap);
            }
        }
        return result;
    }

    @Override
    public Map<Integer, String> getUsersMap() {
        List<Employee> list=employeeMapper.getUsersMap();
        Map<Integer, String> result=new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            Employee employee = list.get(i);
            result.put(employee.getId(), employee.getName());
        }
        return result;
    }
}
