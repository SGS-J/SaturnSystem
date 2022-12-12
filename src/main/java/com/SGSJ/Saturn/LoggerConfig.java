package com.SGSJ.Saturn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

public class LoggerConfig {
    private final Logger logger = Logger.getLogger(LoggerConfig.class.getName());
    private static LoggerConfig loggerConfig;

    static {
        try {
            loggerConfig = new LoggerConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LoggerConfig() throws IOException {
        FileInputStream file = new FileInputStream(new ClassPathResource("logging.properties").getURL().getFile());
        LogManager.getLogManager().readConfiguration(file);
        this.configLogger();
        LogManager.getLogManager().addLogger(this.logger);
    }

    public static LoggerConfig getInstance() {
        return loggerConfig;
    }

    private void configLogger() throws IOException {
        File jarFile = new File(new ClassPathResource("").getURL().getPath());
        File appFile = jarFile.getParentFile().getParentFile().getParentFile();

        File folder = new File(appFile + "/log");
        folder.mkdir();
        File logFile = new File(folder + "/saturn.log");
        String pattern = logFile.getPath();

        FileHandler handler = new FileHandler(pattern);
        handler.setFormatter(new SimpleFormatter());
        this.logger.addHandler(handler);
    }

    public Logger getLogger() {
        return this.logger;
    }

    public String getLogMessage(String errorMessage) {
        return "\n\nError fatal en la aplicación:\n\n------ Init Stacktrace ------\n\n" + errorMessage + "\n\n------ End Stacktrace ------\n\n";
    }
}
