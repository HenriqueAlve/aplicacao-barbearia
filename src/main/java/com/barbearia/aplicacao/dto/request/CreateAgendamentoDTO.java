package com.barbearia.aplicacao.dto.request;

import com.barbearia.aplicacao.model.entity.Barbeiro;
import com.barbearia.aplicacao.model.entity.Cliente;
import com.barbearia.aplicacao.model.entity.Servico;
import com.barbearia.aplicacao.model.enums.StatusAgendamento;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAgendamentoDTO(

         UUID clienteId,
         UUID barbeiroId,
         UUID servicoId,
         LocalDateTime dataHoraInicio
) {
}
