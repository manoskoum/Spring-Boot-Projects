package com.school.entity.util;

import com.school.entity.dto.ProfessorsDTO;
import com.school.entity.model.Classes;
import com.school.entity.model.Professors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ProfessorDtoMapperImpl implements ProfessorDtoMapper{


    @Override
    public ProfessorsDTO toDto(Professors p) {
            if (p == null) return null;

            ProfessorsDTO dto = new ProfessorsDTO();
            dto.setProfessorId(p.getProfessorId());
            dto.setProfessorName(p.getProfessorName());

            List<ProfessorsDTO.ClassDTO> classDTOs =
                    p.getClasses() == null ? List.of()
                            : p.getClasses().stream()
                            .filter(Objects::nonNull)
                            .map(this::toClassDto)
                            .toList();

            dto.setClasses(classDTOs);
            return dto;
        }

        @Override
        public List<ProfessorsDTO> toDtoList(List<Professors> professors) {
            if (professors == null) return List.of();
            return professors.stream().map(this::toDto).toList();
        }

        private ProfessorsDTO.ClassDTO toClassDto(Classes c) {
            ProfessorsDTO.ClassDTO cd = new ProfessorsDTO.ClassDTO();
            cd.setClassesId(c.getClassesId());
            cd.setClassName(c.getClassName());
            return cd;
        }
}
