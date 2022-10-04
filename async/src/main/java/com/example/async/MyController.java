package com.example.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
@Slf4j
public class MyController {
  @Autowired
  SendMessageService sendMessageService;
  // simple GET
  // GET localhost:8080/greeting/sendMessage
  @RequestMapping(value = "/sendMessage",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> sendMessage() {
    log.info(Thread.currentThread().getName());
    sendMessageService.sendMessage();
    return ResponseEntity.status(HttpStatus.OK).body("Success");
  }
}
