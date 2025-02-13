package com.adverolt.app_api.service;

import com.adverolt.app_api.model.Articulo;
import com.adverolt.app_api.model.Usuario;
import com.adverolt.app_api.repository.IArticuloRepository;
import com.adverolt.app_api.repository.IUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloServiceImpl implements IArticuloService {

    @Autowired
    private IArticuloRepository repository;

    @Autowired
    private IUsuarioRepository repoUsu;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Articulo> listar() {
        return repository.findAll().stream()
                .map(articulo -> modelMapper.map(articulo, Articulo.class))
                .collect(Collectors.toList());
    }

    @Override
    public Articulo listarPorId(Integer id) {
        return repository.findById(id)
                .map(articulo -> modelMapper.map(articulo, Articulo.class))
                .orElse(null);
    }

    @Override
    @Transactional
    public Articulo registrar(Articulo articulodto) throws Exception {
        Usuario usuario = repoUsu.findById(articulodto.getUsuario().getId())
                .orElseThrow(() -> new Exception("El usuario con ID " + articulodto.getUsuario().getId() + " no existe."));

        Articulo articulo = modelMapper.map(articulodto, Articulo.class);
        articulo.setUsuario(usuario);

        return repository.save(articulo);
    }

    @Override
    @Transactional
    public Articulo modificar(Integer id, Articulo articuloDto) throws Exception {
        Articulo articuloExistente = repository.findById(id)
                .orElseThrow(() -> new Exception("El artículo con ID " + id + " no existe."));

        // Mantener el ID y el usuario original
        articuloExistente.setTitulo(articuloDto.getTitulo());
        articuloExistente.setDescripcion(articuloDto.getDescripcion());
        articuloExistente.setFechaCreacion(articuloDto.getFechaCreacion());

        return repository.save(articuloExistente);
    }


    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
