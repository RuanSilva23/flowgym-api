package com.ruan.workout.controller;

import com.ruan.workout.domain.usuario.dto.UsuarioDTO;
import com.ruan.workout.domain.usuario.service.UsuarioService;
import com.ruan.workout.infra.exception.ValidationUsuarioException;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;


    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsuarioDTO dto){
        try{
            usuarioService.cadastro(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizarsenha/{id}")
    public ResponseEntity<String> atualizarSenha(@PathVariable Long id, @RequestParam String senhaAtual, @RequestBody String novaSenha) {
        try {
            usuarioService.atualizarSenha(id, senhaAtual, novaSenha);
            return  ResponseEntity.status(HttpStatus.OK).build();
        } catch (ValidationUsuarioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletaUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletaUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (ValidationUsuarioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }


}
