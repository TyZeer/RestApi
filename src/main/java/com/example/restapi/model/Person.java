package com.example.restapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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


}
