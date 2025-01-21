package com.alura.foro.domain.usuarios;


import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(@NotNull Long id,
                                     String name,
                                     String userName,
                                     String password) {
}
