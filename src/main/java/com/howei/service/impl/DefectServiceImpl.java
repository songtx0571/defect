package com.howei.service.impl;

import com.howei.mapper.AuthorityMapper;
import com.howei.mapper.DefectMapper;
import com.howei.pojo.Authority;
import com.howei.pojo.Defect;
import com.howei.pojo.Permission;
import com.howei.pojo.RolePermission;
import com.howei.service.AuthorityService;
import com.howei.service.DefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DefectServiceImpl implements DefectService {

    @Autowired
    DefectMapper defectMapper;

    @Override
    public List<Defect> getDefectList(Map map) {
        return defectMapper.getDefectList(map);
    }

    @Override
    public int addDefect(Defect defect) {
        return defectMapper.addDefect(defect);
    }

    @Override
    public int updDefect(Defect defect) {
        return defectMapper.updDefect(defect);
    }

    @Override
    public Defect getDefectById(Integer id) {
        return defectMapper.getDefectById(id);
    }

    @Override
    public int getDefectCountByDep(int departmentId) {
        return defectMapper.getDefectCountByDep(departmentId);
    }

    @Override
    public List<Map<String, String>> getDefectHistiryByEqu(Map map) {
        List<Defect> list=defectMapper.getDefectHistiryByEqu(map);
        List<Map<String, String>> result=new ArrayList<>();
        for (Defect defect:list){
            Map<String,String> map1=new HashMap<>();
            map1.put("created",defect.getCreated());
            map1.put("abs",defect.getAbs());
            map1.put("type",defect.getType().toString());
            map1.put("sysName",defect.getSysName());
            map1.put("equipmentName",defect.getEquipmentName());
            result.add(map1);
        }
        return result;
    }
}
