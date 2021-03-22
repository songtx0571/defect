package com.howei.mapper;

import com.howei.pojo.Authority;
import com.howei.pojo.Role;
import com.howei.pojo.RoleAuthority;
import com.howei.util.LayuiTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface RoleMapper {
    List<Role> selectRole();

    Role getRoleById(String id);

    int updateRole(Role role);

    int addRole(Role role);

    void deleteRole(String roleId);

    List<Role> getRolesMap();

    List<Role> getRoleAuthorityList(Map map);

    List<Authority> getAuthorityMap(Map map);

    List<Authority> getRoleAuthoritys(@Param("roleId") String roleId);

    void delRoleAuthoritys(@Param("roleId") String roleId);

    int addRoleAuthority(RoleAuthority roleAuthority);

    List<LayuiTree> getURLList(@Param("roleId") String parentId);

    List<Authority> getAuthIdByRoleId(@Param("roleId") String roleId);
}
