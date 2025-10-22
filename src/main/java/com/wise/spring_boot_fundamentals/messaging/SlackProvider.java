package com.wise.spring_boot_fundamentals.messaging;

public interface SlackProvider {
  void sendMessage(String message);
}
