package com.SGSJ.Saturn.domain.Employee;

import com.SGSJ.Saturn.model.Empleado.Empleado;
import com.SGSJ.Saturn.model.Empleado.EmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDTO {
    @Autowired
    private EmpleadoMapper mapper;

    public Employee toEmployee(Empleado empleado) {
        return mapper.toEmployee(empleado);
    }

    public List<Employee> toEmployees(List<Empleado> empleados) {
        return mapper.toEmployees(empleados);
    }

    public Empleado toEmpleado(Employee employee) {
        return mapper.toEmpleado(employee);
    }

    public List<Empleado> toEmpleados(List<Employee> employees) {
        return mapper.toEmpleados(employees);
    }
}
