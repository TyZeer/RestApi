package com.example.restapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @Min(value = 0, message = "Year is too small")
    @Max(value = 2023, message = "This is a future")
    int writeYear;
    @Column(name = "author")
    @NotEmpty
    @Size(min = 1,max = 150, message = "Author should be valid")
    String author;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    Person person_id; // надо ли в модели????



}
