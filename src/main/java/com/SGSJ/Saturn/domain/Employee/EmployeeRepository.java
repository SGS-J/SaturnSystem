package com.SGSJ.Saturn.domain.Employee;

import com.SGSJ.Saturn.domain.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends GenericRepository<Employee, Long> {
    void setPermissionById(String permission, Long employeeId);
    Employee logIn();
    void logOut();
}
