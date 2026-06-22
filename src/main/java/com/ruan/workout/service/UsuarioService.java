package com.ruan.workout.service;

import com.ruan.workout.dto.LoginDTO;
import com.ruan.workout.dto.UsuarioDTO;
import com.ruan.workout.exception.LoginException;
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
        if (usuarioRepository.existsUsuarioByEmailOrUsuario(dto.usuario(), dto.email())) {
            throw new ValidationUsuarioException("Usuario já cadastrado! ");
        }
        usuarioRepository.save(new Usuario(dto.usuario(), dto.password(), dto.email(), dto.name(), dto.idade()));
    }

    public void atualizarInformacoesUsuario(UsuarioDTO dto) {
        if (!usuarioRepository.existsUsuarioByEmailOrUsuario(dto.usuario(), dto.email())) {
            throw new ValidationUsuarioException("Usuario não cadastrado!");
        }

        Usuario usuario = usuarioRepository.findByUsuario(dto.usuario());
        usuario.setEmail(dto.email());
        usuario.setPassword(dto.password());
        usuario.setIdade(dto.idade());
        usuario.setName(dto.name());
        usuarioRepository.save(usuario);
    }

    public String login(LoginDTO dto) {
        Usuario usuario = usuarioRepository.findByUsuario(dto.usuario());

        if (usuario == null) {
            throw new ValidationUsuarioException("Usuario não cadastrado!");
        }

        if (usuario.getPassword().equals(dto.password())) {
            throw new LoginException("Senha Incorreta!");
        }

        return "Login realizado com sucesso!";
    }

}
