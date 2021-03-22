package com.howei.util;

import java.util.List;

public class Friend {

    private String groupName;
    private String gid;
    private List<Mine> list;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public List<Mine> getList() {
        return list;
    }

    public void setList(List<Mine> list) {
        this.list = list;
    }
}
