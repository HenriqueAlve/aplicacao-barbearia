package com.barbearia.aplicacao.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Barbearia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String endereco;
    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacao;
    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL)
    private List<Servico> servicos;
    @OneToOne
    private Barbeiro barbeiro;
    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL)
    private List<HorarioTrabalho> horarioTrabalho;
    @ElementCollection
    private List<String> imagensCorte;


}
