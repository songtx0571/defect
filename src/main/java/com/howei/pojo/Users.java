package com.howei.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 账户表
 */
public class Users implements Serializable {
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", employeeId=" + employeeId +
                ", state=" + state +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", companyId=" + companyId +
                ", departmentId=" + departmentId +
                ", postId=" + postId +
                ", userNumber='" + userNumber + '\'' +
                ", manager=" + manager +
                ", entryDate='" + entryDate + '\'' +
                ", stateName='" + stateName + '\'' +
                ", departName='" + departName + '\'' +
                ", sexName='" + sexName + '\'' +
                ", postName='" + postName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", roles=" + roles +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    private static final long serialVersionUID = -2071565876962058344L;

    private Integer id;
    private String userName;//用户
    private String password;//密码
    private int employeeId;//员工Id
    private int state;//在职状态
    private int sex;
    private String phone;
    private  String email;
    private int companyId;//公司id
    private int departmentId;//部门id
    private int postId;//岗位id
    private String userNumber;//编号
    private int manager;//绩效管理人
    private String entryDate;//入职日期

    //虚字段
    private String stateName;
    private String departName;
    private String sexName;
    private String postName;
    private String companyName;
    private String managerName;
    private List<Role> roles;//角色集合
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
