package com.example.restapi.controller;

import com.example.restapi.errors.AppError;
import com.example.restapi.model.Person;
import com.example.restapi.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

;
import java.util.List;
@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
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
            return new ResponseEntity<>(personService.getById(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
                    "Id "+id+" not found"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createPerson( @Validated @RequestBody  Person person) { //ПОпробовать перевести в try catch
            try {
                return new ResponseEntity<>( personService.create(person),HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }



    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Long id,@Validated @RequestBody  Person person) {
        try{
            return new ResponseEntity<>( personService.update(id, person),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {

        personService.delete(id);
    }

}
