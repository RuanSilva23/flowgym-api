package com.ruan.workout.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsUsuarioByEmailOrUsuario(String email, String usuario);

    Optional<Usuario> findByUsuario(String usuario);

    Optional<Usuario> findByEmail(String emailUsuario);


}
