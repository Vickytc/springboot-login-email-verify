package com.example.service;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;

@Service
public class MailService {
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    /**
     * 激活账号邮件发送
     *
     * @param activationUrl 激活url链接 or验证码
     * @param email 收件人邮箱
     */
    public void sendMailForActivationAccount(String activationUrl, String email){
        //创建邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
            //设置邮件主题
            message.setSubject("个人账号激活验证码");
            //设置邮件发送者
            message.setFrom(mailUsername);
            //设置邮件接收者，可以多个
            message.setTo(email);
            //设置邮件抄送人
//            message.setCc();
            //设置邮件密送人
//            message.setBcc();
            //设置邮件发送日期
            message.setSentDate(new Date());
            //创建上下文环境
            Context context = new Context();
            context.setVariable("activationUrl", activationUrl);
            String text = templateEngine.process("active-account.html",context);
            //设置邮件正文
            message.setText(text,true);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        //邮件发送
        javaMailSender.send(mimeMessage);
    }
}
