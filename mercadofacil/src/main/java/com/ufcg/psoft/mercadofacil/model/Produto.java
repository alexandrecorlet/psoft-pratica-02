package com.ufcg.psoft.mercadofacil.model;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Produto {

    private Long id;

    private String nome;

    private double preco;

    private String codigoBarra;

    private String fabricante;

}
