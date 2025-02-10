package com.adverolt.app_api.repository;

import com.adverolt.app_api.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticuloRepository extends JpaRepository<Articulo, Integer> {
    // Métodos específicos de esta entidad diferentes al CRUD
}
