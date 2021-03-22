package com.howei.util;

public enum Type {
     SUCCESS,//成功
     ERROR,//后台错误
     CANCEL,//页面错误
    INVALID,//登录信息失效
    HAVE,//存在同名
    NOUSER,//用户验证信息过期
    FORMAT,//格式错误
    REJECT;//驳回：记录已经被修改
}
