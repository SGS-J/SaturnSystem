package com.SGSJ.Saturn.model.Empleado;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoCrud extends CrudRepository<Empleado, Long> {
    @Query(value = "CALL crear_empleado(?1, ?2, ?3)", nativeQuery = true)
    Empleado createEmpleado(String nombre, String permisos, String cargo);
    @Query(value = "CALL buscar_empleado_por_nombre(?1)", nativeQuery = true)
    Empleado getEmpleadoByNombre(String nombre);
}
