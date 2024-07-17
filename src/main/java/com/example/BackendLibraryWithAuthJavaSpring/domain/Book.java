package com.example.BackendLibraryWithAuthJavaSpring.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "books")
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false, unique = true)
    private String author;
    @Column(nullable = false)
    private LocalDate releaseYear;
    @Column(nullable = false)
    private Integer quantity;

}
