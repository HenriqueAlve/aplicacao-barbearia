package com.barbearia.aplicacao.controller;

import com.barbearia.aplicacao.dto.request.CreateBarbeiroDTO;
import com.barbearia.aplicacao.model.entity.Barbeiro;
import com.barbearia.aplicacao.service.BarbeiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barbeiro")
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @PostMapping
    public ResponseEntity<Barbeiro> cadastrarBarbeiro(@RequestBody CreateBarbeiroDTO dto){
        var barbeiro = barbeiroService.cadastrarBarbeiro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(barbeiro);
    }
}
