package com.ufcg.psoft.mercadofacil.model;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Lote {

    private Long id;

    private Produto produto;

    private Integer numeroDeItens;

}
