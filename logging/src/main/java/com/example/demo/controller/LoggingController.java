package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingController.class);
    
    // http://localhost:8080/log
    @RequestMapping("/log")
    public String log() {
        LOG.error("This is an error message.");
        LOG.warn("This is a warning message.");
        LOG.info("This is an info message.");
        LOG.debug("This is a debug message.");
        LOG.trace("This is a trace message.");
        return "foo";
    }
}
