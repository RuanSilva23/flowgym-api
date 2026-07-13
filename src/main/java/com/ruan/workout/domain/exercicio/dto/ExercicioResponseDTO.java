package com.ruan.workout.domain.exercicio.dto;

import com.ruan.workout.domain.exercicio.enums.GrupoMuscular;
import com.ruan.workout.domain.exercicio.enums.TipoExercicio;

public record ExercicioResponseDTO(Long id,
                                   String nome,
                                   GrupoMuscular grupoMuscular,
                                   TipoExercicio tipoExercicio) {
}
