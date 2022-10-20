package com.SGSJ.Saturn.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.ResultSet;

public interface UsuarioCrud extends CrudRepository<Usuario, Long> {
    @Query(value = "CALL actualizar_estado_usuario(?1, ?2)", nativeQuery = true)
    Usuario updateUsuarioState(Long usuarioId, String estado);

    @Query(value = "CALL asignar_vacante(?1, ?2)", nativeQuery = true)
    void setUsuarioVacante(Long usuarioId, Long vacanteId);
}
