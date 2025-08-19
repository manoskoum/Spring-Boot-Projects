package com.school.entity.util;

import com.school.entity.dto.ClassesDTO;
import com.school.entity.dto.StudentDTO;
import com.school.entity.model.Classes;
import com.school.entity.model.Students;

import java.util.List;

public interface StudentDtoMapper {

    StudentDTO toDto(Students entity);
    List<StudentDTO> toDtoList(List<Students> entities);
}
