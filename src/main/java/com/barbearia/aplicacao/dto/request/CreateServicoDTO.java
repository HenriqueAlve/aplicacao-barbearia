package com.barbearia.aplicacao.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateServicoDTO(

         String nome,
         String descricao,
         BigDecimal preco,
         Integer duracaoMin,
         boolean ativo,
         UUID barbeariaId
) {
}
