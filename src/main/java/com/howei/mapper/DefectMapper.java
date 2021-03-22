package com.howei.mapper;

import com.howei.pojo.Authority;
import com.howei.pojo.Defect;
import com.howei.pojo.Permission;
import com.howei.pojo.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface DefectMapper {


    List<Defect> getDefectList(Map map);

    int addDefect(Defect defect);

    int updDefect(Defect defect);

    Defect getDefectById(Integer id);
}
