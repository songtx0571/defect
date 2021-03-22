package com.howei.service;

import com.howei.pojo.Employee;
import com.howei.pojo.Role;
import com.howei.pojo.UserRole;
import com.howei.pojo.Users;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 登陆验证
     * @param userNumber
     * @param password
     * @return
     */
    public Users findUser(String userNumber, String password);

    List<Users> getUserList(Map map);

    List<Users> getUserByParam(Users users);

    Users getUserById(String userId);

    int addUser(Users user);

    int updateUser(Users users);

    Map<Integer,String> getUsersMap();

    List<Map<String,String>> getUsersList(Map map);

    List<Map> selSeen(String informId);

    String selSeenUser(String name);

    Users findUserByName(String name);

    int addEmployee(Employee employee);

    Users loginUserNumber(String userNumber, String password);

    int addUserRole(UserRole userRole);

    void deleteUserRole(int userId);

    List<Role> getUserRoles(String userId);

    List<Users> getUserRoleList(Map map);

    Users getUserRolesByName(String userName);

    int updPassword(Integer userId, String password);

    List<Users> searchUsersList(Map map);
}
