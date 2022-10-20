package com.SGSJ.Saturn.domain.User;

public class User {
    private Long userId;
    private String name;
    private String email;
    private String pathToCV;
    private String phoneNumbers;
    private String state;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPathToCV() {
        return pathToCV;
    }

    public void setPathToCV(String pathToCV) {
        this.pathToCV = pathToCV;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
