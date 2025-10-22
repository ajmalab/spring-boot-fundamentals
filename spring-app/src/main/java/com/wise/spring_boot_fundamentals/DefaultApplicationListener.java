package com.wise.spring_boot_fundamentals;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DefaultApplicationListener implements ApplicationListener<ApplicationReadyEvent> {
  private final ApplicationContext applicationContext;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
      log.info("Add a breakpoint here and explore the application context!");
  }
}
