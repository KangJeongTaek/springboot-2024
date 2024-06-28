package com.promm.backboard.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promm.backboard.service.MailService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class RestMailController {
    
    private final MailService mailService;

    @PostMapping("/test-email")
    public ResponseEntity<HttpStatus> testEmail() {
        String to = "dfgddjd@gmail.com";
        String subject = "전송 테스트 드가자";
        String message = "테스트 메일의 메시지입니다. 제발 보내지기를 바라겠습니다.";
        mailService.sentMail(to, subject, message);
        
        return new ResponseEntity<HttpStatus> (HttpStatus.OK);
    }
    
}
