package com.alura.foro.controller;

import com.alura.foro.domain.topico.*;
import com.alura.foro.domain.usuarios.Usuario;
import com.alura.foro.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topico")
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class TopicoController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico>registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
           Usuario usuario = usuarioRepository.getReferenceById(datosRegistroTopico.idAutor());
           Topico topico = new Topico(datosRegistroTopico,usuario);
           topicoRepository.save(topico);
           DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),topico.getCurseName(),topico.getTitle(),topico.getMessage(),topico.getCreationDate(),topico.getUser().getId());
           URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(usuario.getId()).toUri();
           return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos(@PageableDefault(size = 10)Pageable pageable){
        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(DatosListadoTopicos::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopicos(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getCurseName(),
                                  topico.getTitle(),topico.getMessage(),
                                  topico.getCreationDate(),topico.getUser().getId()));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id){
        Optional<Topico> topics = topicoRepository.findById(id);

        if (!topics.isPresent()){
            return ResponseEntity.notFound().build();
        }

        topicoRepository.delete(topics.get());
        return ResponseEntity.noContent().build();
    }

}
