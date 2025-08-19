package com.school.entity.controller;

import com.school.entity.dto.ClassesDTO;
import com.school.entity.model.Classes;
import com.school.entity.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/school/classes")
    public ResponseEntity<List<ClassesDTO>> getAllClasses() {
        List<ClassesDTO> classDTOs = schoolService.getAllClasses();
        return new ResponseEntity<>(classDTOs, HttpStatus.OK);
    }

    @PostMapping("/school/classes")
    public ResponseEntity<ClassesDTO> addClass(@RequestBody Classes classes) {
        Classes saved = schoolService.addClass(classes);
        ClassesDTO dto = schoolService.mapToDTO(saved);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("admin/school/classes/{classId}")
    public ResponseEntity<String> deleteClass(@PathVariable Long classId) {
        schoolService.deleteClass(classId);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }
}
