package com.adverolt.app_api.service;

import com.adverolt.app_api.model.Foto;
import com.adverolt.app_api.repository.FotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FotoService {


    Optional<Foto> obtenerFoto(Integer id);

}
