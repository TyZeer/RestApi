package com.example.restapi.utils;

import com.example.restapi.model.Book;
import com.example.restapi.model.Person;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomEntityForBooks {
    private Book book;
    private Long person_id;

    public CustomEntityForBooks(Book book, Person person) {
        this.book = book;
        this.person_id = person.getId();
    }
}
