package com.wise.spring_boot_fundamentals;

import com.wise.spring_boot_fundamentals.messaging.SlackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
  @Bean
  public SlackProvider slackProvider() {
    return new TestSlackProvider();
  }
}
