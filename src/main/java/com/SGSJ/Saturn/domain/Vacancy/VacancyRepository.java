package com.SGSJ.Saturn.domain.Vacancy;

import com.SGSJ.Saturn.domain.MainDomainRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends MainDomainRepository<Vacancy, Long> {
}
