package com.example.BackendLibraryWithAuthJavaSpring.controllers;

import com.example.BackendLibraryWithAuthJavaSpring.domain.Book;
import com.example.BackendLibraryWithAuthJavaSpring.dtos.BookDTO;
import com.example.BackendLibraryWithAuthJavaSpring.repositories.BookRepository;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBookById(@PathVariable(value = "id") String id,
                                                   @RequestBody BookDTO bookDTO) {
        Optional<Book> bookModel = bookRepository.findById(id);
        if(bookModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
        var book = bookModel.get();
        BeanUtils.copyProperties(bookDTO, book);
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable(value = "id") String id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        bookRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted sucessfully.");
    }

}
