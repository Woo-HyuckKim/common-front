package com.front.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/login", "/css/**", "/js/**", "/img/**", "/fonts/**", "/test").permitAll() // 로그인 페이지와 정적 리소스에 접근 허용
                .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
            )
            .formLogin((form) -> form
                .loginPage("/login") // 커스텀 로그인 페이지 설정
                .loginProcessingUrl("/login") 
                .defaultSuccessUrl("/test", true) // 로그인 성공 후 리다이렉트 페이지
                .permitAll()
            )
            .logout((logout) -> logout.permitAll());

        return http.build();
    }

    // 비밀번호 암호화 방식 설정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 유저 정보를 제공하는 UserDetailsService 빈 설정 (예시용)
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService; // CustomUserDetailsService는 따로 정의
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        
        authenticationManagerBuilder
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }
}
