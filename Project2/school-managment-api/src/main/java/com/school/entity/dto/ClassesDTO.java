package com.school.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.school.entity.model.Professors;
import com.school.entity.model.Students;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClassesDTO {

    @Positive(message = "Class id must be positive number")
    private Long classesId;

    @NotBlank(message = "Class name is mandatory")
    @Size(min = 2, max = 50, message = "Class name must be between 2 and 5o characters")
    private String className;

    private String professorName;

    private List<@NotBlank(message = "Student name cannot be empty")String> studentNames;

    public ClassesDTO(Long classesId, String className, String professorName, List<String> studentNames) {
        this.classesId = classesId;
        this.className = className;
        this.professorName = professorName;
        this.studentNames = studentNames;
    }

    public Long getClassesId() {
        return classesId;
    }

    public void setClassesId(Long classesId) {
        this.classesId = classesId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }
}
