package com.howei.controller;

import com.howei.config.Sender;
import com.howei.pojo.OperationRecord;
import com.howei.pojo.Users;
import com.howei.service.UserService;
import com.howei.util.DateFormat;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping("/defect/operation")
public class OperationController {
    @Autowired
    private Sender sender;

    @Autowired
    private UserService userService;



    @PostMapping("/send")
    @ResponseBody
    public String sendRecord(
                             @RequestParam(required = false) String verb,
                             @RequestParam(required = false) String content,
                             @RequestParam(required = false) String type,
                             @RequestParam(required = false) String remark,
                             @RequestParam(required = false) Integer id
                             ) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Subject subject = SecurityUtils.getSubject();
        Users users = (Users) subject.getPrincipal();
        int sendId = users.getEmployeeId();
        String userName = users.getUserName();
        Long timeMillis = System.currentTimeMillis();
        OperationRecord record = new OperationRecord();
        record.setSendId(sendId);
        record.setSendName(userName);
        record.setVerb(verb);
        record.setContent(content);
        record.setType(type);
        record.setRemark(remark);
        record.setLongTime(timeMillis.toString());
        record.setCreateTime(sdf.format(timeMillis));


        String level ="5";
        Map<String, Object> userSettinMap = userService.getUserSettingByEmployeeId(sendId);
        if(userSettinMap!=null&& userSettinMap.get(type + "_level")!=null){
            level = (String) userSettinMap.get(type + "_level");
        }

        Long confirmTime = DateFormat.getConfirmTimeMills(timeMillis, level);

        if (confirmTime != null) {
            record.setConfirmTime(sdf.format(confirmTime));
        } else {
            record.setConfirmTime("1");
        }


        System.out.println("Openation::" + record);
        try {
            sender.send(record);
            //webSocketOperation.sendMessageToAll(record.toString());
            return "SUCCESSS";
        } catch (Exception e) {

            return "FALSE";
        }

    }
}
