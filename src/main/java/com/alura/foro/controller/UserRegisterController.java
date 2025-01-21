package com.alura.foro.controller;


import com.alura.foro.domain.usuarios.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRegisterController {
        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @PostMapping()
        public ResponseEntity<DatosRespuestaUsuario>registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder){

            String encodedPassword = passwordEncoder.encode(datosRegistroUsuario.password());
            Usuario usuario = new Usuario((datosRegistroUsuario));
            usuario.setPassword(encodedPassword);
            usuarioRepository.save(usuario);
            DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getName(), usuario.getUsername());
            URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(url).body(datosRespuestaUsuario);
        }

        @PutMapping
        @Transactional
        public ResponseEntity actualizarDatos(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
            Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
            usuario.actualizarDatos(datosActualizarUsuario);
            return ResponseEntity.ok(new DatosRespuestaUsuario(usuario.getId(),
                    usuario.getUsername(), usuario.getName()));
        }

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
            Optional<Usuario> usuario = usuarioRepository.findById(id);

            if(!usuario.isPresent()){
                return ResponseEntity.notFound().build();
            }
            usuarioRepository.delete(usuario.get());
            return ResponseEntity.noContent().build();
        }
}
