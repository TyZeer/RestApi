package com.example.restapi.service;

import com.example.restapi.model.Book;
import com.example.restapi.model.Person;
import com.example.restapi.repos.BookRepos;
import com.example.restapi.repos.PersonRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
@Service
public class BookService {
    @Autowired
    private PersonRepos personRepos;
    @Autowired
    private BookRepos bookRepos;

    public List<Book> getAll() {

        return bookRepos.findAll();
    }

    public Book getById(Long id){

        return bookRepos.findById(id).orElseThrow();
    }

    public Book create(@Valid Book book) {
        bookRepos.save(book);
        return book;
    }

    public Book update(Long id, Book updBook) {
        Book exists = bookRepos.findById(id).orElse(null);
        if (exists == null){
            return null;
        }
        else{
            exists.setTitle(updBook.getTitle());
            exists.setAuthor(updBook.getAuthor());
            exists.setWriteYear(updBook.getWriteYear());
            exists.setPerson_id(updBook.getPerson_id()); //может не надо
            return bookRepos.save(exists);
        }
    }
    public int setOwner(Long personId, Long id){
        return bookRepos.setBookOwner(id, personId);

    }
    public int releaseBook(Long id){
        return bookRepos.setBookOwner(id, null);
    }

    public void delete(Long id) {

        bookRepos.deleteById(id);
    }
}
