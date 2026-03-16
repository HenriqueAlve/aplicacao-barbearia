package com.barbearia.aplicacao.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioTrabalho {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Barbeiro barbeiro;

    @ManyToOne
    @JsonBackReference
    private Barbearia barbearia;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

}
