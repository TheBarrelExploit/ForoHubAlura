package com.alura.foro.domain.soluciones;


import com.alura.foro.domain.topico.DatosListadoTopicos;
import com.alura.foro.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosListadoSolucion(Long id, String answer, LocalDateTime creationDate, Long idAutor, Long idTopico) {
    public  DatosListadoSolucion(Solucion solucion){
        this(solucion.getId(), solucion.getAnswer(), solucion.getCreationDate(),solucion.getUser().getId(), solucion.getTopico().getId());
    }

}
