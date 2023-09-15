package com.example.restapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table(name = "person")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Size(min = 2,max = 30, message = "Name should be valid")
    @Column(name = "full_name")
    String fullName;
    @Column(name = "age")
    @Min(value = 1, message = "You are too small")
    @Max(value = 150, message = "Can not live that long")
    int age;
    @OneToMany(mappedBy = "person_id")
    @JsonManagedReference
    private List<Book> books;


}
