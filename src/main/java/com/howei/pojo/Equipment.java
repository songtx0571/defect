package com.howei.pojo;

/**
 * 系统号/设备
 */
public class Equipment {
    private int id;
    private String name;
    private int type;//类型:1为系统号;2为设备
    private int department;//项目部

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

}
