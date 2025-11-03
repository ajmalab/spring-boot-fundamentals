package com.wise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppListener implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired
  ApplicationContext applicationContext;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    var beans = applicationContext.getBeanDefinitionNames();
  }
}
