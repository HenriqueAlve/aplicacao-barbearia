package com.barbearia.aplicacao.service;

import com.barbearia.aplicacao.dto.request.CreateBarbeiroDTO;
import com.barbearia.aplicacao.model.entity.Barbeiro;
import com.barbearia.aplicacao.repository.BarbeiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Transactional
    public Barbeiro cadastrarBarbeiro(CreateBarbeiroDTO dto) {
        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setNome(dto.nome());
        barbeiro.setEmail(dto.email());
        barbeiro.setTelefone(dto.telefone());
        //barbeiro.setAtivo(dto.ativo());

        return barbeiroRepository.save(barbeiro);
    }
}
