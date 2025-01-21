package com.alura.foro.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String userName,
        @NotBlank
        String password) {

}
