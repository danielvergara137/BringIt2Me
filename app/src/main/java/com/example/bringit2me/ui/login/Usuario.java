package com.example.bringit2me.ui.login;

import java.io.Serializable;

/**
 * Class exposing authenticated user details to the UI.
 */
public class Usuario implements Serializable {
    private String displayName;
    private String nombre;
    private String correo;
    private int telefono;
    private String rut;
    private String sexo;
    //... other data fields that may be accessible to the UI

    public Usuario(String displayName, String nombre, String correo, int telefono, String rut, String sexo) {
        this.displayName = displayName;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.rut = rut;
        this.sexo = sexo;
    }

    public String getDisplayName() {
        return displayName;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public int getTelefono() { return telefono; }
    public String getRut() {
        return rut;
    }
    public String getSexo() {
        return sexo;
    }
}
