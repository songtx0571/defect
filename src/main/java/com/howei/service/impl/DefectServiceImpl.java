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
}
