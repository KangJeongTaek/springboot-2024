package com.promm.backboard.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.promm.backboard.entity.Member;
import com.promm.backboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member memberSave(String username, String email,String password){
        Member member =Member.builder().username(username).email(email).password(password).build();

        //비밀번호 인코딩하기
        // 이보다는 Bean 등록을 해놓고 쓰는게 유지보수를 위해 더 좋다.
        // BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

        member.setPassword(passwordEncoder.encode(password));

        //처리되는 일이 많다면 현재 시간은 저장하기 직전에 추가해주는 것이 좋다.
        member.setRegDate(LocalDateTime.now());
        return memberRepository.save(member);
    }
}
