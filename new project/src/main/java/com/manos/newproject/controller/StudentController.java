package com.manos.newproject.controller;


import com.manos.newproject.model.Student;
import com.manos.newproject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value="id") Long studentId) {
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@Valid @RequestBody Student student) {

            studentService.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student created");
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<String> updateStudent (@PathVariable Long id,
                                                  @Valid @RequestBody Student student){

        studentService.updateStudent(student, id);
        return new ResponseEntity<>("Student with ID: " + id + " updated successfully", HttpStatus.OK);
    }

        @DeleteMapping("/students/{id}")
        public ResponseEntity<Object> deleteStudent (@PathVariable Long id){

           String status =studentService.deleteStudent(id);
           return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
