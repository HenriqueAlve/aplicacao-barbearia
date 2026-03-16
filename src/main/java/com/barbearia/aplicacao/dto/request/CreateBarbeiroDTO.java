package com.barbearia.aplicacao.dto.request;

public record CreateBarbeiroDTO(
        String nome,
        String email,
        String senha,
        String telefone
) {
}
