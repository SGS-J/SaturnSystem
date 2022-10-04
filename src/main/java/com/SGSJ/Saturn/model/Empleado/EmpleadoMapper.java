package com.SGSJ.Saturn.model.Empleado;

import com.SGSJ.Saturn.domain.Employee.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {
    @Mappings({
            @Mapping(source = "EmpleadoID", target = "employeeId"),
            @Mapping(source = "EmpleadoNombre", target = "name"),
            @Mapping(source = "Permisos", target = "permission"),
            @Mapping(source = "Cargo", target = "post"),
            @Mapping(target = "EmpleadoUUID", ignore = true)
    })
    Employee toEmployee(Empleado empleado);
    List<Employee> toEmployees(List<Empleado> empleadoList);

    @InheritInverseConfiguration
    Empleado toEmpleado(Employee employee);
    List<Empleado> toEmpleados(List<Employee> employeeList);
}
