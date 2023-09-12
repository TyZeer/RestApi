package com.example.restapi.repos;

import com.example.restapi.model.Book;
import com.example.restapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepos extends JpaRepository<Person,Long> {
    @Modifying
    @Query("SELECT id FROM Book where person_id = ?1")
    List<Long>getBooks(Long person_id);
}
