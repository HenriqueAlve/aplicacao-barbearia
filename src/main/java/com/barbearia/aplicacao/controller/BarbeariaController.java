package com.barbearia.aplicacao.controller;

import com.barbearia.aplicacao.dto.request.CreateBarbeariaDTO;
import com.barbearia.aplicacao.dto.response.BarbeariaResponseHome;
import com.barbearia.aplicacao.model.entity.Barbearia;
import com.barbearia.aplicacao.service.BarbeariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    @GetMapping("/{id}/servicos")
    public ResponseEntity<Barbearia> listarServicosDeUmaBarbeariaExpecifica(@PathVariable UUID id){
        var servicosBarbearia = barbeariaService.listarServicosDeUmaBarbeariaExpecifica(id);
        return ResponseEntity.status(HttpStatus.OK).body(servicosBarbearia);
    }

    @GetMapping
    public ResponseEntity<List<BarbeariaResponseHome>> listarBarbeariasNaHome(){
        var listaBarbearia = barbeariaService.listarBarbeariasNaHome();
        return ResponseEntity.status(HttpStatus.OK).body(listaBarbearia);
    }


}
