package com.promm.backboard.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.promm.backboard.service.MemberService;
import com.promm.backboard.validation.MemberForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;





@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;

    @GetMapping("/register")
    public String register(MemberForm memberForm) {
        return "member/register";
    }
    
    @PostMapping("/register")
    public String register(@Valid MemberForm memberForm,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "member/register";
        }
        if(!memberForm.getPassword1().equals(memberForm.getPassword2())){
            bindingResult.rejectValue("password2","passwordInCorrect","패스워드가 일치하지 않습니다.");
            return "member/register";
        }
        try {
            memberService.memberSave(memberForm.getUsername(),memberForm.getEmail(), memberForm.getPassword1());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("registerFailed","이미등록된 사용자입니다.");  // 중복 사용자처리
            return "member/register";
        } catch(Exception e){
            e.printStackTrace();
            bindingResult.reject("registerFailed",e.getMessage());
            return "member/register";
        }
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }


}
