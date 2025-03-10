package com.school.entity;

import com.school.entity.model.Classes;
import com.school.entity.model.Professors;
import com.school.entity.model.Students;
import com.school.entity.repository.ClassesRepository;
import com.school.entity.repository.ProfessorRepository;
import com.school.entity.repository.StudentsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class DataInitializer {

    private final StudentsRepository studentsRepository;
    private final ProfessorRepository pRepository;
    private final ClassesRepository cRepository;

    public DataInitializer(StudentsRepository studentsRepository,
                           ProfessorRepository pRepository,
                           ClassesRepository cRepository) {
        this.studentsRepository = studentsRepository;
        this.pRepository = pRepository;
        this.cRepository = cRepository;
    }

    @Bean
    public CommandLineRunner initializeData() {
        return args -> {

            // Create classes
            Classes classes1 = new Classes();
            classes1.setClassName("Class1");

            Classes classes2 = new Classes();
            classes2.setClassName("Class2");

            Classes classes3 = new Classes();
            classes3.setClassName("Class3");

            // Save classes
            cRepository.save(classes1);
            cRepository.save(classes2);
            cRepository.save(classes3);

            // Create students
            Students students1 = new Students();
            students1.setStudentName("John Doe");

            Students students2 = new Students();
            students2.setStudentName("Jane Doe");

            Students students3 = new Students();
            students3.setStudentName("Oe Doe");

            // Associate students with classes
            students1.getClasses().add(classes1);
            students1.getClasses().add(classes2);
            students1.getClasses().add(classes3);

            students2.getClasses().add(classes1);
            students2.getClasses().add(classes3);

            students3.getClasses().add(classes1);
            students3.getClasses().add(classes2);

            classes1.getStudents().add(students1);
            classes1.getStudents().add(students2);
            classes1.getStudents().add(students3);

            classes2.getStudents().add(students1);
            classes2.getStudents().add(students3);

            classes3.getStudents().add(students1);
            classes3.getStudents().add(students2);

            // Save students AFTER associations
            studentsRepository.save(students1);
            studentsRepository.save(students2);
            studentsRepository.save(students3);

            // Create professors
            Professors professors1 = new Professors();
            professors1.setProfessorName("Dr. Smith");

            Professors professors2 = new Professors();
            professors2.setProfessorName("Dr. Jen");

            Professors professors3 = new Professors();
            professors3.setProfessorName("Dr. South");

            // Save professors
            pRepository.save(professors1);
            pRepository.save(professors2);
            pRepository.save(professors3);

            // Associate professors with classes
            classes1.setProfessor(professors1);
            classes2.setProfessor(professors2);
            classes3.setProfessor(professors3);

            // Save classes again to update the associations
            cRepository.save(classes1);
            cRepository.save(classes2);
            cRepository.save(classes3);
        };
    };
}


