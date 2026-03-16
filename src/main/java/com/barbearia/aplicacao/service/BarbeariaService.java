package com.barbearia.aplicacao.service;

import com.barbearia.aplicacao.dto.request.CreateBarbeariaDTO;
import com.barbearia.aplicacao.dto.response.BarbeariaResponseHome;
import com.barbearia.aplicacao.model.entity.Barbearia;
import com.barbearia.aplicacao.model.entity.Barbeiro;
import com.barbearia.aplicacao.repository.BarbeariaRepository;
import com.barbearia.aplicacao.repository.BarbeiroRepository;
import com.barbearia.aplicacao.repository.ServicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BarbeariaService {

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private ServicoRepository servicoRepository;


    public Barbearia create(CreateBarbeariaDTO dto) {

        Barbeiro barbeiro = barbeiroRepository.findById(dto.barbeiro())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        Barbearia barbearia = new Barbearia();
        barbeiro.setBarbearia(barbearia); // lado do @ManyToOne


        barbearia.setNome(dto.nome());
        barbearia.setEndereco(dto.endereco());

        Barbearia savedBarbearia = barbeariaRepository.save(barbearia);

        barbeiro.setBarbearia(savedBarbearia);
        barbeiroRepository.save(barbeiro);

        return savedBarbearia;
    }

    public Barbearia listarServicosDeUmaBarbeariaExpecifica(UUID id) {
        Barbearia barbearia = barbeariaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Barbearia não encontrada")
        );

        barbearia.getServicos();
        return barbearia;
    }

    public List<BarbeariaResponseHome> listarBarbeariasNaHome() {
        return barbeariaRepository.findAll()
                .stream()
                .map(barbearia -> new BarbeariaResponseHome(
                        barbearia.getNome(),
                        barbearia.getEndereco()
                ))
                .toList();
    }
}
