package com.barbearia.aplicacao.model.entity;

import com.barbearia.aplicacao.model.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Barbeiro barbeiro;

    @ManyToOne
    private Servico servico;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    @Enumerated(EnumType.STRING)
    private StatusAgendamento statusAgendamento;

}
