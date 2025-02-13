package com.adverolt.app_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private double longitud;

    @Column(nullable = false)
    private double latitud;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "favoritos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "articulo_id")
    )
    private List<Articulo> articulosFavoritos;

    // CONSTRUCTORES
    public Usuario() {
    }
    public Usuario(String nombre, String apellidos, String correo, String contrasena, double longitud, double latitud) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    // GETTERS Y SETTERS
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public List<Articulo> getArticulosFavoritos() {
        return articulosFavoritos;
    }

    public void setArticulosFavoritos(List<Articulo> articulosFavoritos) {
        this.articulosFavoritos = articulosFavoritos;
    }
}

/*@Embeddable // MARCA LA CLASE PARA QUE SE PUEDA USAR DENTRO DE OTRA ENTIDAD
class Ubicacion {
    private double longitud;
    private double latitud;

    // CONSTRUCTORES
    public Ubicacion() {
    }
    public Ubicacion(double longitud, double latitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    // GETTERS Y SETTERS
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
}*/
