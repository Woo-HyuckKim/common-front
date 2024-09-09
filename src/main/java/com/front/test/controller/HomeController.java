package com.front.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.front.common.config.CustomUserDetails;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Spring Boot 3.3.3");
        return "index";  // src/main/resources/templates/index.html 을 찾음
    }
    
    @GetMapping("/test")
    public String test(Model model) {
    	
    	// 현재 로그인한 사용자 정보 가져오기
    	System.out.println("test Start");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getPrincipal() =  " + authentication.getPrincipal());
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 사용자 이름, 회사, 직책 등을 모델에 추가
        System.out.println("userDetails.getName() =  " + userDetails.getName());
        System.out.println("userDetails.getCompany() =  " + userDetails.getCompany());
        System.out.println("userDetails.getPosition() =  " + userDetails.getPosition());
        model.addAttribute("name", userDetails.getName());
        model.addAttribute("company", userDetails.getCompany());
        model.addAttribute("position", userDetails.getPosition());
    	
        return "page/main";
    }
    
    @GetMapping("/login")
    public String login() {
    	System.out.println("123213213213");
        return "page/login";
    }
    
    @PostMapping("/login")
    public String loginp() {
    	System.out.println("5555555555555555555");
        return "page/login";
    }
}
