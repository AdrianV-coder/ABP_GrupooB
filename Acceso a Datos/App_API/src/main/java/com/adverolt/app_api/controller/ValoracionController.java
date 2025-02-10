package com.adverolt.app_api.controller;

import com.adverolt.app_api.model.Valoracion;
import com.adverolt.app_api.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valoraciones")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    @PostMapping
    public ResponseEntity<Valoracion> insertarValoracion(@RequestBody Valoracion valoracion) {
        Valoracion nuevaValoracion = valoracionService.insertarValoracion(valoracion);
        return ResponseEntity.ok(nuevaValoracion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Integer>> obtenerValoracionesPorUsuario(@PathVariable Integer id) {
        List<Integer> valoraciones = valoracionService.obtenerValoracionesPorUsuario(id);
        return new ResponseEntity<>(valoraciones, HttpStatus.OK);
    }
}
