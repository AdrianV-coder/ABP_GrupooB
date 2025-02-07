package com.adverolt.app_api.repository;

import com.adverolt.app_api.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {

    List<Valoracion> findByUsuarioValoratId(Integer idUsuario);
}
