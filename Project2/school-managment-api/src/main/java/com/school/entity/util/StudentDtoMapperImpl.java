package com.school.entity.util;

import com.school.entity.dto.ClassesDTO;
import com.school.entity.dto.StudentDTO;
import com.school.entity.model.Classes;
import com.school.entity.model.Students;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class StudentDtoMapperImpl implements StudentDtoMapper{

    @Override
    public StudentDTO toDto(Students s) {
        if (s == null) return null;

        StudentDTO dto = new StudentDTO();
        dto.setStudentId(s.getStudentId());
        dto.setStudentName(s.getStudentName());

        List<StudentDTO.ClassDTO> classDtos =
                s.getClasses() == null ? List.of()
                        : s.getClasses().stream()
                        .filter(Objects::nonNull)
                        .map(this::toClassDto)
                        .toList();

        dto.setClasses(classDtos);
        return dto;
    }

    @Override
    public List<StudentDTO> toDtoList(List<Students> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::toDto)
                .toList();
    }

    private StudentDTO.ClassDTO toClassDto(Classes c) {
        StudentDTO.ClassDTO cd = new StudentDTO.ClassDTO();
        cd.setClassesId(c.getClassesId());
        cd.setClassName(c.getClassName());
        return cd;
    }
}
