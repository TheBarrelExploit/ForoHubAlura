package com.alura.foro.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(@NotNull Long idAutor,
                                  String message,
                                  String title,
                                  String curseName) {
}
