package com.SGSJ.Saturn.domain.Vacancy;

import com.SGSJ.Saturn.model.Vacante.Vacante;
import com.SGSJ.Saturn.model.Vacante.VacanteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyService implements VacancyRepository{
    @Autowired
    private VacancyDTO vacancyDTO;
    @Autowired
    private VacanteCrud vacanteCrud;

    @Override
    public List<Vacancy> getAll() {
        return vacancyDTO.toVacancies((List<Vacante>) vacanteCrud.findAll());
    }

    @Override
    public Vacancy getById(Long ID) {
        return vacancyDTO.toVacancy(vacanteCrud.findById(ID).get());
    }

    @Override
    public Vacancy add(Vacancy newVacancy) {
        return vacancyDTO.toVacancy(vacanteCrud.save(vacancyDTO.toVacante(newVacancy)));
    }

    @Override
    public Vacancy updateById(Vacancy newVacancy, Long ID) {
        Vacancy oldVacancy = this.getById(ID);

        oldVacancy.setName((newVacancy.getName() == null ? oldVacancy : newVacancy).getName());
        oldVacancy.setJobOffer((newVacancy.getJobOffer() == null ? oldVacancy : newVacancy).getJobOffer());

        return vacancyDTO.toVacancy(vacanteCrud.save(vacancyDTO.toVacante(oldVacancy)));
    }

    @Override
    public void deleteById(Long ID) {
        vacanteCrud.deleteById(ID);
    }
}
