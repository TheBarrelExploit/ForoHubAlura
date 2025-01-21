package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopicos(Long id, String title, String message, String curseName, LocalDateTime creationDate, String user) {

    public DatosListadoTopicos(Topico topico){
        this(topico.getId(), topico.getTitle(), topico.getMessage(), topico.getCurseName(), topico.getCreationDate(), topico.getUser().getName());
    }
}
