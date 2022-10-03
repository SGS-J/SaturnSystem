package com.SGSJ.Saturn.model.Empleado;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoCrud extends CrudRepository<Empleado, Long> {
    @Query(value = "CALL crear_empleado(u.nombre, u.permisos, u.cargo)", nativeQuery = true)
    Empleado createEmployee(String nombre, String permisos, String cargo);
}
