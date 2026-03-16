package com.barbearia.aplicacao.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Avaliacao> avaliacao;
    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Servico> servicos;
    @OneToMany(mappedBy = "barbearia")
    @JsonManagedReference
    private List<Barbeiro> barbeiros;
    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<HorarioTrabalho> horarioTrabalho;
    @ElementCollection
    private List<String> imagensCorte;


}
