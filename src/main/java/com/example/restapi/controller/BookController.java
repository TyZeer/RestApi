package com.example.restapi.controller;

import com.example.restapi.errors.AppError;
import com.example.restapi.model.Book;
import com.example.restapi.model.Person;
import com.example.restapi.service.BookService;
import com.example.restapi.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")

public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Nothing found"),
                    HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Id "+id+" not found"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createPerson( @Validated @RequestBody Book book) { //ПОпробовать перевести в try catch
        try {
            return new ResponseEntity<>( bookService.create(book),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id,@Validated @RequestBody Book book) {
        try{
            return new ResponseEntity<>( bookService.update(id, book),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        try {
            bookService.delete(id);
           return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Id "+id+" not found"),
                    HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("{id}/assign")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody Long person_id){
        try {
            return new ResponseEntity<>(bookService.setOwner(person_id,id),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Id "+id+" not found"),
                    HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{id}/release")
    public ResponseEntity<?> releaseBook(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(bookService.releaseBook(id),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Id "+id+" not found"),
                    HttpStatus.NOT_FOUND);
        }
    }




}
