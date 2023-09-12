package com.example.restapi.service;

import com.example.restapi.model.Book;
import com.example.restapi.model.Person;
import com.example.restapi.repos.BookRepos;
import com.example.restapi.repos.PersonRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepos personRepos;
    @Autowired
    private BookRepos bookRepos;

    public List<Person> getAll() {

        return personRepos.findAll();
    }
    public List<Long> getPersonsBook(Long id){
        return personRepos.getBooks(id);
    }

    public Person getById(Long id){
        return personRepos.findById(id).orElseThrow();
    }

    public Person create(@Valid Person person) {
        personRepos.save(person);
        return person;
    }

    public Person update(Long id, Person person) {
        Person exists = personRepos.findById(id).orElse(null);
        if (exists == null){
            return null;
        }
        else{
            exists.setFullName(person.getFullName());
            exists.setAge(person.getAge());
            return personRepos.save(exists);
        }
    }

    public void delete(Long id) {
        personRepos.deleteById(id);
    }
}
