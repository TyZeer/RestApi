package com.example.restapi.utils;

import com.example.restapi.model.Book;
import com.example.restapi.model.Person;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomEntityForPeople {
    private List<Book> books;
    private Person person;
    public CustomEntityForPeople(List<Book> book, Person person) {
     this.person = person;
     this.books = book;
      }
}
