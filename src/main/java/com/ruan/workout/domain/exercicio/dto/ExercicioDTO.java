package com.ruan.workout.domain.exercicio.dto;

import com.ruan.workout.domain.exercicio.enums.GrupoMuscular;
import com.ruan.workout.domain.exercicio.enums.TipoExercicio;

public record ExercicioDTO(String nome, GrupoMuscular grupoMuscular, String descricao, TipoExercicio tipoExercicio) {
}
