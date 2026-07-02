package com.ruan.workout.domain.usuario.dto;

import org.jspecify.annotations.NonNull;

public record LoginDTO(@NonNull String usuario,
                       @NonNull String password) {
}
