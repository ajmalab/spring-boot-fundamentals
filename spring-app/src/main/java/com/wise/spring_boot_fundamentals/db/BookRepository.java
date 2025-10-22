package com.wise.spring_boot_fundamentals.db;

import com.wise.spring_boot_fundamentals.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookRepository {

  private final JdbcTemplate jdbcTemplate;

  public void insert(Book book) {
    jdbcTemplate.update("INSERT INTO book (title, author) VALUES (?,?)", book.getTitle(), book.getAuthor());
  }
}
