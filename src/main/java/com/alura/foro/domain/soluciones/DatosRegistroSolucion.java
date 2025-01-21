package com.alura.foro.domain.soluciones;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroSolucion(@NotNull Long idAutor,
                                    @NotNull Long idTopico,
                                    String answer) {
}
