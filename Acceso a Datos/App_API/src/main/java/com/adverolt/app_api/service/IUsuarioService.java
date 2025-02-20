package com.adverolt.app_api.service;

import com.adverolt.app_api.model.Usuario;
import com.adverolt.app_api.model.dto.usuario.UsuarioRequestDto;
import com.adverolt.app_api.model.dto.usuario.UsuarioResponseDto;

import java.util.List;

public interface IUsuarioService {
    List<UsuarioResponseDto> listar();
    UsuarioResponseDto listarPorId(Integer id);
    Usuario registrar(UsuarioRequestDto usuarioDto) throws Exception;
    Usuario modificar(Integer id, UsuarioRequestDto usuarioDto);
    void eliminar(Integer id) throws Exception;

    //Métodos espefíficos de esta entidad
    Boolean comprobarUsuarioCorrecto(String email, String contrasenya) throws Exception;
    Boolean comprobarUsuarioExiste(String email) throws Exception;
    UsuarioResponseDto devolverUsuarioConCorreo(String email) throws Exception;
    Usuario modificarPremium(Integer id);
}
