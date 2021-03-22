package com.howei.util;

import java.util.List;

public class CheckboxTree {
    private String name;
    private Integer value;
    private List<CheckboxTree> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<CheckboxTree> getChildren() {
        return children;
    }

    public void setChildren(List<CheckboxTree> children) {
        this.children = children;
    }
}
