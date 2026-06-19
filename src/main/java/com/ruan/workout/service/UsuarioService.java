package com.ruan.workout.service;

import com.ruan.workout.dto.LoginDTO;
import com.ruan.workout.dto.UsuarioDTO;
import com.ruan.workout.exception.ValidationUsuarioException;
import com.ruan.workout.model.Usuario;
import com.ruan.workout.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    
    public void cadastro(UsuarioDTO dto) {
        if (usuarioRepository.existsUsuarioByEmailOrUsuario(dto.email(), dto.usuario())){
            throw new ValidationUsuarioException("Email ou Usuário já em uso!");
        }
        usuarioRepository.save(new Usuario(dto.usuario(), dto.name(), dto.email(), dto.password(), dto.idade()));
    }

    public String login(LoginDTO dto) {
        return "";
    }

    public void atualizarInformacoesUsuario(UsuarioDTO dto) {
    }
}
