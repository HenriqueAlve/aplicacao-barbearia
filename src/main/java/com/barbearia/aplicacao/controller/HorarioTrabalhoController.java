package com.barbearia.aplicacao.controller;

import com.barbearia.aplicacao.dto.request.CreateHorarioTrabalho;
import com.barbearia.aplicacao.model.entity.HorarioTrabalho;
import com.barbearia.aplicacao.service.HorarioTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/horario-trabalho")
public class HorarioTrabalhoController {

    @Autowired
    private HorarioTrabalhoService horarioTrabalhoService;

    @PostMapping
    public ResponseEntity<List<HorarioTrabalho>> create(@RequestBody CreateHorarioTrabalho dto){
        List<HorarioTrabalho> horarios = horarioTrabalhoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(horarios);
    }
}
