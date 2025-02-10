package com.adverolt.app_api.model.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class UsuarioRequestDto {
    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellidos;

    @Email
    private String correo;

    @Pattern(regexp = "^[a-zA-Z0-9]{0,10}$")
    private String contrasena;

    //private Ubicacion ubicacion;

    private double longitud;
    private double latitud;

    // CONSTRUCTORES
    public UsuarioRequestDto() {
    }
    public UsuarioRequestDto(String nombre, String apellidos, String correo, String contrasena, double longitud, double latitud) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    // GETTERS Y SETTERS
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
