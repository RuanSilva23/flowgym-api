package com.ruan.workout.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class HistoricoPesoCorporal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(precision = 5, scale = 2)
    private BigDecimal pesoCorporal;
    private LocalDateTime dataRegistro;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public  HistoricoPesoCorporal() {}

    public HistoricoPesoCorporal(BigDecimal pesoCorporal, Usuario usuario) {
        this.pesoCorporal = pesoCorporal;
        this.dataRegistro = LocalDateTime.now();
        this.usuario = usuario;
    }
}
