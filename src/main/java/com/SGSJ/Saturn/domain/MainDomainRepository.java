package com.SGSJ.Saturn.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainDomainRepository<T> {
    List<T> getAll();
    T getById(Long ID);
    T add(Long ID);
    T updateById(Long ID);
    void deleteById(Long ID);
}
