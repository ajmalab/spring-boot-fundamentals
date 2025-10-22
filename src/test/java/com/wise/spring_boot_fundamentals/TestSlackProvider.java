package com.wise.spring_boot_fundamentals;

import com.wise.spring_boot_fundamentals.messaging.SlackProvider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSlackProvider implements SlackProvider {
  @Override
  public void sendMessage(String message) {
    log.info(message);
  }
}
