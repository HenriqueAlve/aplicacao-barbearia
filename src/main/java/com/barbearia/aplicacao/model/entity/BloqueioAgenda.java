package com.barbearia.aplicacao.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloqueioAgenda {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Barbeiro barbeiro;

    private String motivo;


    private LocalDateTime inicio;
    private LocalDateTime fim;
}
