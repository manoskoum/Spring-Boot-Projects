package com.school.entity.service;

import com.school.entity.dto.ProfessorsDTO;
import com.school.entity.model.Classes;
import com.school.entity.model.Professors;
import com.school.entity.repository.ClassesRepository;
import com.school.entity.repository.ProfessorRepository;
import com.school.entity.util.ProfessorDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private ProfessorDtoMapper professorDtoMapper;


    public List<ProfessorsDTO> getAllProfessors() {
        return professorDtoMapper.toDtoList(professorRepository.findAll());

    }

    public ProfessorsDTO getProfessorById(Long professorId) {
        return professorRepository.findById(professorId)
                .map(professorDtoMapper::toDto)
    .orElseThrow(()-> new RuntimeException("Professor with id :" +professorId + " not found ") );
    }

    public ProfessorsDTO addProfessors(Professors professors) {
        if (professors.getClasses() != null) {
            for (Classes cls : professors.getClasses()) {

                // Αν προσπαθείς να αναθέσεις υπάρχον μάθημα (έχει id), έλεγξε αν έχει ήδη καθηγητή
                if (cls.getClassesId() != null) {
                    Classes existing = classesRepository.findById(cls.getClassesId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Class not found"));

                    if (existing.getProfessor() != null) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT,
                                "Class '" + existing.getClassName() + "' is already assigned to a professor.");
                    }
                }


                cls.setProfessor(professors);
            }
        }

        Professors savedProfessor = professorRepository.save(professors);
        return professorDtoMapper.toDto(savedProfessor);
    }


    public ProfessorsDTO updateProfessors(Long professorId, Professors professors) {
        Professors existing = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        existing.setProfessorName(professors.getProfessorName());
        return professorDtoMapper.toDto(professorRepository.save(existing));

    }

    public void deleteProfessors(Long professorId) {
        Professors professors=professorRepository.findById(professorId)
                .orElseThrow(()-> new RuntimeException("Professor with id :" +professorId + " not found ") );


        for (Classes cls : professors.getClasses()) {
            cls.setProfessor(null);
            classesRepository.save(cls);
        }

        professors.getClasses().clear();

        professorRepository.delete(professors);

    }
}

