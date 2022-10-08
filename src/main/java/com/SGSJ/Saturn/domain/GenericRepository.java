package com.SGSJ.Saturn.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenericRepository<T extends Object, IDType> {
    List<T> getAll();
    T getById(IDType ID);
    T add(T objectType);
    T updateById(T objectType, IDType ID);
    void deleteById(IDType ID);
}
