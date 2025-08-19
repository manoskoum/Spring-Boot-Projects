package com.school.entity.util;

import com.school.entity.dto.ProfessorsDTO;
import com.school.entity.model.Professors;

import java.util.List;

public interface ProfessorDtoMapper {

    public ProfessorsDTO toDto(Professors p);
    List<ProfessorsDTO> toDtoList(List<Professors> professors);


}
