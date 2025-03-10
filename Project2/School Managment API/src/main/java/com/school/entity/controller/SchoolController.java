package com.school.entity.controller;

import com.school.entity.model.Classes;
import com.school.entity.model.Students;
import com.school.entity.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/school/classes")
    public ResponseEntity<List<Classes>> getAllClasses() {
        return new ResponseEntity<>( schoolService.getAllClasses(), HttpStatus.OK);
    }

    @PostMapping("/school/classes")
    public ResponseEntity<Classes> addClass(@RequestBody Classes classes) {
        return new ResponseEntity<>(schoolService.addClass(classes),HttpStatus.CREATED);
    }

    @DeleteMapping("admin/school/classes/{classId}")
    public ResponseEntity<String> deleteClass(@PathVariable Long classId) {
        schoolService.deleteClass(classId);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }
}
