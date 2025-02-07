package com.adverolt.app_api.service;

import com.adverolt.app_api.model.Foto;
import com.adverolt.app_api.repository.FotoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FotoServiceImpl implements FotoService{

    private FotoRepository fotoRepository;

    public Optional<Foto> obtenerFoto(Integer id) {
        return fotoRepository.findById(id);
    }
}
