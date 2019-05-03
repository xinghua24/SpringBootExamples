package com.example.demo;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyConsoleBean implements CommandLineRunner{
    private static final Logger LOG = LoggerFactory.getLogger(MyConsoleBean.class);

    @Override
    public void run(String... args) throws Exception {
        // print all args
        Stream.of(args).forEach(LOG::info);
    }
}
