package com.SGSJ.Saturn.model.Vacante;

import com.SGSJ.Saturn.model.Usuario.Usuario;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vacante")
public class Vacante implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VacanteID;
    @Column(name = "vacantenombre")
    private String VacanteNombre;
    private Integer Oferta;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String VacanteUUID;

    // Relationship with FK
    @OneToMany(mappedBy = "UsuarioVacante", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new HashSet<>();

    public Vacante() {
    }

    public Vacante(Long vacanteID, String vacanteNombre, Integer oferta, String vacanteUUID) {
        VacanteID = vacanteID;
        VacanteNombre = vacanteNombre;
        Oferta = oferta;
        VacanteUUID = vacanteUUID;
    }

    public Long getVacanteID() {
        return VacanteID;
    }

    public void setVacanteID(Long vacanteID) {
        VacanteID = vacanteID;
    }

    public String getVacanteNombre() {
        return VacanteNombre;
    }

    public void setVacanteNombre(String vacanteNombre) {
        VacanteNombre = vacanteNombre;
    }

    public Integer getOferta() {
        return Oferta;
    }

    public void setOferta(Integer oferta) {
        Oferta = oferta;
    }

    public String getVacanteUUID() {
        return VacanteUUID;
    }

    public void setVacanteUUID(String vacanteUUID) {
        VacanteUUID = vacanteUUID;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
