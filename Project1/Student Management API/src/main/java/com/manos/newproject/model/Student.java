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

    @NotBlank(message = "Το όνομα είναι υποχρεωτικό")
    @Size(min = 2, max = 50, message = "Το όνομα πρέπει να έχει 2-50 χαρακτήρες")
    private String firstname;

    @NotBlank(message = "Το επώνυμο είναι υποχρεωτικό")
    @Size(min = 2, max = 50, message = "Το επώνυμο πρέπει να έχει 2-50 χαρακτήρες")
    private String lastname;

    @Min(value = 18, message = "Η ηλικία πρέπει να είναι τουλάχιστον 18")
    private int age;
}

