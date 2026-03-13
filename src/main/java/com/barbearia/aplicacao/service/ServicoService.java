package com.barbearia.aplicacao.service;

import com.barbearia.aplicacao.dto.request.CreateServicoDTO;
import com.barbearia.aplicacao.model.entity.Barbearia;
import com.barbearia.aplicacao.model.entity.Servico;
import com.barbearia.aplicacao.repository.BarbeariaRepository;
import com.barbearia.aplicacao.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @Transactional
    public Servico cadastrarServico(CreateServicoDTO dto) {

        Barbearia barbearia = barbeariaRepository.findById(dto.barbeariaId())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrada"));


        Servico servico = new Servico();
        servico.setNome(dto.nome());
        servico.setDescricao(dto.descricao());
        servico.setPreco(dto.preco());
        servico.setDuracaoMin(dto.duracaoMin());
        servico.setAtivo(dto.ativo());
        servico.setBarbearia(barbearia);

        return servicoRepository.save(servico);
    }
}
