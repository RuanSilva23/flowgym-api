package com.ruan.workout.controller;

import com.ruan.workout.domain.exercicio.dto.ExercicioDTO;
import com.ruan.workout.domain.exercicio.dto.ExercicioResponseDTO;
import com.ruan.workout.domain.exercicio.service.ExercicioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
@AllArgsConstructor
public class ExercicioController {
    private final ExercicioService exercicioService;

    @PostMapping("/cadastrar/")
    public ResponseEntity<String> cadastrarExercicio(ExercicioDTO dto) {
        exercicioService.cadastrarExercicio(dto);
        return ResponseEntity.ok("Exercicio cadastrado com sucesso!");
    }

    @PostMapping("/cadastrar/{id}/")
    public ResponseEntity<String> cadastrarExercicioPersonalizado(@PathVariable Long id, @RequestBody ExercicioDTO dto) {
       exercicioService.cadastrarExercicioPersonalizado(id , dto);
       return ResponseEntity.ok("Exercicio cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ExercicioResponseDTO>> listarExercicios(@RequestParam Long idUsuario) {
        var lista = exercicioService.listar(idUsuario);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/upgrade/{id}")
    public ResponseEntity<String> upgradeExercicio(@PathVariable Long id, @RequestBody ExercicioDTO dto) {
         exercicioService.upgrade(id , dto);
         return ResponseEntity.ok("Exercicio atualizado com sucesso!");
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> deleteExercicio(@PathVariable Long id) {
        exercicioService.delete(id);
        return ResponseEntity.ok("Exercicio deletado com sucesso!");
    }
}
