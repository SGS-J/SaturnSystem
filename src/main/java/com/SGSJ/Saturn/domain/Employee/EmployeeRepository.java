package com.SGSJ.Saturn.domain.Employee;

import com.SGSJ.Saturn.domain.MainDomainRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MainDomainRepository<Employee, Long> {
    void setPermissionById(String permission, Long employeeId);
}
