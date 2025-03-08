package com.clm.logging.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class LogBackConfig {

    @PostConstruct
    public void setupLogger() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        // Create Pattern Encoder (JSON Format)
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("{\"timestamp\": \"%d{yyyy-MM-dd HH:mm:ss}\", \"level\": \"%p\", \"logger\": \"%c\", \"message\": \"%m\", \"exception\": \"%ex\"}%n");
        encoder.start();

        // Console Appender
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setContext(loggerContext);
        consoleAppender.setEncoder(encoder);
        consoleAppender.start();

        // Configure Root Logger
        Logger rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.detachAndStopAllAppenders(); // Remove default appenders
        rootLogger.addAppender(consoleAppender);
        rootLogger.setLevel(Level.INFO);

        // Configure Logger for 403 Errors
        Logger accessDeniedLogger = loggerContext.getLogger("org.springframework.security.access.AccessDeniedException");
        accessDeniedLogger.setLevel(Level.WARN);
        accessDeniedLogger.addAppender(consoleAppender);
    }
}
