package com.adverolt.app_api.model.dto.valoracion;

import com.adverolt.app_api.model.Usuario;


public class ValoracionRequestDTO {
    private String descripcion;
    private int estrellas;
    private Integer usuarioValora;
    private Integer usuarioValorat;

    public ValoracionRequestDTO(String descripcion, int estrellas, Integer usuarioValora, Integer usuarioValorat) {
        this.descripcion = descripcion;
        this.estrellas = estrellas;
        this.usuarioValora = usuarioValora;
        this.usuarioValorat = usuarioValorat;
    }

    public ValoracionRequestDTO() {
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

    public Integer getUsuarioValora() {
        return usuarioValora;
    }

    public void setUsuarioValora(Integer usuarioValora) {
        this.usuarioValora = usuarioValora;
    }

    public Integer getUsuarioValorat() {
        return usuarioValorat;
    }

    public void setUsuarioValorat(Integer usuarioValorat) {
        this.usuarioValorat = usuarioValorat;
    }
}
