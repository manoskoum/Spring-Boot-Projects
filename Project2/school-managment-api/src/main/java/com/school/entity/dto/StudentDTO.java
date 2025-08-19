package com.school.entity.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {

    @Positive(message = "Student id must be positive number")
    private Long studentId;

    @NotBlank(message = "Student name is mandatory")
    @Size(min = 2, max = 50, message = "Student name must be between 2 and 5o characters")
    private String studentName;

    @Valid
    private List<StudentDTO.ClassDTO> classes;

    @Data
    public static class ClassDTO {
        @NotNull(message = "Class id is mandatory")
        @Positive(message = "Class id mus be positive number")
        private Long classesId;

        @NotBlank(message = "Class name is mandatory")
        @Size(min = 2, max = 60, message = "Class name must be between 2 and 5o characters")
        private String className;
    }

}
