package com.school.entity.service;

import com.school.entity.model.Classes;
import com.school.entity.model.Students;
import com.school.entity.repository.ClassesRepository;
import com.school.entity.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private ClassesRepository classesRepository;

    public List<Classes> getAllClasses(){
       return classesRepository.findAll();
    }

    public Classes addClass(Classes classes) {
        return classesRepository.save(classes);
    }

    public Classes deleteClass(Long classId) {
        Classes classes=classesRepository.findById(classId)
                .orElseThrow(()->new RuntimeException("Class not found"));
        classesRepository.delete(classes);
        return classes;
    }
}
