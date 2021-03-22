package com.howei.service.impl;

import com.howei.mapper.EquipmentMapper;
import com.howei.pojo.Equipment;
import com.howei.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentMapper equipmentMapper;

    @Override
    public List<Map<String,Object>> getEquMap(Map map) {
        List<Equipment> list=equipmentMapper.getEquMap(map);
        List<Map<String,Object>> result=new ArrayList<>();
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                Equipment equipment = list.get(i);
                Map<String, Object> eMap = new HashMap<>();
                eMap.put("text", equipment.getName());
                eMap.put("id", equipment.getId());
                result.add(eMap);
            }
        }
        return result;
    }

}
