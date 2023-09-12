package com.example.restapi.repos;

import com.example.restapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepos extends JpaRepository<Book,Long> {
    @Modifying
    @Query("UPDATE Book b SET b.person_id = :personId WHERE b.id = :bookId")
    int setBookOwner(@Param("bookId") Long bookId, @Param("personId") Long personId);
}

