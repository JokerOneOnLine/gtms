package com.gtms.gtms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @Author: 84644
 * @Date: 2019/4/14 1:19
 * @Description: 邮件发送工具类
 **/
@Component
public class MailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 给某人发送异常邮件
     *
     * @param fromBody 发送至
     * @param toBody   发送到
     * @param topic    发送主题
     * @param content  发送内容
     */
    public void sendEmail(String fromBody, String toBody, String topic, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromBody);
        message.setTo(toBody);
        message.setSubject("主题：" + topic);
        message.setText(content);
        javaMailSender.send(message);
    }
}

