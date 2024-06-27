package com.promm.backboard.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class CustomErrorController implements ErrorController{
    
    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE); // 404,500,403

        if(status !=null){
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()){
                return "error/404";
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "error/500"; //가장 중요!
            }
        }

        return "error/error";
    }
    
}
