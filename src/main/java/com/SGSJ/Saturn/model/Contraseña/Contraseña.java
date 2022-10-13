package com.SGSJ.Saturn.model;

import javax.persistence.*;

@Entity
@Table(name = "contraseña")
public class Contraseña {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ContraseñaID;
    private String Encriptado;
    private String Hash;
    private String Sal;

    public Contraseña(Long contraseñaID, String encriptado, String hash, String sal) {
        ContraseñaID = contraseñaID;
        Encriptado = encriptado;
        Hash = hash;
        Sal = sal;
    }

    public Long getContraseñaID() {
        return ContraseñaID;
    }

    public void setContraseñaID(Long contraseñaID) {
        ContraseñaID = contraseñaID;
    }

    public String getEncriptado() {
        return Encriptado;
    }

    public void setEncriptado(String encriptado) {
        Encriptado = encriptado;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String hash) {
        Hash = hash;
    }

    public String getSal() {
        return Sal;
    }

    public void setSal(String sal) {
        Sal = sal;
    }
}
