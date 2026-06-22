package com.ruan.workout.repository;

import com.ruan.workout.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsUsuarioByEmailOrUsuario(String email, String usuario);

    Usuario findByUsuario(String usuario);
}
