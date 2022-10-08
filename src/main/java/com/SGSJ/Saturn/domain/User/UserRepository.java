package com.SGSJ.Saturn.domain.User;

import com.SGSJ.Saturn.domain.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User, Long> {
    void updateState(UserState state, Long userId);
    void setVacancy(Long userId, Long vacancyId);
}
