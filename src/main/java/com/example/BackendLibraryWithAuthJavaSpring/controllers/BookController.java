package com.example.BackendLibraryWithAuthJavaSpring.controllers;

import com.example.BackendLibraryWithAuthJavaSpring.domain.Book;
import com.example.BackendLibraryWithAuthJavaSpring.dtos.BookDTO;
import com.example.BackendLibraryWithAuthJavaSpring.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Book> addNewBook(@RequestBody BookDTO bookDTO) {
        var book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(book));
    }
}
