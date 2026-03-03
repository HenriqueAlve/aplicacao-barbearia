package com.barbearia.aplicacao.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioTrabalho {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Barbeiro barbeiro;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

}
