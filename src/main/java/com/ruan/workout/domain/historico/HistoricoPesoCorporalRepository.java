package com.ruan.workout.domain.historico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoricoPesoCorporalRepository extends JpaRepository<HistoricoPesoCorporal, Long> {
    List<HistoricoPesoCorporal> findByUsuarioIdOrderByDataRegistroAsc(Long idUsuario);

    Optional<HistoricoPesoCorporal> findFirstByUsuarioIdOrderByDataRegistroAsc(Long idUsuario);
}
