package com.barbearia.aplicacao.service;

import com.barbearia.aplicacao.dto.request.CreateAgendamentoDTO;
import com.barbearia.aplicacao.dto.response.AgendamentoResponse;
import com.barbearia.aplicacao.dto.response.HorarioDisponivelResponse;
import com.barbearia.aplicacao.model.entity.*;
import com.barbearia.aplicacao.model.enums.StatusAgendamento;
import com.barbearia.aplicacao.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private HorarioTrabalhoRepository horarioTrabalhoRepository;

    @Autowired
    private BloqueioAgendaRepository bloqueioAgendaRepository;

    @Transactional
    public AgendamentoResponse  cadastrarAgendamento(CreateAgendamentoDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Barbeiro barbeiro = barbeiroRepository.findById(dto.barbeiroId())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        Servico servico = servicoRepository.findById(dto.servicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        if (!barbeiro.isAtivo()) {
            throw new RuntimeException("Barbeiro inativo");
        }

        if (!servico.isAtivo()) {
            throw new RuntimeException("Serviço inativo");
        }

        LocalDateTime fim = dto.dataHoraInicio()
                .plusMinutes(servico.getDuracaoMin());

        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(cliente);
        agendamento.setBarbeiro(barbeiro);
        agendamento.setServico(servico);
        agendamento.setDataHoraInicio(dto.dataHoraInicio());
        agendamento.setDataHoraFim(fim);
        agendamento.setStatusAgendamento(StatusAgendamento.CONFIRMADO);

        boolean conflito = agendamentoRepository.existeConflitoDeHorario(
                dto.barbeiroId(),
                dto.dataHoraInicio(),
                fim
        );

        if (conflito) {
            throw new RuntimeException("Horário indisponível para este barbeiro");
        }

        Agendamento salvo = agendamentoRepository.save(agendamento);

        return new AgendamentoResponse(
                salvo.getId(),
                salvo.getCliente().getNome(),
                salvo.getBarbeiro().getNome(),
                salvo.getServico().getNome(),
                salvo.getServico().getPreco(),
                salvo.getDataHoraInicio(),
                salvo.getDataHoraFim(),
                salvo.getStatusAgendamento()
        );
    }

    public List<HorarioDisponivelResponse> listarHorariosDisponiveis(UUID barbeiroId, UUID servicoId, LocalDate data) {
        Servico servico = servicoRepository.findById(servicoId).orElseThrow(
                () -> new RuntimeException("Serviço não encontrado")
        );

        int duracaoMin = servico.getDuracaoMin();

        DayOfWeek diaSemana = data.getDayOfWeek();

        HorarioTrabalho horarioTrabalho = horarioTrabalhoRepository
                .findByBarbeiroIdAndDiaSemana(barbeiroId, diaSemana)
                .orElseThrow(() -> new RuntimeException(
                        "Barbeiro não trabalha neste dia da semana"
                ));

        List<Agendamento> agendamentosDoDia = agendamentoRepository
                .findAgendamentosDoBarbeiroNaData(barbeiroId, data);

        LocalDateTime inicioDia = data.atTime(horarioTrabalho.getHoraInicio());
        LocalDateTime fimDia    = data.atTime(horarioTrabalho.getHoraFim());

        List<BloqueioAgenda> bloqueios = bloqueioAgendaRepository
                .findBloqueiosDoBarbeiroNaData(barbeiroId, inicioDia, fimDia);

        // 4. gera os slots e filtra os ocupados
        return gerarSlotsDisponiveis(
                horarioTrabalho.getHoraInicio(),
                horarioTrabalho.getHoraFim(),
                duracaoMin,
                data,
                agendamentosDoDia,
                bloqueios
        );
    }

    private List<HorarioDisponivelResponse> gerarSlotsDisponiveis(
            LocalTime inicio,
            LocalTime fim,
            int duracaoMin,
            LocalDate data,
            List<Agendamento> agendamentos,
            List<BloqueioAgenda> bloqueios) {

        List<HorarioDisponivelResponse> disponiveis = new ArrayList<>();

        LocalTime slotAtual = inicio;

        while (!slotAtual.plusMinutes(duracaoMin).isAfter(fim)) {

            LocalDateTime slotInicio = data.atTime(slotAtual);
            LocalDateTime slotFim    = slotInicio.plusMinutes(duracaoMin);

            boolean ocupado = agendamentos.stream().anyMatch(a ->
                    a.getDataHoraInicio().isBefore(slotFim) &&
                            a.getDataHoraFim().isAfter(slotInicio)
            );

            boolean bloqueado = bloqueios.stream().anyMatch(b ->
                    b.getInicio().isBefore(slotFim) &&
                            b.getFim().isAfter(slotInicio)
            );

            boolean jáPassou = slotInicio.isBefore(LocalDateTime.now());

            if (!ocupado && !bloqueado && !jáPassou) {
                disponiveis.add(new HorarioDisponivelResponse(slotAtual, slotAtual.plusMinutes(duracaoMin)));
            }

            slotAtual = slotAtual.plusMinutes(duracaoMin);
        }

        return disponiveis;
    }
}
