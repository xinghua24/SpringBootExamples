package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements ApplicationRunner{
    private static final Logger LOG = LoggerFactory.getLogger(MyBean.class);
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        args.getOptionNames().forEach(LOG::info);
    }
}
