package com.barbearia.aplicacao.model.entity;

import com.barbearia.aplicacao.model.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Barbeiro barbeiro;

    @ManyToOne
    private Servico servico;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    @Enumerated
    private StatusAgendamento statusAgendamento;

}
