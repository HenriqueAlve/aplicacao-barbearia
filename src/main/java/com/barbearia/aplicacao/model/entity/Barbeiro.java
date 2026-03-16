package com.barbearia.aplicacao.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private boolean ativo;
    @ManyToOne
    @JsonBackReference
    private Barbearia barbearia;

    @OneToOne
    private Usuario usuario;

}
