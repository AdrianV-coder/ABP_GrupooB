package com.adverolt.app_api.service;

import com.adverolt.app_api.model.Articulo;

import java.util.List;

public interface IArticuloService {
    List<Articulo> listar();
    Articulo listarPorId(Integer id);
    Articulo registrar(Articulo articulo) throws Exception;
    Articulo modificar(Integer id, Articulo articuloDto) throws Exception;
    void eliminar(Integer id) throws Exception;

    //Métodos espefíficos de esta entidad
}
