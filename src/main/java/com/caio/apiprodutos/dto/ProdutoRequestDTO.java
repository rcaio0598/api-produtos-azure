package com.caio.apiprodutos.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProdutoRequestDTO {

    @NotBlank(message = "Nome do produto é obrigatório")
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
