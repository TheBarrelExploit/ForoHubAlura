package com.alura.foro.controller;

import com.alura.foro.domain.soluciones.*;
import com.alura.foro.domain.topico.*;
import com.alura.foro.domain.usuarios.Usuario;
import com.alura.foro.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/solucion")
public class SolucionController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private SolucionRepository solucionRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaSolucion> registrarTopico(@RequestBody @Valid DatosRegistroSolucion datosRegistroSolucion, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroSolucion.idAutor());
        Topico topico = topicoRepository.getReferenceById(datosRegistroSolucion.idTopico());
        Solucion solucion = new Solucion(datosRegistroSolucion,usuario,topico);
        solucionRepository.save(solucion);
        DatosRespuestaSolucion datosRespuestaSolucion = new DatosRespuestaSolucion(solucion.getId(),solucion.getAnswer(),solucion.getCreationDate(),solucion.getUser().getId(),solucion.getTopico().getId());
        URI url = uriComponentsBuilder.path("/solucion/{id}").buildAndExpand(solucion.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaSolucion);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoSolucion>> listadoSoluciones(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(solucionRepository.findAll(pageable).map(DatosListadoSolucion::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarSolucion datosActualizarSolucion){
        Solucion solucion = solucionRepository.getReferenceById(datosActualizarSolucion.id());
        solucion.actualizarAnswer(datosActualizarSolucion);
        return ResponseEntity.ok(new DatosRespuestaSolucion(solucion.getId(), solucion.getAnswer(),
                solucion.getCreationDate(),solucion.getUser().getId(),solucion.getTopico().getId()));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id){
        Optional<Solucion> solution = solucionRepository.findById(id);

        if (!solution.isPresent()){
            return ResponseEntity.notFound().build();
        }

        solucionRepository.delete(solution.get());
        return ResponseEntity.noContent().build();
    }



}
