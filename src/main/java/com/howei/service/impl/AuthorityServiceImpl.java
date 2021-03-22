package com.howei.service.impl;

import com.howei.mapper.AuthorityMapper;
import com.howei.pojo.Authority;
import com.howei.pojo.Permission;
import com.howei.pojo.RolePermission;
import com.howei.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public int addAistributeURL(Authority authority) {
        return authorityMapper.addAistributeURL(authority);
    }

    @Override
    public Authority getUrlById(String id) {
        return authorityMapper.getUrlById(id);
    }

    @Override
    public List<Authority> getURLList(Map map) {
        return authorityMapper.getURLList(map);
    }

    @Override
    public List<Permission> getPermissionByparentId(String parentId) {
        return authorityMapper.getPermissionByparentId(parentId);
    }

    @Override
    public List<RolePermission> getRolePermissionByRoleId(String roleId) {
        return authorityMapper.getRolePermissionByRoleId(roleId);
    }

    @Override
    public int changRolePermissionTypeByRoleId(String roleId) {
        return authorityMapper.changRolePermissionTypeByRoleId(roleId);
    }

    @Override
    public int addRolePermission(String roleId, String permissionId) {
        return authorityMapper.addRolePermission(roleId,permissionId);
    }

    @Override
    public int getCountRolePermission(String roleId, String permissionId) {
        return authorityMapper.getCountRolePermission(roleId,permissionId);
    }

    @Override
    public int changRolePermissionTypeByPermissionId(String roleId, String permissionId) {
        return authorityMapper.changRolePermissionTypeByPermissionId(roleId,permissionId);
    }

    @Override
    public int updAistributeURL(Authority authority) {
        return authorityMapper.updAistributeURL(authority);
    }
}
