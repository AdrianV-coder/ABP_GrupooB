package com.adverolt.app_api.service;

import com.adverolt.app_api.model.Valoracion;

import com.adverolt.app_api.repository.IUsuarioRepository;
import com.adverolt.app_api.repository.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    @Autowired
    private ValoracionRepository repo;

    @Autowired
    private IUsuarioRepository repoUsu;

    @Override
    public Valoracion insertarValoracion(Valoracion valoracion) {
        if (valoracion == null) {
            throw new IllegalArgumentException("La valoración no puede ser nula");
        }

        System.out.println("Valoración recibida: " + valoracion);
        System.out.println("Usuario que valora: " + valoracion.getUsuarioValora());
        System.out.println("Usuario valorado: " + valoracion.getUsuarioValorat());

        if (valoracion.getUsuarioValora() == null || valoracion.getUsuarioValorat() == null) {
            throw new IllegalArgumentException("Los usuarios no pueden ser nulos");
        }

        var usuarioValora = repoUsu.findById(valoracion.getUsuarioValora().getId());
        var usuarioValorat = repoUsu.findById(valoracion.getUsuarioValorat().getId());

        if (usuarioValora.isEmpty() || usuarioValorat.isEmpty()) {
            throw new IllegalArgumentException("Uno de los usuarios no existe en la base de datos");
        }

        valoracion.setUsuarioValora(usuarioValora.get());
        valoracion.setUsuarioValorat(usuarioValorat.get());

        return repo.save(valoracion);
    }

    @Override
    public List<Integer> obtenerValoracionesPorUsuario(Integer idUsuario) {
        List<Valoracion> list = repo.findByUsuarioValoratId(idUsuario);
        List<Integer> intVal = new ArrayList<>();

        for (Valoracion valoracion : list){
            intVal.add(valoracion.getEstrellas());
        }
        return intVal;
    }
}

