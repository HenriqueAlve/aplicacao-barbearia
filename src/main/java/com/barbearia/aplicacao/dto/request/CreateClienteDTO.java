package com.barbearia.aplicacao.dto.request;

import java.time.LocalDateTime;

public record CreateClienteDTO(

        String nome,
        String email,
        String senha,
        String telefone
) {
}
