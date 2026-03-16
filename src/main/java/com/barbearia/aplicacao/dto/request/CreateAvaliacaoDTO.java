package com.barbearia.aplicacao.dto.request;

import java.util.UUID;

public record CreateAvaliacaoDTO(
        UUID agendamentoId,
        Integer nota,
        String comentario
) {
}
