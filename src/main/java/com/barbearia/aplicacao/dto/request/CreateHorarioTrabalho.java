package com.barbearia.aplicacao.dto.request;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record CreateHorarioTrabalho(
        UUID barbeiroId,
        UUID barbeariaId,
        List<DayOfWeek> diasSemana,
        LocalTime horaInicio,
        LocalTime horaFim
) {
}
