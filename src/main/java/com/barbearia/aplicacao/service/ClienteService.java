package com.barbearia.aplicacao.service;

import com.barbearia.aplicacao.dto.request.CreateClienteDTO;
import com.barbearia.aplicacao.model.entity.Cliente;
import com.barbearia.aplicacao.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Transactional
    public Cliente cadastrarCliente(CreateClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        //cliente.setDataNasc(dto.dataNasc());

        return clienteRepository.save(cliente);


    }
}
