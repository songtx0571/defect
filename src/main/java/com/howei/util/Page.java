package com.howei.util;

public class Page {

    //获取
    public static int getOffSet(String page,String rows) {
        int result=(Integer.parseInt(page)-1)*Integer.parseInt(rows);
        return result;
    }

}
