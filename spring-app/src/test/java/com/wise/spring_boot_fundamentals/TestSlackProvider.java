package com.wise.spring_boot_fundamentals;

import com.wise.spring_boot_fundamentals.messaging.SlackProvider;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class TestSlackProvider implements SlackProvider {
  private final List<String> messageStore = new ArrayList<>();

  @Override
  public void sendMessage(String message) {
    messageStore.add(message);
    log.info(message);
  }
}
