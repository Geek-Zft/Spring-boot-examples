package com.zft;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SendEmailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    /**
     * send simple text
     */
    @Test
    public void testSendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo("2507919076@qq.com");
        message.setSubject("sub: this is a sub");
        message.setText("this is a text");
        javaMailSender.send(message);
    }

    /**
     * send attachment mail
     */
    @Test
    public void testAttachmentMail() throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(username);
        messageHelper.setTo("2507919076@qq.com");
        messageHelper.setSubject("attachment email");
        messageHelper.setText("this is an attachment email");

        FileSystemResource file = new FileSystemResource(new File("E:\\idea_code\\spring-boot-demo\\src\\main\\resources\\static\\test.jpg"));
        messageHelper.addAttachment("attachment_1", file);
        javaMailSender.send(message);
    }


}
