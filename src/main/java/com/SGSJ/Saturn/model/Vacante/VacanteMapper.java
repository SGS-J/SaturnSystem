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
            @Mapping(source = "VacanteID", target = "vacancyId"),
            @Mapping(source = "VacanteNombre", target = "name"),
            @Mapping(source = "Oferta", target = "jobOffer"),
            @Mapping(target = "VacanteUUID", ignore = true),
            @Mapping(target = "usuarios", ignore = true),
    })
    Vacancy toVacancy(Vacante vacante);
    List<Vacancy> toVacancies(List<Vacante> vacanteList);

    @InheritInverseConfiguration
    Vacante toVacante(Vacancy vacancy);
    List<Vacante> toVacantes(List<Vacancy> vacancyList);
}
