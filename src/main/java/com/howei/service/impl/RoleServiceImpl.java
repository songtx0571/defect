package com.howei.service.impl;

import com.howei.mapper.RoleMapper;
import com.howei.pojo.Authority;
import com.howei.pojo.Role;
import com.howei.pojo.RoleAuthority;
import com.howei.service.RoleService;
import com.howei.util.LayuiTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> selectRole() {
        return roleMapper.selectRole();
    }

    @Override
    public Role getRoleById(String id) {
        return roleMapper.getRoleById(id);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public void deleteRole(String roleId) {
        roleMapper.deleteRole(roleId);
    }

    @Override
    public List<Map<String,String>> getRolesMap() {
        List<Role> list=roleMapper.getRolesMap();
        List<Map<String,String>> listMap=new ArrayList<>();
        if(list!=null){
            for (Role role: list) {
                Map<String, String> map=new HashMap();
                map.put("id",role.getId()+"");
                map.put("name",role.getRoleName());
                listMap.add(map);
            }
        }
        return listMap;
    }

    @Override
    public List<Role> getRoleAuthorityList(Map map) {
        return roleMapper.getRoleAuthorityList(map);
    }

    @Override
    public List<Map<String, String>> getAuthorityMap(Map map1) {
        List<Authority> list=roleMapper.getAuthorityMap(map1);
        List<Map<String,String>> listMap=new ArrayList<>();
        if(list!=null){
            for (Authority authority: list) {
                Map<String, String> map=new HashMap();
                map.put("id",authority.getId()+"");
                map.put("name",authority.getName());
                map.put("remark",authority.getRemark());
                listMap.add(map);
            }
        }
        return listMap;
    }

    @Override
    public List<Authority> getRoleAuthoritys(String roleId) {
        return roleMapper.getRoleAuthoritys(roleId);
    }

    @Override
    public void delRoleAuthoritys(String roleId) {
        roleMapper.delRoleAuthoritys(roleId);
    }

    @Override
    public int addRoleAuthority(RoleAuthority roleAuthority) {
        return roleMapper.addRoleAuthority(roleAuthority);
    }

    @Override
    public List<LayuiTree> getURLList(String parentId) {
        return roleMapper.getURLList(parentId);
    }

    @Override
    public List<Authority> getAuthIdByRoleId(String roleId) {
        return roleMapper.getAuthIdByRoleId(roleId);
    }
}
