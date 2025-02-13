package com.adverolt.app_api.controller;

import com.adverolt.app_api.model.Articulo;
import com.adverolt.app_api.model.Categoria;
import com.adverolt.app_api.model.dto.usuario.UsuarioResponseDto;
import com.adverolt.app_api.repository.ICategoriaRespositorio;
import com.adverolt.app_api.repository.IFotoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private ICategoriaRespositorio repo;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {

        List<Categoria> categorias = repo.findAll()
                .stream()
                .map(hotel -> modelMapper.map(hotel, Categoria.class))
                .collect(Collectors.toList());

        // Código 200 OK para select
        return new ResponseEntity<>(categorias, HttpStatus.OK);

    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> listarPorId(@PathVariable Integer idCategoria) {

        Optional<Categoria> op = repo.findById(idCategoria);

        if (op.isPresent()){
            return new ResponseEntity<>(op.get(), HttpStatus.OK);
        }
        // Código 200 OK para select
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
