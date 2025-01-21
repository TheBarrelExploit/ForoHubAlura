package com.alura.foro.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id, String message, String title, String curseName ) {
}
