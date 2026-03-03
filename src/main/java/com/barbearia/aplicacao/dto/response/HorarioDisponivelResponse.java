package com.barbearia.aplicacao.dto.response;

import java.time.LocalTime;

public record HorarioDisponivelResponse(
        LocalTime horaInicio,
        LocalTime horaFim
) {
}
