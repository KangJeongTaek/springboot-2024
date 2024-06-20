package com.promm.backboard.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.promm.backboard.entity.Member;
import com.promm.backboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;

    public Member memberSave(String username, String email,String password){
        Member member =Member.builder().username(username).email(email).password(password).build();

        //비밀번호 인코딩하기
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

        member.setPassword(pwdEncoder.encode(password));
        return memberRepository.save(member);
    }
}
