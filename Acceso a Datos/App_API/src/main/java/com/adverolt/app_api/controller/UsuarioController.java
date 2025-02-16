package com.adverolt.app_api.controller;

import com.adverolt.app_api.model.Articulo;
import com.adverolt.app_api.model.Usuario;
import com.adverolt.app_api.model.dto.usuario.UsuarioRequestDto;
import com.adverolt.app_api.model.dto.usuario.UsuarioResponseDto;
import com.adverolt.app_api.repository.IArticuloRepository;
import com.adverolt.app_api.repository.IUsuarioRepository;
import com.adverolt.app_api.service.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IArticuloRepository articuloRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar() {
        List<UsuarioResponseDto> hoteles = service.listar()
                .stream()
                .map(hotel -> modelMapper.map(hotel, UsuarioResponseDto.class))
                .collect(Collectors.toList());

        // Código 200 OK para select
        return new ResponseEntity<>(hoteles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> listarPorId(@PathVariable("id") Integer id){
        UsuarioResponseDto usuario = service.listarPorId(id);
        if (usuario == null) {
            // Código 404 No encontrado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Código 200 OK para select
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> registrar(@RequestBody UsuarioRequestDto usuario) {

        Usuario usuario1 = service.registrar(usuario);
        if (usuario1 == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(usuario1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificar(@PathVariable Integer id, @RequestBody UsuarioRequestDto usuario) {
        // Código 200 OK para select
        return new ResponseEntity<>(service.modificar(id, usuario), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) {
        try {
            service.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    ////////////////////////////////////////
    /// Métods espefícos de esta entidad ///
    ////////////////////////////////////////

    @GetMapping("/comprobarUsuarioCorrecto")
    public ResponseEntity<Boolean> comprobarUsuarioCorrecto(@Param("correo") String correo, @Param("contrasena") String contrasena) throws Exception {
        return new ResponseEntity<>(service.comprobarUsuarioCorrecto(correo, contrasena), HttpStatus.OK);
    }

    @GetMapping("/comprobarUsuarioExiste")
    public ResponseEntity<Boolean> comprobarUsuarioExiste(@Param("correo") String correo) throws Exception {
        return new ResponseEntity<>(service.comprobarUsuarioExiste(correo), HttpStatus.OK);
    }

    @GetMapping("/correo")
    public ResponseEntity<UsuarioResponseDto> devolverUsuarioConCorreo(@Param("correo") String correo) throws Exception {
        UsuarioResponseDto usuario = service.devolverUsuarioConCorreo(correo);

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    ////////////////////////////////////////
         /// Metodos de favoritos ///
    ////////////////////////////////////////
    ///
    // Agregar artículo a favoritos
         @PostMapping("/{usuarioId}/favoritos/{articuloId}")
         public ResponseEntity<?> agregarFavorito(@PathVariable Integer usuarioId, @PathVariable Integer articuloId) {
             try {
                 Usuario usuario = usuarioRepository.findById(usuarioId)
                         .orElseThrow(() -> new RuntimeException("❌ Usuario con ID " + usuarioId + " no encontrado."));
                 Articulo articulo = articuloRepository.findById(articuloId)
                         .orElseThrow(() -> new RuntimeException("❌ Artículo con ID " + articuloId + " no encontrado."));

                 usuario.getArticulosFavoritos().add(articulo);
                 usuarioRepository.saveAndFlush(usuario);

                 return ResponseEntity.ok("✅ Artículo con ID " + articuloId + " agregado a favoritos del usuario " + usuarioId);
             } catch (Exception e) {
                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                         .body("⚠️ Error al agregar favorito: " + e.getMessage());
             }
         }

    @DeleteMapping("/{usuarioId}/favoritos/{articuloId}")
    public ResponseEntity<?> eliminarFavorito(@PathVariable Integer usuarioId, @PathVariable Integer articuloId) {
        try {
            Usuario usuario = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("❌ Usuario con ID " + usuarioId + " no encontrado."));
            Articulo articulo = articuloRepository.findById(articuloId)
                    .orElseThrow(() -> new RuntimeException("❌ Artículo con ID " + articuloId + " no encontrado."));

            boolean eliminado = usuario.getArticulosFavoritos().removeIf(a -> a.getId().equals(articuloId));

            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("⚠️ El artículo con ID " + articuloId + " no estaba en la lista de favoritos del usuario " + usuarioId);
            }

            usuarioRepository.saveAndFlush(usuario);

            return ResponseEntity.ok("✅ Artículo con ID " + articuloId + " eliminado de favoritos del usuario " + usuarioId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("⚠️ Error al eliminar favorito: " + e.getMessage());
        }
    }

}
