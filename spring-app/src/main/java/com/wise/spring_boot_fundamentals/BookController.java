package com.wise.spring_boot_fundamentals;

import com.wise.spring_boot_fundamentals.messaging.SlackProvider;
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
  private SlackProvider slackProvider;

  @PostMapping("/add")
  public ResponseEntity<Book> addBook(@RequestBody Book book) {
    //TODO: use the BookRepository here to add this book to the database!
    // Bonus: Use the SlackProvider to send a slack message when the book is added.
    return ResponseEntity.ok().body(book);
  }
}
