package com.manos.newproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 5, max = 50, message = "First name must be at least 5 characters long")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 5, max = 50, message = "Last name must be at least 5 characters long")
    private String lastName;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;
}

