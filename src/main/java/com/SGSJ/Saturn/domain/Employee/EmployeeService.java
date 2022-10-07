package com.SGSJ.Saturn.domain.Employee;

import com.SGSJ.Saturn.model.Empleado.Empleado;
import com.SGSJ.Saturn.model.Empleado.EmpleadoCrud;
import com.SGSJ.Saturn.security.PasswordCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService implements EmployeeRepository{
    @Autowired
    private EmployeeDTO employeeDTO;
    @Autowired
    private EmpleadoCrud empleadoCrud;

    @Override
    public void setPermissionById(String permission, Long employeeId) {
        Employee employee = this.getById(employeeId);
        employee.setPermission(permission);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDTO.toEmployees((List<Empleado>) empleadoCrud.findAll());
    }

    @Override
    public Employee getById(Long ID) {
        return employeeDTO.toEmployee(empleadoCrud.findById(ID).get());
    }

    @Override
    synchronized public Employee add(Employee newEmployee) {
        String salt = PasswordCrypt.getSalt(10);
        String hash = PasswordCrypt.generateSecurePassword(newEmployee.getPassword(), salt);
        newEmployee.setPassword(hash);
        return employeeDTO.toEmployee(empleadoCrud.save(employeeDTO.toEmpleado(newEmployee)));
    }

    @Override
    synchronized public Employee updateById(Employee newEmployee, Long ID) {
        Employee oldEmployee = this.getById(ID);
        String permission = (newEmployee.getPermission() == null ? oldEmployee : newEmployee).getPermission();

        oldEmployee.setName((newEmployee.getName() == null ? oldEmployee : newEmployee).getName());
        oldEmployee.setPost((newEmployee.getPost() == null ? oldEmployee : newEmployee).getPost());

        this.setPermissionById(permission, ID);

        if (newEmployee.getPassword() != null) {
            String salt = PasswordCrypt.getSalt(10);
            String hash = PasswordCrypt.generateSecurePassword(newEmployee.getPassword(), salt);
            oldEmployee.setPassword(hash);
        }

        return employeeDTO.toEmployee(empleadoCrud.save(employeeDTO.toEmpleado(oldEmployee)));
    }

    @Override
    public void deleteById(Long ID) {
        empleadoCrud.deleteById(ID);
    }
}
