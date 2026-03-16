package com.barbearia.aplicacao.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Agendamento agendamento;
    @ManyToOne
    @JsonBackReference
    private Barbearia barbearia;
    private LocalDateTime data;

    private Integer nota;
    private String comentario;
    @ManyToOne
    private Cliente cliente;

}
