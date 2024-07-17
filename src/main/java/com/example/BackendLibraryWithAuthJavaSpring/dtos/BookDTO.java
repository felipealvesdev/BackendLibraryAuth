package com.example.BackendLibraryWithAuthJavaSpring.dtos;

import java.time.LocalDate;

public record BookDTO(String author, LocalDate releaseYear, Integer quantity) {
}
