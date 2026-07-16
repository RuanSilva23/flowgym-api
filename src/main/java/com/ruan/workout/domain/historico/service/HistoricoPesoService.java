package com.ruan.workout.domain.historico.service;

import com.ruan.workout.domain.historico.HistoricoPesoCorporal;
import com.ruan.workout.domain.historico.HistoricoPesoCorporalRepository;
import com.ruan.workout.domain.usuario.Usuario;
import com.ruan.workout.domain.usuario.UsuarioRepository;
import com.ruan.workout.infra.exception.ValidationPesoException;
import com.ruan.workout.infra.exception.ValidationUsuarioException;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class HistoricoPesoService {
    private UsuarioRepository usuarioRepository;

    private HistoricoPesoCorporalRepository historicoPeso;

    // Metodo para validação de usuario e evitar repetição desnecessaria
    private @NonNull Usuario usuarioExiste(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new ValidationUsuarioException("Usuario não encontrado ou não cadastrado no sistema!"));
    }
    
    public void cadastrarPeso(BigDecimal pesoCorporal, Long idUsuario) {
        Usuario usuario = usuarioExiste(idUsuario);

        if (pesoCorporal == null || pesoCorporal.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new ValidationPesoException("Peso Invalido! Tente novamente! ");
        }

        historicoPeso.save(new HistoricoPesoCorporal(pesoCorporal, usuario));
    }

    public void alterarPeso(Long idHistoricoPeso, Long idUsuario, BigDecimal novoPeso) {
        // Valida se o usuario existe
        Usuario usuario = usuarioExiste(idUsuario);

        // Valida se o id do historico existe no Banco de Dados
        HistoricoPesoCorporal historico = historicoPeso.findById(idHistoricoPeso).orElseThrow(() -> new ValidationPesoException("Peso não encontrado!"));

        // Verifica se é o Historico do Peso é do Usuário logado
        if (!historico.getUsuario().getId().equals(usuario.getId())) {
            throw new ValidationPesoException("Acesso negado! Você não tem permissão para alterar esse peso!");
        }

        // Verifica se o novo peso é valido
        if (novoPeso == null || novoPeso.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationPesoException("Peso Invalido! Tente novamente! ");
        }

        // Atualiza o peso
        historico.setPesoCorporal(novoPeso);
        historicoPeso.save(historico);
    }

    // Metodo para validação de usuario e evitar repetição desnecessaria
    private @NonNull Usuario usuarioExiste(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new ValidationUsuarioException("Usuario não encontrado ou não cadastrado no sistema!"));
    }

}
