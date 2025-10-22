package com.wise.spring_boot_fundamentals;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/author")
public class AuthorController {

  @PostMapping("/add")
  public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
    //TODO: use the AuthorRepository here to add this book to the database!
    return ResponseEntity.ok().body(author);
  }
}
