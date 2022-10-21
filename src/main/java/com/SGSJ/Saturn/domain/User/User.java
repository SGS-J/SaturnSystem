package com.SGSJ.Saturn.domain.User;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public class User {
    private Long userId;
    private String name;
    private String email;
    private String pathToCV;
    private String state;
    private ArrayList<String> phoneNumbers;
    private MultipartFile documentPDF;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public MultipartFile getDocumentPDF() {
        return documentPDF;
    }

    public void setDocumentPDF(MultipartFile documentPDF) {
        this.documentPDF = documentPDF;
    }
}
