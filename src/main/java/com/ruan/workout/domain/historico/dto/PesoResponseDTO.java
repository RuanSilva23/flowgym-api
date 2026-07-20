package com.ruan.workout.domain.historico.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PesoResponseDTO(Long id,
                              BigDecimal pesoCorporal,
                              LocalDateTime dataRegistro) {
}
