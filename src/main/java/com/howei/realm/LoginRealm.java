package com.howei.realm;

import com.howei.pojo.Authority;
import com.howei.pojo.Role;
import com.howei.pojo.Users;
import com.howei.service.RoleService;
import com.howei.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        Users users=(Users) principalCollection.getPrimaryPrincipal();
        String userNumber= users.getUserNumber();
        //查询用户名称
        Users user= userService.getUserRolesByName(userNumber);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role:user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            Integer roleId=role.getId();
            List<Authority> list=roleService.getRoleAuthoritys(roleId+"");
            role.setAuthoritys(list);
            for (Authority p:role.getAuthoritys()) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(p.getName());
            }
        }
        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        String userNumber=token.getUsername();
        String password=String.copyValueOf(token.getPassword());
        if(userNumber!=null){
            Users user=userService.loginUserNumber(userNumber,null);
            if(user==null){
                throw new AuthenticationException("no_user");
            }else{
                Users user1= userService.loginUserNumber(userNumber,password);
                if(user1 == null){
                    throw new AuthenticationException("no_permission");
                }else{
                    if(user1.getState()==0){
                        throw new AuthenticationException("no_status");
                    }
                }
            }
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());

        }
        return null;
    }

    /**
     * 重写方法,清除当前用户的的 授权缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    /**
     * 重写方法，清除当前用户的认证缓存
     * @param principals
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * 自定义方法：清除所有授权缓存
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 自定义方法：清除所有认证缓存
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 自定义方法：清除所有的认证缓存和授权缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
