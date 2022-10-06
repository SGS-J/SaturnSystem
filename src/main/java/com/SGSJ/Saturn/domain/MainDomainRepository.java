package com.SGSJ.Saturn.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainDomainRepository<T> {
    List<T> getAll();
    <Data extends T> Data getById(Long ID);
    <Data extends T> Data add(Long ID);
    <Data extends T> Data updateById(Long ID);
    void deleteById(Long userId);
}
