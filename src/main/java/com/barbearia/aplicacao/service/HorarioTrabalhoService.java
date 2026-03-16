package com.barbearia.aplicacao.service;

import com.barbearia.aplicacao.dto.request.CreateHorarioTrabalho;
import com.barbearia.aplicacao.model.entity.Barbearia;
import com.barbearia.aplicacao.model.entity.Barbeiro;
import com.barbearia.aplicacao.model.entity.HorarioTrabalho;
import com.barbearia.aplicacao.repository.BarbeariaRepository;
import com.barbearia.aplicacao.repository.BarbeiroRepository;
import com.barbearia.aplicacao.repository.HorarioTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioTrabalhoService {

    @Autowired
    private HorarioTrabalhoRepository horarioTrabalhoRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;


    public List<HorarioTrabalho> create(CreateHorarioTrabalho dto) {

        Barbeiro barbeiro = barbeiroRepository.findById(dto.barbeiroId())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        Barbearia barbearia = barbeariaRepository.findById(dto.barbeariaId())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrada"));

        List<HorarioTrabalho> horarios = dto.diasSemana().stream()
                .map(dia -> {
                    HorarioTrabalho horario = new HorarioTrabalho();
                    horario.setBarbeiro(barbeiro);
                    horario.setBarbearia(barbearia);
                    horario.setDiaSemana(dia);
                    horario.setHoraInicio(dto.horaInicio());
                    horario.setHoraFim(dto.horaFim());
                    return horario;
                })
                .toList();

        return horarioTrabalhoRepository.saveAll(horarios);
    }
}
