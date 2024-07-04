package com.promm.backboard.restcontroller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.promm.backboard.dto.Header;
import com.promm.backboard.entity.Member;
import com.promm.backboard.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
@Slf4j
public class RestMemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public Header<Member> login(@RequestParam Map<String,String> logininfo) {
        log.info("▶▶▶▶▶▶ React에서 넘어온 정보 {}" , logininfo.get("username"));
        
        // 계정 정보 객체 필요 없음
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = logininfo.get("username");
        String password = logininfo.get("password");
        try{
        Member member = memberService.getMemberByUsernameAndPassword(username,password);
        if(member!=null){
            Header<Member> result = Header.OK(member);
            return result;
        }else{
            Header<Member> fail = Header.OK("Member Not Found");
            return fail;
        }
        }catch(Exception e){
            Header<Member> fail = Header.OK("Member Not Found");
            return fail;
        }
    }
    
}
