package com.adverolt.app_api.model.dto.usuario;

import jakarta.persistence.Column;

public class UsuarioResponseDto {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String correo;
    private double longitud;
    private double latitud;
    private Boolean premium;

    // CONSTRUCTORES
    public UsuarioResponseDto() {
    }

    public UsuarioResponseDto(Integer id, String nombre, String apellidos, String correo, double longitud, double latitud, Boolean premium) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.longitud = longitud;
        this.latitud = latitud;
        this.premium = premium;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }


    // GETTERS Y SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

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
