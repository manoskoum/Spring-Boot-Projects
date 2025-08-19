package com.school.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classesId;

    private String className;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY) // Προσθήκη Cascade
    @JoinTable(
            name="classes_students",
            joinColumns = @JoinColumn(name="class_id"),
            inverseJoinColumns = @JoinColumn(name="student_id")
    )
    @JsonIgnore
    private List<Students> students=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonBackReference
    private Professors professor;
}
