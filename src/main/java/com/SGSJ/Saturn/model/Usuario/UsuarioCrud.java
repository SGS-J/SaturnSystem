package com.SGSJ.Saturn.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrud extends CrudRepository<Usuario, Long> {
    @Query(value = "CALL crear_usuario(?1, ?2, ?3, ?4)", nativeQuery = true)
    Usuario createUsuario(String nombre, String correo, String hojaVida, String telefonos);

    @Query(value = "CALL actualizar_estado_usuario(?1, ?2)", nativeQuery = true)
    void updateUsuarioState(Long usuarioId, String estado);

    @Query(value = "CALL asignar_vacante(?1, ?2)", nativeQuery = true)
    void setUsuarioVacante(Long usuarioId, Long vacanteId);
}
