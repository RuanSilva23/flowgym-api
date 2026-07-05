package com.ruan.workout.domain.usuario.service;

import com.ruan.workout.domain.usuario.dto.UsuarioDTO;
import com.ruan.workout.domain.usuario.enums.RoleEnum;
import com.ruan.workout.domain.usuario.enums.StatusUsuario;
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

        if (usuarioRepository.existsUsuarioByEmailOrUsuario(dto.email(), dto.usuario())){
            throw new ValidationUsuarioException("Usuário já cadastrado no sistema");
        }

        Usuario usuario = new Usuario();
        usuario.setName(dto.name());
        usuario.setEmail(dto.email());
        usuario.setUsuario(dto.usuario());

        // Criptografa a senha
        String senhaCriptografada = passwordEncoder.encode(dto.password());
        usuario.setPassword(senhaCriptografada);

        // Criação da Data de Cadastro do Usuário
        usuario.setDataCadastro(LocalDateTime.now());

        if (dto.role()!= null) {
            usuario.setRole(dto.role());

        } else {
            usuario.setRole(RoleEnum.ROLE_USER);
        }

        usuario.setStatus(StatusUsuario.ATIVO);

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
        usuario.setStatus(StatusUsuario.INATIVO);
        usuarioRepository.save(usuario);
    }
}
