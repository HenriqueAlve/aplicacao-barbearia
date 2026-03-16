package com.barbearia.aplicacao.controller;

import com.barbearia.aplicacao.dto.request.CreateAvaliacaoDTO;
import com.barbearia.aplicacao.model.entity.Avaliacao;
import com.barbearia.aplicacao.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {


    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/{idBarbearia}")
    public ResponseEntity<Avaliacao> create(@RequestBody CreateAvaliacaoDTO dto, @PathVariable UUID idBarbearia){
        Avaliacao avaliacao = avaliacaoService.create(dto, idBarbearia);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);
    }
}
