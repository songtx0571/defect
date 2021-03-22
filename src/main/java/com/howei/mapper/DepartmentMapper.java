package com.howei.mapper;

import com.howei.pojo.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface DepartmentMapper {
    Department getById(@Param("id") Integer id);
}
