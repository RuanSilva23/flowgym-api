package com.ruan.workout.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import org.jspecify.annotations.NonNull;

public record UsuarioDTO(@NonNull String name,
                         @NonNull @Email String email,
                         @NonNull String usuario,
                         @NonNull String password) {
}
