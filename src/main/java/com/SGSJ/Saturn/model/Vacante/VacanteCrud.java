package com.SGSJ.Saturn.model.Vacante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VacanteCrud extends CrudRepository<Vacante, Long> {
    @Query(value = "CALL crear_vacante(?1, ?2)", nativeQuery = true)
    Vacante crearVacante(String nombre, String oferta);
}
