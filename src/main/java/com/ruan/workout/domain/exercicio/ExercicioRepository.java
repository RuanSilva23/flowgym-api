package com.ruan.workout.domain.exercicio;

import com.ruan.workout.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    Optional<Exercicio> existsByNome(String nome);

    boolean existsByNomeOrUsuarioIsNull(String nome);

    boolean existsByNomeOrUsuarioId(String nome, Long idUsuario);
}
