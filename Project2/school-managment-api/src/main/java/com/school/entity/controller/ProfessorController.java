package com.school.entity.controller;

import com.school.entity.dto.ProfessorsDTO;
import com.school.entity.model.Professors;
import com.school.entity.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorsDTO>> getAllProfessors(){
        return new ResponseEntity<>(professorService.getAllProfessors(), HttpStatus.OK);
    }

    @GetMapping("/{professorId}")
    public ResponseEntity<ProfessorsDTO> getProfessorById(@PathVariable Long professorId){
        return new ResponseEntity<>(professorService.getProfessorById(professorId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessorsDTO> addProfessors(@RequestBody Professors professors){
        return new ResponseEntity<>(professorService.addProfessors(professors),HttpStatus.CREATED);
    }

    @PutMapping("/{professorId}")
    public ResponseEntity<ProfessorsDTO> updateProfessors(@PathVariable Long professorId,
                                                       @RequestBody Professors professors){
        return new ResponseEntity<>(professorService.updateProfessors(professorId,professors),HttpStatus.OK);

    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity<String> deleteProfessors(@PathVariable Long professorId){
        professorService.deleteProfessors(professorId);
        return ResponseEntity.ok("Professor deleted successfully");
    }


}
