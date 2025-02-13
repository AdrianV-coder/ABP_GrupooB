package com.adverolt.app_api.repository;

import com.adverolt.app_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Métodos específicos de esta entidad diferentes al CRUD
    // @Query("FROM Usuario u WHERE u.correo LIKE :correo AND u.contrasena LIKE :contrasena")
    // Usuario findByEmailAndPassword(@Param("correo") String correo, @Param("contrasena") String contrasena);

    //metodo que retorne bool si existe el correo del usuario em la base de datos
    Usuario findByCorreoAndContrasena( String correo,  String contrasena);

    @Query("FROM Usuario u WHERE u.correo LIKE :correo")
    Usuario findByEmail(@Param("correo") String correo);

    //List<Integer> getArticulosFavoritos
    //Articulo asignarIdArticuloFavorito
    //bool esArticuloFavorito(Integer idaArticulo)
}
