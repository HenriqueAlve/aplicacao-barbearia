package com.barbearia.aplicacao.service;

import com.barbearia.aplicacao.dto.request.CreateBarbeariaDTO;
import com.barbearia.aplicacao.model.entity.Barbearia;
import com.barbearia.aplicacao.model.entity.Barbeiro;
import com.barbearia.aplicacao.repository.BarbeariaRepository;
import com.barbearia.aplicacao.repository.BarbeiroRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarbeariaService {

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;


    public Barbearia create(CreateBarbeariaDTO dto) {

        Barbeiro barbeiro = barbeiroRepository.findById(dto.barbeiro())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        Barbearia barbearia = new Barbearia();
        barbearia.setNome(dto.nome());
        barbearia.setEndereco(dto.endereco());
        barbearia.setBarbeiro(barbeiro);

        return barbeariaRepository.save(barbearia);
    }
}
