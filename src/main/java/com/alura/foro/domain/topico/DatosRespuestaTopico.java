package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(Long id, String curseName, String title, String message ,
                                   LocalDateTime creationDater, Long idAutor) {
}
