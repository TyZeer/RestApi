package com.example.restapi.repos;

import com.example.restapi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepos extends JpaRepository<Person,Long> {
}
