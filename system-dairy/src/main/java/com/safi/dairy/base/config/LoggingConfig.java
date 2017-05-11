package com.safi.dairy.base.config;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by safi on 17/4/8.
 */
@Configuration
@ComponentScan("com.safi")
public class LoggingConfig {
    public LoggingConfig() {}

    @Bean
    public ConsoleAppender consoleAppender() {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setThreshold(Level.ALL);
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.setConversionPattern("%d %-5p [%c{1}] %m %n");
        consoleAppender.setLayout(patternLayout);
        return consoleAppender;
    }
}
