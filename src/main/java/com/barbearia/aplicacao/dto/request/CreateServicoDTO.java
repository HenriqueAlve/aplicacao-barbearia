package com.barbearia.aplicacao.dto.request;

import java.math.BigDecimal;

public record CreateServicoDTO(

         String nome,
         String descricao,
         BigDecimal preco,
         Integer duracaoMin,
         boolean ativo
) {
}
