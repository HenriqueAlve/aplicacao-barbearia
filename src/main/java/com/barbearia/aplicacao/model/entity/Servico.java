package com.barbearia.aplicacao.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer duracaoMin;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "barbearia_id")
    @JsonBackReference
    private Barbearia barbearia;
}
