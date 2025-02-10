package com.adverolt.app_api.model;

import jakarta.persistence.*;

@Entity
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int estrellas;

    @ManyToOne
    @JoinColumn(name = "id_usuario_valora", nullable = false)
    private Usuario usuarioValora;

    @ManyToOne
    @JoinColumn(name = "id_usuario_valorat", nullable = false)
    private Usuario usuarioValorat;

    // Constructor vacío
    public Valoracion() {}

    // Constructor completo
    public Valoracion(Long id, String descripcion, int estrellas, Usuario usuarioValora, Usuario usuarioValorat) {
        this.id = id;
        this.descripcion = descripcion;
        this.estrellas = estrellas;
        this.usuarioValora = usuarioValora;
        this.usuarioValorat = usuarioValorat;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public Usuario getUsuarioValora() {
        return usuarioValora;
    }

    public void setUsuarioValora(Usuario usuarioValora) {
        this.usuarioValora = usuarioValora;
    }

    public Usuario getUsuarioValorat() {
        return usuarioValorat;
    }

    public void setUsuarioValorat(Usuario usuarioValorat) {
        this.usuarioValorat = usuarioValorat;
    }

    @Override
    public String toString() {
        return "Valoracion{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", estrellas=" + estrellas +
                ", usuarioValora=" + usuarioValora +
                ", usuarioValorat=" + usuarioValorat +
                '}';
    }


}
