package com.barbearia.aplicacao.controller;

import com.barbearia.aplicacao.dto.request.CreateAgendamentoDTO;
import com.barbearia.aplicacao.dto.response.AgendamentoResponse;
import com.barbearia.aplicacao.dto.response.HorarioDisponivelResponse;
import com.barbearia.aplicacao.model.entity.Agendamento;
import com.barbearia.aplicacao.service.AgendamentoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;


    @PostMapping
    public ResponseEntity<AgendamentoResponse> cadastrarAgendamento(@RequestBody CreateAgendamentoDTO dto){
        var agendamento = agendamentoService.cadastrarAgendamento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<HorarioDisponivelResponse>> listarHorariosDisponiveis(
            @RequestParam UUID barbeiroId,
            @RequestParam UUID servicoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate data)
    {
        return ResponseEntity.ok(
                agendamentoService.listarHorariosDisponiveis(barbeiroId, servicoId, data)
        );
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<AgendamentoResponse>> listarAgendamentosDoCliente(
            @PathVariable UUID clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(agendamentoService.listarAgendamentosDoCliente(clienteId, data));
    }
}
