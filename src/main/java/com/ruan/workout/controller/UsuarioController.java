package com.ruan.workout.controller;

import com.ruan.workout.dto.LoginDTO;
import com.ruan.workout.dto.TokenDTO;
import com.ruan.workout.dto.UsuarioDTO;
import com.ruan.workout.exception.LoginException;
import com.ruan.workout.service.UsuarioService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
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

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO dto){
        try{
            String token = usuarioService.login(dto);
            return ResponseEntity.ok(new TokenDTO(token));
        } catch (LoginException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarUsuario(@RequestBody UsuarioDTO dto){
        try{
            usuarioService.atualizarInformacoesUsuario(dto);
            return ResponseEntity.ok().build();
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
