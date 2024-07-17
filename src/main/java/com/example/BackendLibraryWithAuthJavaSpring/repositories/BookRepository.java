package com.example.BackendLibraryWithAuthJavaSpring.repositories;

import com.example.BackendLibraryWithAuthJavaSpring.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
