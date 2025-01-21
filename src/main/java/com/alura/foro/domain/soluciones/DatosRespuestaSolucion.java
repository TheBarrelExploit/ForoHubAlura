package com.alura.foro.domain.soluciones;

import java.time.LocalDateTime;

public record DatosRespuestaSolucion(Long id, String answer, LocalDateTime creationDate,
                                     Long idAutor, Long idTopico) {
}
