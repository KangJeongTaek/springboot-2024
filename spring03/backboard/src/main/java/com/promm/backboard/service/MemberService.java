package com.promm.backboard.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.promm.backboard.common.NotFoundException;
import com.promm.backboard.entity.Member;
import com.promm.backboard.repository.MemberRepository;
import com.promm.backboard.security.MemberRole;

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
        member.setRole(MemberRole.USER); //일반 사용자 권한
        return memberRepository.save(member);
    }

    // 해당 정보의 사용자가 존재하는지 찾고 있다면 그 객체를 반환
    public Member membreFind(String username) {
        Optional<Member> _member = memberRepository.findByUsername(username);
        if(_member.isPresent()){
            return _member.get();
        }else{
            throw new NotFoundException("Member not found");
        }
    }

    public Member memberFindByEmail(String email){
        Optional<Member> member= memberRepository.findByEmail(email);
        if(member.isPresent()){
            return member.get();
        }else{
            throw new NotFoundException("Member not found");
        }
    }
    // 기존 사용자 비번 초기화
    public void membersavenewPassword(Member member){
        // 암호와
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }


    // 2024-07-04 React에서 넘어온 정보로 로그인 확인하기
    public Member getMemberByUsernameAndPassword(String username, String password) {
        Optional<Member> _member = memberRepository.findByUsername(username);
        Member realMember;
        if(_member.isPresent()){
            realMember = _member.get();
            
            //plain text와 암호화된 값이 일치하는지 찾아준다.
            boolean isMatched = passwordEncoder.matches(password, realMember.getPassword());
            if(isMatched){
                return realMember;
            }else{
                throw new NotFoundException("Wrong Password!");
            }
        }
        else{
            throw new NotFoundException("Member not found");
        }
    }
}
