package com.SGSJ.Saturn.domain.User;

import java.util.List;

public class UserService implements UserRepository{
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(Long ID) {
        return null;
    }

    @Override
    public User add(User objectType) {
        return null;
    }

    @Override
    public User updateById(User objectType, Long ID) {
        return null;
    }

    @Override
    public void deleteById(Long ID) {

    }

    @Override
    public void updateState(String state, Long userId) {

    }

    @Override
    public void setVacancy(Long userId, Long vacancyId) {

    }
}
