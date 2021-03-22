package com.howei.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.howei.pojo.Employee;
import com.howei.service.EmployeeService;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/socket/{userId}")
@Controller
public class WebSocket {
    public static EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        WebSocket.employeeService = employeeService;
    }
}
