package com.ruan.workout.controller;

import com.ruan.workout.domain.usuario.Usuario;
import com.ruan.workout.domain.usuario.dto.LoginDTO;
import com.ruan.workout.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private TokenService token;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService token) {
        this.authenticationManager = authenticationManager;
        this.token = token;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO dto) {
        var dadosLogin = new UsernamePasswordAuthenticationToken(dto.usuario(), dto.password());
        var auth = authenticationManager.authenticate(dadosLogin);
        String tokenJWT = token.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(tokenJWT);
    }
}
