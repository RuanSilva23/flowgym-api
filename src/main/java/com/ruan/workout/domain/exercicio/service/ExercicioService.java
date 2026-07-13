package com.ruan.workout.domain.exercicio.service;

import com.ruan.workout.domain.exercicio.Exercicio;
import com.ruan.workout.domain.exercicio.ExercicioRepository;
import com.ruan.workout.domain.exercicio.dto.ExercicioDTO;
import com.ruan.workout.domain.exercicio.dto.ExercicioResponseDTO;
import com.ruan.workout.domain.usuario.UsuarioRepository;
import com.ruan.workout.infra.exception.ValidationExercicioException;
import com.ruan.workout.infra.exception.ValidationUsuarioException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ExercicioService {
    private ExercicioRepository exercicioRepository;

    private UsuarioRepository usuarioRepository;

    public void cadastrarExercicio(ExercicioDTO dto) {

        if (exercicioRepository.existsByNome(dto.nome()).isPresent()){
            throw new ValidationExercicioException("Exercicio já cadastrado no sistema");

        }

        Exercicio exercicio = new Exercicio();
        exercicio.setNome(dto.nome());
        exercicio.setGrupoMuscular(dto.grupoMuscular());
        exercicio.setTipoExercicio(dto.tipoExercicio());
        exercicio.setDataCadastro(LocalDateTime.now());

        exercicioRepository.save(exercicio);
    }

    public void cadastrarExercicioPersonalizado(Long idUsuario, ExercicioDTO dto) {

        var usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ValidationUsuarioException("usuário não encontrado"));

        if (exercicioRepository.existsByNomeOrUsuarioIsNull(dto.nome()) || exercicioRepository.existsByNomeOrUsuarioId(dto.nome(), idUsuario)){
            throw new ValidationExercicioException("Você ou o sistema já possuem um exercício com esse nome!");
        }

        Exercicio exercicio = new Exercicio();
        exercicio.setNome(dto.nome());
        exercicio.setGrupoMuscular(dto.grupoMuscular());
        exercicio.setTipoExercicio(dto.tipoExercicio());
        exercicio.setDataCadastro(LocalDateTime.now());
        exercicio.setUsuario(usuario);

        exercicioRepository.save(exercicio);

    }

    public List<ExercicioResponseDTO> listar(Long idUsuario) {
        List<Exercicio> exercicios = exercicioRepository.findByUsuarioIsNullOrUsuarioId(idUsuario);

        return exercicios.stream()
                .map(exercicio -> new ExercicioResponseDTO(
                        exercicio.getId(),
                        exercicio.getNome(),
                        exercicio.getGrupoMuscular(),
                        exercicio.getTipoExercicio()
                )).toList();
    }

    public void upgrade(Long idExercicio, ExercicioDTO dto) {
        Exercicio exercicio = exercicioRepository.findById(idExercicio).orElseThrow(() -> new ValidationExercicioException("Exercicio não encontrado"));

        exercicio.setNome(dto.nome());
        exercicio.setTipoExercicio(dto.tipoExercicio());
        exercicio.setGrupoMuscular(dto.grupoMuscular());

        exercicioRepository.save(exercicio);
    }

    public void delete(Long idExercicio) {
        Exercicio exercicio = exercicioRepository.findById(idExercicio).orElseThrow(() -> new ValidationUsuarioException("Exercicio não encontrado"));

        exercicioRepository.delete(exercicio);
    }
}
