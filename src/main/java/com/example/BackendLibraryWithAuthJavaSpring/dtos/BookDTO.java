package com.example.BackendLibraryWithAuthJavaSpring.dtos;

import java.time.LocalDate;

public record BookDTO(String author, String title,LocalDate releaseYear, Integer quantity) {
}
