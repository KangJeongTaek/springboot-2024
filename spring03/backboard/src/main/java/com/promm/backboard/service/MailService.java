package com.promm.backboard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailService {
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String from;

    public void sentMail(String to,String subject,String message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // MIME type 설정
        
        try {
            // MimeMessageHelper로 MimeMessage 구성 이메일에 작성되는 글은 UTF-8로 생성
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage,false,"UTF-8");
            // 이메일 수신자 설정
            mmh.setTo(to);
            mmh.setSubject(subject);
            mmh.setText(message);
            mmh.setFrom(from);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
