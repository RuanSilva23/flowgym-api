package com.ruan.workout.controller;

import com.ruan.workout.domain.historico.HistoricoPesoCorporal;
import com.ruan.workout.domain.historico.dto.PesoDTO;
import com.ruan.workout.domain.historico.dto.PesoResponseDTO;
import com.ruan.workout.domain.historico.service.HistoricoPesoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/api/usuario/historicopeso")
public class HistoricoPesoController {
    private HistoricoPesoService historicoPesoService;


    @PostMapping("/cadastro/{id}")
    public ResponseEntity<String> cadastroPeso(@RequestBody PesoDTO dto, @PathVariable Long id) {
        historicoPesoService.cadastrarPeso(dto, id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizapeso/{idHistorico}/usuario/{idUsuario}")
    public ResponseEntity<String> atualizaPeso(@RequestBody BigDecimal peso, @PathVariable Long idHistorico, @PathVariable Long idUsuario) {
        historicoPesoService.alterarPeso(idHistorico, idUsuario, peso);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/pesoatual/{idUsuario}")
    public ResponseEntity<PesoResponseDTO> ultimoPesoRegistrado(@PathVariable Long idUsuario) {
        var pesoAtual = historicoPesoService.mostraUltimoPeso(idUsuario);
        return ResponseEntity.ok(pesoAtual);

    }

    @GetMapping("/historicopeso/{idUsuario}")
    public ResponseEntity<List<PesoResponseDTO>> historicoPeso(@PathVariable Long idUsuario) {
        var listhistorico = historicoPesoService.historicoPorUsuario(idUsuario);
        return ResponseEntity.ok(listhistorico);

    }
}
