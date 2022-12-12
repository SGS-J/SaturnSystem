package com.SGSJ.Saturn;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainAppInitializer {
    public static void main(String[] args) {
        Application.launch(SaturnSystemApplication.class, args);
    }
}
