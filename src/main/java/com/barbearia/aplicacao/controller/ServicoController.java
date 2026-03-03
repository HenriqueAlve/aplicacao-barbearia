package com.barbearia.aplicacao.controller;

import com.barbearia.aplicacao.dto.request.CreateServicoDTO;
import com.barbearia.aplicacao.model.entity.Servico;
import com.barbearia.aplicacao.service.ServicoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;


    @PostMapping
    public ResponseEntity<Servico> cadastrarServico(@RequestBody CreateServicoDTO dto){
        var servico = servicoService.cadastrarServico(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(servico);
    }
}
