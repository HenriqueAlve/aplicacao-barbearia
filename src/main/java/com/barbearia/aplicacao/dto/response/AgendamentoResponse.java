package com.barbearia.aplicacao.dto.response;

import com.barbearia.aplicacao.model.enums.StatusAgendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoResponse(
        UUID id,
        String clienteNome,
        String barbeiroNome,
        String servicoNome,
        BigDecimal preco,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        StatusAgendamento status
) {
}
