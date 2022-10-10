package com.SGSJ.Saturn.model.Empleado;

import com.SGSJ.Saturn.domain.Employee.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {
    @Mappings({
            @Mapping(source = "employeeId", target = "empleadoID"),
            @Mapping(source = "name", target = "empleadoNombre"),
            @Mapping(source = "password", target = "contraseña"),
            @Mapping(source = "permission", target = "permisos"),
            @Mapping(source = "post", target = "cargo"),
            @Mapping(target = "empleadoUUID", ignore = true)
    })
    Empleado toEmpleado(Employee employee);
    List<Empleado> toEmpleados(List<Employee> employeeList);

    @InheritInverseConfiguration
    Employee toEmployee(Empleado empleado);
    List<Employee> toEmployees(List<Empleado> empleadoList);
}
