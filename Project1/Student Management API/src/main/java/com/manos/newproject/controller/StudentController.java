package com.manos.newproject.controller;

import com.manos.newproject.dto.StudentRequestDTO;
import com.manos.newproject.dto.StudentResponseDTO;
import com.manos.newproject.model.Student;
import com.manos.newproject.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public Page<StudentResponseDTO> getAll(@PageableDefault(size = 10, sort = "lastName") Pageable pageable) {
        return studentService.getAllStudents(pageable);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable(value="id") Long studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @GetMapping("/students/search/{lastName}")
    public Page<StudentResponseDTO> searchStudentsByLastname(@PathVariable String lastName, @PageableDefault(size = 5,sort="lastName") Pageable pageable){
        return studentService.searchStudentsByLastname(lastName,pageable);
    }

    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@Valid @RequestBody StudentRequestDTO student) {
        studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student created");
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent (@PathVariable Long id, @Valid @RequestBody StudentRequestDTO student){
      StudentResponseDTO studentResponseDTO =  studentService.updateStudent(student,id);
        return new ResponseEntity<>(studentResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent (@PathVariable Long id){
        String status =studentService.deleteStudent(id);
        return  ResponseEntity.ok("Student deleted successfully");
    }
}
