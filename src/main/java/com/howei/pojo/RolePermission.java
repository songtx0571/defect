package com.howei.pojo;

public class RolePermission {

    private int id;
    private int roleId;
    private int permissionId;
    private int type;

    private int parentId1;
    private int parentId2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getParentId1() {
        return parentId1;
    }

    public void setParentId1(int parentId1) {
        this.parentId1 = parentId1;
    }

    public int getParentId2() {
        return parentId2;
    }

    public void setParentId2(int parentId2) {
        this.parentId2 = parentId2;
    }


}
