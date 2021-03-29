package com.howei.config;

import com.alibaba.fastjson.JSONObject;
import com.howei.pojo.OperationRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private final static Logger logger = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    //消息发送确认回调方法
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("消息发送成功:" + correlationData);
    }

    //消息发送失败回调方法
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.error("消息发送失败:" + new String(message.getBody()));
    }

    /**
     * 发送消息，不需要实现任何接口，供外部调用
     *
     */
    public void send(OperationRecord record) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        // 将消息类转为json发送
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE, RabbitConstant.RK_CONTRACT, JSONObject.toJSON(record).toString(), correlationId);
    }
}