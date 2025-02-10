package com.adverolt.app_api.model;

import jakarta.persistence.*;

@Entity
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] datos;

    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

}
