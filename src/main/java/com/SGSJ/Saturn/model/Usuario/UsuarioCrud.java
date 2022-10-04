package com.SGSJ.Saturn.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioCrud extends CrudRepository<Usuario, Long> {
    @Query(value = "CALL crear_usuario(u.nombre, u.correo, u.hojaVida, u.telefonos)", nativeQuery = true)
    Usuario createUsuario(String nombre, String correo, String hojaVida, String telefonos);

    @Query(value = "CALL actualizar_estado_usuario(u.usuarioId, u.estado)", nativeQuery = true)
    void updateUsuarioState(Long usuarioId, String estado);

    @Query(value = "CALL asignar_vacante(u.usuarioId, u.vacanteId)", nativeQuery = true)
    void setUsuarioVacante(Long usuarioId, Long vacanteId);
}
