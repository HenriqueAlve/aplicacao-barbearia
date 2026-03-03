package com.barbearia.aplicacao.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    private Agendamento agendamento;

    private Integer nota;
    private String comentario;

}
