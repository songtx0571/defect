package com.howei.service.impl;

import com.howei.mapper.DepartmentMapper;
import com.howei.pojo.Department;
import com.howei.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper mapper;

    @Override
    public Department getById(Integer departmentId) {
        return mapper.getById(departmentId);
    }
}
