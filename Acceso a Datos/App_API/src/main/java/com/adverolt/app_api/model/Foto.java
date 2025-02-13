package com.adverolt.app_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String url;

    // Relación con Articulo (Opcional, si cada artículo tiene una sola foto)
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id_articulo", nullable = false)
    private Articulo articulo;

    // CONSTRUCTORES
    public Foto() {
    }

    public Foto(String url, Articulo articulo) {
        this.url = url;
        this.articulo = articulo;
    }

    // GETTERS Y SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
