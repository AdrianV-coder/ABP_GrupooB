package com.adverolt.app_api.repository;

import com.adverolt.app_api.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRespositorio extends JpaRepository<Categoria, Integer> {
}

