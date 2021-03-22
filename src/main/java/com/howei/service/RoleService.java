package com.howei.service;

import com.howei.pojo.Authority;
import com.howei.pojo.Role;
import com.howei.pojo.RoleAuthority;
import com.howei.util.LayuiTree;

import java.util.List;
import java.util.Map;

public interface RoleService {
     List<Role> selectRole();

    Role getRoleById(String id);

    int updateRole(Role role);

    int addRole(Role role);

    void deleteRole(String roleId);

    List<Map<String,String>> getRolesMap();

    List<Role> getRoleAuthorityList(Map map);

    List<Map<String,String>> getAuthorityMap(Map map);

    List<Authority> getRoleAuthoritys(String roleId);

    void delRoleAuthoritys(String roleId);

    int addRoleAuthority(RoleAuthority roleAuthority);

    List<LayuiTree> getURLList(String parentId);

    List<Authority> getAuthIdByRoleId(String roleId);
}
