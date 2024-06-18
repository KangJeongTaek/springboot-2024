package com.promm.backboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
public class MainController {
    
    @GetMapping("/hello")
    public String getHello() {
        log.debug("get Hello() : 실행");
        return "hello";
    }
    
    @GetMapping("/")
    public String getMain() {
        return "redirect:/board/list";
    }
    
}
