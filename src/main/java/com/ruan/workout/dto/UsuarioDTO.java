package com.ruan.workout.dto;

public record UsuarioDTO(String usuario,
                         String password,
                         String name,
                         String email,
                         Integer idade) {
}
