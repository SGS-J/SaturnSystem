package com.SGSJ.Saturn.model.Empleado;

import javax.persistence.*;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EmpleadoID;
    private String EmpleadoNombre;
    private String Permisos;
    private String Cargo;
    private String EmpleadoUUID;
    private String Contraseña;

    public Empleado(Long empleadoID, String empleadoNombre, String permisos, String cargo, String empleadoUUID, String contraseña) {
        EmpleadoID = empleadoID;
        EmpleadoNombre = empleadoNombre;
        Permisos = permisos;
        Cargo = cargo;
        EmpleadoUUID = empleadoUUID;
        Contraseña = contraseña;
    }

    public Empleado(Long empleadoID, String empleadoNombre, String permisos, String empleadoUUID, String contraseña) {
        EmpleadoID = empleadoID;
        EmpleadoNombre = empleadoNombre;
        Permisos = permisos;
        EmpleadoUUID = empleadoUUID;
        Contraseña = contraseña;
    }

    public Long getEmpleadoID() {
        return EmpleadoID;
    }

    public void setEmpleadoID(Long empleadoID) {
        EmpleadoID = empleadoID;
    }

    public String getEmpleadoNombre() {
        return EmpleadoNombre;
    }

    public void setEmpleadoNombre(String empleadoNombre) {
        EmpleadoNombre = empleadoNombre;
    }

    public String getPermisos() {
        return Permisos;
    }

    public void setPermisos(String permisos) {
        Permisos = permisos;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public String getEmpleadoUUID() {
        return EmpleadoUUID;
    }

    public void setEmpleadoUUID(String empleadoUUID) {
        EmpleadoUUID = empleadoUUID;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
