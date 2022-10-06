package com.SGSJ.Saturn.model.Usuario;

import com.SGSJ.Saturn.model.Vacante.Vacante;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UsuarioID;
    private String UsuarioNombre;
    private String Correo;
    private String HojaVidaPath;
    private String Estado;
    @Column(name = "Telefonos",columnDefinition = "json")
    private String Telefonos;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String UsuarioUUID;

    // Foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VacanteID", insertable = false, updatable = false)
    private Vacante UsuarioVacante;

    public Usuario() {
    }

    public Usuario(Long usuarioID, String usuarioNombre, String correo, String hojaVidaPath, String telefonos, Vacante usuarioVacante, String usuarioUUID) {
        UsuarioID = usuarioID;
        UsuarioNombre = usuarioNombre;
        Correo = correo;
        HojaVidaPath = hojaVidaPath;
        Telefonos = telefonos;
        UsuarioVacante = usuarioVacante;
        UsuarioUUID = usuarioUUID;
    }

    public Long getUsuarioID() {
        return UsuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        UsuarioID = usuarioID;
    }

    public String getUsuarioNombre() {
        return UsuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        UsuarioNombre = usuarioNombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getHojaVidaPath() {
        return HojaVidaPath;
    }

    public void setHojaVidaPath(String hojaVidaPath) {
        HojaVidaPath = hojaVidaPath;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getTelefonos() {
        return Telefonos;
    }

    public void setTelefonos(String telefonos) {
        Telefonos = telefonos;
    }

    public Vacante getUsuarioVacante() {
        return UsuarioVacante;
    }

    public void setUsuarioVacante(Vacante usuarioVacante) {
        UsuarioVacante = usuarioVacante;
    }

    public String getUsuarioUUID() {
        return UsuarioUUID;
    }

    public void setUsuarioUUID(String usuarioUUID) {
        UsuarioUUID = usuarioUUID;
    }
}
