package com.ruan.workout.domain.exercicio;

import com.ruan.workout.domain.usuario.Usuario;
import com.ruan.workout.domain.exercicio.enums.GrupoMuscular;
import com.ruan.workout.domain.exercicio.enums.TipoExercicio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private GrupoMuscular grupoMuscular;
    @Enumerated(EnumType.STRING)
    private TipoExercicio tipoExercicio;
    private LocalDateTime dataCadastro;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

    public Exercicio() {}

    public Exercicio(String nome, GrupoMuscular grupoMuscular, Usuario usuario) {
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.dataCadastro = LocalDateTime.now();
        this.usuario = usuario;
    }
}
