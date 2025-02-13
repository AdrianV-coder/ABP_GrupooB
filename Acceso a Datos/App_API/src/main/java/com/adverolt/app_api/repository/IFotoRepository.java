package com.adverolt.app_api.repository;

import com.adverolt.app_api.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFotoRepository extends JpaRepository<Foto, Integer> {
}
