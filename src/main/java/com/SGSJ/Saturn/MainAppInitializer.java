package com.SGSJ.Saturn;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class MainAppInitializer {
    public static void main(String[] args) {
        Application.launch(SaturnSystemApplication.class, args);
    }
}
