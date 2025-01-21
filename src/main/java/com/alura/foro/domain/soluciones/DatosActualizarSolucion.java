package com.alura.foro.domain.soluciones;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarSolucion(@NotNull Long id, String answer) {
}
