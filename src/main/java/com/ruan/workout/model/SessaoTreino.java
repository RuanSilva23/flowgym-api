package com.ruan.workout.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class SessaoTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    public SessaoTreino() {}

    public SessaoTreino(Usuario usuario) {
        this.usuario = usuario;
        this.dataHoraInicio = LocalDateTime.now();
    }
}
