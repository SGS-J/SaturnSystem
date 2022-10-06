package com.SGSJ.Saturn.domain.User;

import com.SGSJ.Saturn.domain.MainDomainRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MainDomainRepository<User, Long> {
    void updateState(String state, Long userId);
    void setVacancy(Long userId, Long vacancyId);
}
