package com.example.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendMessageService {
  @Async( "asyncExecutor")
  public void sendMessage() {
      log.info(Thread.currentThread().getName() + " - sending message....");
  }
}
