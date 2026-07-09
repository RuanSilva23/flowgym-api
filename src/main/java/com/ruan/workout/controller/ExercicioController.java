package com.ruan.workout.controller;

import com.ruan.workout.domain.exercicio.dto.ExercicioDTO;
import com.ruan.workout.domain.exercicio.service.ExercicioService;
import com.ruan.workout.infra.exception.ValidationExercicioException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercicios")
@AllArgsConstructor
public class ExercicioController {
    private final ExercicioService exercicioService;

    @PostMapping("/cadastrar/")
    public ResponseEntity<String> cadastrarExercicio(ExercicioDTO dto) {
        try {
            exercicioService.cadastrarExercicio(dto);
            return ResponseEntity.ok("Exercicio cadastrado com sucesso!");

        } catch (ValidationExercicioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar/{id}/")
    public ResponseEntity<String> cadastrarExercicioPersonalizado(@PathVariable Long id, @RequestBody ExercicioDTO dto) {
        try {
            exercicioService.cadastrarExercicioPersonalizado(id , dto);
            return ResponseEntity.ok("Exercicio cadastrado com sucesso!");

        } catch (ValidationExercicioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<String> listarExercicios() {
        try {
            exercicioService.listar();
            return ResponseEntity.ok().build();

        } catch (ValidationExercicioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
