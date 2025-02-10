package com.adverolt.app_api.service;

import com.adverolt.app_api.model.Articulo;
import com.adverolt.app_api.model.dto.articulo.ArticuloRequestDto;
import com.adverolt.app_api.model.dto.articulo.ArticuloResponseDto;

import java.util.List;

public interface IArticuloService {
    List<ArticuloResponseDto> listar();
    ArticuloResponseDto listarPorId(Integer id);
    Articulo registrar(ArticuloRequestDto articulo) throws Exception;
    Articulo modificar(Integer id, ArticuloRequestDto articuloDto) throws Exception;
    void eliminar(Integer id) throws Exception;

    //Métodos espefíficos de esta entidad
}
