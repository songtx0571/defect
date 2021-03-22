package com.howei.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单树
 */
public class MenuTree {
    private String id;
    private String pId; //父节点id
    private String text;//显示节点文本。也就是菜单的标题
    private String state;//节点状态，'open' 或 'closed'，默认：'open'。如果为'closed'的时候，将不自动展开该节点。
    private String url;//检索远程数据的URL地址。
    private Map<String, Object> attributes = new HashMap<String, Object>(); // 添加到节点的自定义属性
    private String iconCls;//图标

    private List<MenuTree> children; //子节点数据

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
}
