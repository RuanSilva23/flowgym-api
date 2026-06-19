package com.ruan.workout.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
public class SerieTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id", nullable = false)
    private SessaoTreino sessaoTreino;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;
    @Column(precision = 5, scale = 2)
    private BigDecimal carga;
    private Integer repeticoes;
    private LocalDateTime dataHoraExecucao;

    public SerieTreino() {}

    public SerieTreino(SessaoTreino sessaoTreino, Exercicio exercicio, BigDecimal peso, Integer repeticoes) {
        this.sessaoTreino = sessaoTreino;
        this.exercicio = exercicio;
        this.carga = peso;
        this.repeticoes = repeticoes;
        this.dataHoraExecucao = LocalDateTime.now();
    }

}
