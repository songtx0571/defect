package com.howei.mapper;

import com.howei.pojo.Employee;
import com.howei.pojo.Role;
import com.howei.pojo.UserRole;
import com.howei.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UsersMapper {

    /**
     * 登陆验证
     * @param userNumber
     * @param password
     * @return
     */
    Users findUser(@Param("userNumber") String userNumber, @Param("password") String password);

    List<Users> getUserList(Map map);

    List<Users> getUserByParam(Users users);

    Users getUserById(String userId);

    int addUser(Users user);

    int updateUser(Users users);

    List<Users> getUsersMap();

    List<Users> getUsersList(Map map);

    List<Map> selSeen(String informId);

    String selSeenUser(@Param("userId") String userId);

    Users findUserByName(@Param("id") String id);

    int addEmployee(Employee employee);

    Users loginUserNumber(@Param("userNumber") String userNumber, @Param("password") String password);

    int addUserRole(UserRole userRole);

    void deleteUserRole(@Param("userId") int userId);

    List<Role> getUserRoles(@Param("userId") String userId);

    List<Users> getUserRoleList(Map map);

    Users getUserRolesByName(@Param("userNumber") String userNumber);

    int updPassword(@Param("userId") Integer userId, @Param("password") String password);

    List<Users> searchUsersList(Map map);
}
