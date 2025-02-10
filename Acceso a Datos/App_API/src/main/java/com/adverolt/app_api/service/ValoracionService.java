package com.adverolt.app_api.service;

import com.adverolt.app_api.model.Valoracion;

import java.util.List;

public interface ValoracionService {
    Valoracion insertarValoracion(Valoracion valoracion);
    List<Integer> obtenerValoracionesPorUsuario(Integer idUsuario);
}
