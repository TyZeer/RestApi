package com.example.restapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Size(min = 1,max = 150, message = "Title should be valid")
    @Column(name = "title")
    String title;
    @Column(name = "write_year")
    @Min(value = 0, message = "Year is too small")
    @Max(value = 2023, message = "This is a future")
    int writeYear;
    @Column(name = "author")
    @Size(min = 1,max = 150, message = "Author should be valid")
    String author;
    @Column(name = "person_id")
    Long person_id; // надо ли в модели????



}
