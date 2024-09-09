package com.front.common.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	// 테스트용 사용자 정보를 저장한 Map
    private Map<String, User> userMap = new HashMap<>();

    // 생성자에서 테스트용 사용자 추가
    public CustomUserDetailsService() {
        // 샘플 유저 데이터 (username, password, name, company, position)
        userMap.put("kwh", new User("kwh", "$2a$10$HFYxSJVk3cyouHPZo.2MreOxyPLHT1j8BaSwDSVleuqjHapi11n5S", "John Doe", "ExampleCorp", "Manager")); // 패스워드는 "password"의 BCrypt 해시값
        userMap.put("jane", new User("jane", "$2a$10$HFYxSJVk3cyouHPZo.2MreOxyPLHT1j8BaSwDSVleuqjHapi11n5S", "Jane Smith", "TechCorp", "Developer")); // 패스워드는 "password123"의 BCrypt 해시값
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 데이터베이스에서 사용자 정보 조회 (예: JPA 사용)
    	System.out.println("1");
        User user =  userMap.get(username);
       
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("2");
        // 사용자 이름, 회사, 직책 등 필요한 정보를 포함한 CustomUserDetails 반환
        return new CustomUserDetails(user);
    }
    
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        String rawPassword = "1"; // 원래 비밀번호
        String encodedPassword = "$2a$10$HFYxSJVk3cyouHPZo.2MreOxyPLHT1j8BaSwDSVleuqjHapi11n5S"; // 기존 해시값
        System.out.println("1 = " + passwordEncoder.encode(rawPassword));
        // 입력된 비밀번호가 기존 해시값과 일치하는지 비교
        String aaa = passwordEncoder.encode(rawPassword);
        boolean isMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches: " + isMatch); // true
    }
}
