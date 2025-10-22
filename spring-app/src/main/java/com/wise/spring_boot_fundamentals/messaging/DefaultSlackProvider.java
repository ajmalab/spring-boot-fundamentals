package com.wise.spring_boot_fundamentals.messaging;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultSlackProvider implements SlackProvider {
  private final SlackClient slackClient;

  @Override
  public void sendMessage(String message) {
    slackClient.sendMessage("Hello World");
  }
}
