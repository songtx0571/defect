package com.howei.config;

import com.howei.pojo.Users;
import com.howei.service.UserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 输错5次密码锁定2分钟，ehcache.xml配置
 * @author ZML
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private UserService userService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //boolean matches = super.doCredentialsMatch(token, info);
        if(token instanceof UsernamePasswordToken){
            //根据用户的登陆信息创建一个token
            UsernamePasswordToken userToken = (UsernamePasswordToken) token;
            //String accountCredentials = (String) info.getCredentials();
            Users user= userService.loginUserNumber(userToken.getUsername(),String.valueOf(userToken.getPassword()));
            char[] passwordchars =userToken.getPassword();
            String password = new String(passwordchars);
            //验证令牌
            if(password.equals("")){
                return false;
            }else{
                boolean res = true;
                return res;
            }
        }
        return false;
    }
}
