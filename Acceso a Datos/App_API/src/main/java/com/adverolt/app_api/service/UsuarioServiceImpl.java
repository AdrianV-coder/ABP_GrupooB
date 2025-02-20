package com.adverolt.app_api.service;

import com.adverolt.app_api.model.Usuario;
import com.adverolt.app_api.model.dto.usuario.UsuarioRequestDto;
import com.adverolt.app_api.model.dto.usuario.UsuarioResponseDto;
import com.adverolt.app_api.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UsuarioResponseDto> listar() {
        return repository.findAll().stream()
                .map(habitacion -> modelMapper.map(habitacion, UsuarioResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDto listarPorId(Integer id) {
        Optional<Usuario> usuario = repository.findById(id);
        return usuario.map(value -> modelMapper.map(value, UsuarioResponseDto.class))
                .orElse(null);
    }

    @Transactional
    @Override
    public Usuario registrar(UsuarioRequestDto usuariodto) throws Exception {
        Usuario usuario = modelMapper.map(usuariodto, Usuario.class);
        usuario.setPremium(false);
        if (repository.findByEmail(usuario.getCorreo())!=null){
            throw new Exception("ya existe un usuario con este correo");
        }
        return repository.save(usuario);
    }

    @Override
    public Usuario modificar(Integer id, UsuarioRequestDto usuariodto) {
        Optional<Usuario> op = repository.findById(id);
        if (op.isPresent()) {
            Usuario usuario = modelMapper.map(usuariodto, Usuario.class);
            usuario.setId(op.get().getId());
            repository.save(usuario);
            return usuario;
        }
        return null;
    }

    @Override
    public Usuario modificarPremium(Integer id) {
        Optional<Usuario> op = repository.findById(id);
        if (op.isPresent()) {
            Usuario usuario = op.get();
            usuario.setPremium(true);
            usuario.setId(op.get().getId());
            repository.save(usuario);
            return usuario;
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) throws Exception {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new Exception("No existe el usuario");
        }
    }

    @Override
    public Boolean comprobarUsuarioCorrecto(String email, String contrasenya) throws Exception {
        Usuario usuario = repository.findByCorreoAndContrasena(email, contrasenya);
        return usuario != null;
    }

    @Override
    public Boolean comprobarUsuarioExiste(String email) throws Exception {
        Usuario usuario = repository.findByEmail(email);
        return usuario != null;
    }

    @Override
    public UsuarioResponseDto devolverUsuarioConCorreo(String email) throws Exception {
        UsuarioResponseDto usuario = modelMapper.map(repository.findByEmail(email), UsuarioResponseDto.class);
        return usuario;
    }


}
