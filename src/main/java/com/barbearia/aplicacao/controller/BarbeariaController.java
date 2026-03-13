package com.barbearia.aplicacao.controller;

import com.barbearia.aplicacao.dto.request.CreateBarbeariaDTO;
import com.barbearia.aplicacao.model.entity.Barbearia;
import com.barbearia.aplicacao.service.BarbeariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/barbearias")
public class BarbeariaController {

    @Autowired
    private BarbeariaService barbeariaService;

    @PostMapping
    public ResponseEntity<Barbearia> create(@RequestBody CreateBarbeariaDTO dto) {
        Barbearia barbearia = barbeariaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(barbearia);
    }


}
