package com.SGSJ.Saturn.domain.Vacancy;

import com.SGSJ.Saturn.model.Vacante.Vacante;
import com.SGSJ.Saturn.model.Vacante.VacanteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VacancyDTO {
    @Autowired
    private VacanteMapper mapper;

    public Vacancy toVacancy(Vacante vacante) {
       return mapper.toVacancy(vacante);
    }

    public List<Vacancy> toVacancies(List<Vacante> vacantes) {
        return mapper.toVacancies(vacantes);
    }

    public Vacante toVacante(Vacancy vacancy) {
        return mapper.toVacante(vacancy);
    }
    public List<Vacante> toVacantes(List<Vacancy> vacancies) {
        return mapper.toVacantes(vacancies);
    }

}
