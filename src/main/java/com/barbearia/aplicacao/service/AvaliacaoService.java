package com.barbearia.aplicacao.service;

import com.barbearia.aplicacao.dto.request.CreateAvaliacaoDTO;
import com.barbearia.aplicacao.model.entity.Agendamento;
import com.barbearia.aplicacao.model.entity.Avaliacao;
import com.barbearia.aplicacao.model.entity.Barbearia;
import com.barbearia.aplicacao.repository.AgendamentoRepository;
import com.barbearia.aplicacao.repository.AvaliacaoRepository;
import com.barbearia.aplicacao.repository.BarbeariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public Avaliacao create(CreateAvaliacaoDTO dto, UUID idBarbearia) {

        Agendamento agendamento = agendamentoRepository.findById(dto.agendamentoId()).orElseThrow(
                () -> new RuntimeException("Agendamento não encontrado")
        );

        Barbearia barbearia = barbeariaRepository.findById(idBarbearia).orElseThrow(
                () -> new RuntimeException("Barbearia não encontrado")
        );

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAgendamento(agendamento);
        avaliacao.setBarbearia(barbearia);
        avaliacao.setCliente(agendamento.getCliente());
        avaliacao.setData(LocalDateTime.now());
        avaliacao.setNota(dto.nota());
        avaliacao.setComentario(dto.comentario());

        avaliacaoRepository.save(avaliacao);
        return avaliacao;

    }
}
