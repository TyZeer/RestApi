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
    private PersonService personService;
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
            return ResponseEntity.ok().body(personService.getById(id));
        }
        catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Id "+id+" not found"),
                    HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("")
    public ResponseEntity<?> createBook( @Validated @RequestBody Book book) { //ПОпробовать перевести в try catch
        try {
            return new ResponseEntity<>( bookService.create(book),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id,@Validated @RequestBody Book book) {
        try{
            return new ResponseEntity<>( bookService.update(id, book),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
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


    @PutMapping("/{bookId}/assign")
    public ResponseEntity<?> updateBookPerson(@PathVariable Long bookId, @RequestParam(name = "personid") Long personId) {
        try {
            Book book = bookService.getById(bookId); //Проверять на наличие человека зарание
            if(book.getPerson_id()==null) {
                book.setPerson_id(personId);
                bookService.update(bookId, book);
                return ResponseEntity.ok("Book person updated successfully");
            }
            else
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}/release")
    public ResponseEntity<?> releaseBook(@PathVariable Long id){
        try {
            Book book = bookService.getById(id);
            book.setPerson_id(null);
            bookService.update(id,book);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



//    @PutMapping("/{id}/assign")
//    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id,@RequestParam(name = "person",defaultValue = "1") Long person_id){
//        try {
//            return new ResponseEntity<>(bookService.setOwner(person_id,id),HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//    @PutMapping("/{id}/release")
//    public ResponseEntity<?> releaseBook(@PathVariable("id") Long id){
//        try {
//            return new ResponseEntity<>(bookService.releaseBook(id),HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
//                    "Id "+id+" not found"),
//                    HttpStatus.NOT_FOUND);
//        }
//    }




}
