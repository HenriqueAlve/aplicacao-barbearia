package com.barbearia.aplicacao.dto.request;

import java.util.UUID;

public record CreateBarbeariaDTO(

        String nome,
        String endereco,
        UUID barbeiro
) {
}
