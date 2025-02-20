package com.adverolt.app_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(name = "fecha_creacion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;

    @ManyToOne()
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToOne(mappedBy = "articulo", cascade = CascadeType.ALL)
    private Foto foto;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToMany(mappedBy = "articulosFavoritos")
    private List<Usuario> usuariosQueFav;

    // CONSTRUCTORES
    public Articulo() {
    }

    public Articulo(String titulo, String descripcion, Double precio, Usuario usuario, Foto foto, Categoria categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaCreacion = LocalDate.now();
        this.usuario = usuario;
        this.foto = foto;
        this.categoria = categoria;
    }

    // GETTERS Y SETTERS

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Double getPrecio() {return precio;}
    public void setPrecio(Double precio) {this.precio = precio;}

    public List<Usuario> getUsuariosQueFav() {
        return usuariosQueFav;
    }

    public void setUsuariosQueFav(List<Usuario> usuariosQueFav) {
        this.usuariosQueFav = usuariosQueFav;
    }
}

