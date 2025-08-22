package com.manos.newproject.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {

    @NotBlank(message = "First name is mandatory")
    @Size(min = 5, max = 50, message = "First name must be at least 5 characters long")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 5, max = 50, message = "Last name must be at least 5 characters long")
    private String lastName;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;
}
