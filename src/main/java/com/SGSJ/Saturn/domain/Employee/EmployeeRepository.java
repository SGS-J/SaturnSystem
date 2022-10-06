package com.SGSJ.Saturn.domain.Employee;

import com.SGSJ.Saturn.domain.MainDomainRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MainDomainRepository<Employee> {
    void setPermissionById(String permission, Long employeeId);
}
