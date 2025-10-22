package com.wise.spring_boot_fundamentals;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/book")
public class BookController {

  private ApplicationContext applicationContext;

  @PostMapping("/add")
  public ResponseEntity<Book> addBook(@RequestBody Book book) {
    //TODO: use the BookRepository here to add this book to the database!
    return ResponseEntity.ok().body(book);
  }
}
