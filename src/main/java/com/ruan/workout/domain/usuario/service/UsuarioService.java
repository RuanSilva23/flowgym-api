package com.ruan.workout.domain.usuario.service;

import com.ruan.workout.domain.usuario.dto.UsuarioDTO;
import com.ruan.workout.infra.exception.ValidationUsuarioException;
import com.ruan.workout.domain.usuario.Usuario;
import com.ruan.workout.domain.usuario.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    
    public void cadastro(UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.existsUsuarioByEmailOrUsuario(dto.email(), dto.usuario()).orElseThrow(() -> new ValidationUsuarioException("Email ou Usuário já cadastrado"));

        usuario.setName(dto.name());
        usuario.setEmail(dto.email());
        usuario.setUsuario(dto.usuario());

        // Criptografa a senha
        String senhaCriptografada = passwordEncoder.encode(dto.password());
        usuario.setPassword(senhaCriptografada);

        // Criação da Data de Cadastro do Usuário
        usuario.setDataCadastro(LocalDateTime.now());

        usuarioRepository.save(usuario);
    }


    public void atualizarSenha(Long id, String senhaAtual, String novaSenha) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ValidationUsuarioException("Usuário não encontrado"));

        // Verifica se a senha atual está correta
        boolean senhaBate = passwordEncoder.matches(senhaAtual, usuario.getPassword());

        // Se a senha estiver errada, lança uma exceção
        if (!senhaBate) {
            throw new ValidationUsuarioException("Senha atual Incorreta!");
        }

        // Se a senha estiver correta, atualiza a senha
        String senhaNovaCriptografada = passwordEncoder.encode(novaSenha);
        usuario.setPassword(senhaNovaCriptografada);

        // Salva no Banco
        usuarioRepository.save(usuario);
    }

    public void deletaUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ValidationUsuarioException("Usuario não encontrado"));
        usuarioRepository.delete(usuario);
    }
}
