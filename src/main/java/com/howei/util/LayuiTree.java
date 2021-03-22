package com.howei.util;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 菜单树
 */
public class LayuiTree {
    private Integer id;
    private String title; //父节点id
    private Integer parentId;//父id
    private Integer level;//深度
    private List<Map<String,String>> checkArr;
    private List<LayuiTree> children; //子节点数据

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<Map<String, String>> getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(List<Map<String, String>> checkArr) {
        this.checkArr = checkArr;
    }

    public List<LayuiTree> getChildren() {
        return children;
    }

    public void setChildren(List<LayuiTree> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LayuiTree layuiTree = (LayuiTree) o;
        return Objects.equals(id, layuiTree.id);
    }
}
