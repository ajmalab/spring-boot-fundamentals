package com.wise.spring_boot_fundamentals;

import com.wise.spring_boot_fundamentals.db.BookRepository;
import com.wise.spring_boot_fundamentals.messaging.DefaultSlackProvider;
import com.wise.spring_boot_fundamentals.messaging.SlackClient;
import com.wise.spring_boot_fundamentals.messaging.SlackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfiguration {

  @Bean
  public BookRepository bookRepository(JdbcTemplate jdbcTemplate) {
    return new BookRepository(jdbcTemplate);
  }

}
