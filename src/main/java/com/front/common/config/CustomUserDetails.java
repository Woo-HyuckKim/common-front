package com.front.common.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final User user; // User는 DB에서 조회한 사용자 객체

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // 권한 설정
    }

    @Override
    public String getPassword() {
        return user.getPassword(); // 암호화된 패스워드
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 로그인한 사용자의 이름, 회사, 직책 등의 정보
    public String getName() {
        return user.getName();
    }

    public String getCompany() {
        return user.getCompany();
    }

    public String getPosition() {
        return user.getPosition();
    }

    // 나머지 필수 메서드들 구현
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
