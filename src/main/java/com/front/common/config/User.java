package com.front.common.config;

public class User {
    private String username;
    private String password;
    private String name;
    private String company;
    private String position;

    public User(String username, String password, String name, String company, String position) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.company = company;
        this.position = position;
    }

    // Getter 메서드 추가
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }
}
