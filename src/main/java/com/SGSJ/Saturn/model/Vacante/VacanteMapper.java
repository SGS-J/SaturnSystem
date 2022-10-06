package com.SGSJ.Saturn.model.Vacante;

import com.SGSJ.Saturn.domain.Vacancy.Vacancy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VacanteMapper {
    @Mappings({
            @Mapping(source = "vacancyId", target = "vacanteID"),
            @Mapping(source = "name", target = "vacanteNombre"),
            @Mapping(source = "jobOffer", target = "oferta"),
            @Mapping(target = "vacanteUUID", ignore = true),
            @Mapping(target = "usuarios", ignore = true),
    })
    Vacante toVacante(Vacancy vacancy);
    List<Vacante> toVacantes(List<Vacancy> vacancyList);

    @InheritInverseConfiguration
    Vacancy toVacancy(Vacante vacante);
    List<Vacancy> toVacancies(List<Vacante> vacanteList);
}
