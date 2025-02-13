package com.adverolt.app_api.controller;

import com.adverolt.app_api.model.Articulo;
import com.adverolt.app_api.service.IArticuloService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    private IArticuloService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Articulo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo> listarPorId(@PathVariable("id") Integer id) {
        Articulo articulo = service.listarPorId(id);
        return articulo != null ? ResponseEntity.ok(articulo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Articulo dto) {
        try {
            Articulo articulo = service.registrar(dto);
            return new ResponseEntity<>(articulo, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar artículo: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable("id") Integer id, @RequestBody Articulo articuloDto) {
        try {
            Articulo actualizado = service.modificar(id, articuloDto);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
