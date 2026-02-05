package com.caio.apiprodutos.validation.impl;

import org.springframework.stereotype.Component;

import com.caio.apiprodutos.exception.NomeProdutoObrigatorioException;
import com.caio.apiprodutos.model.Produto;
import com.caio.apiprodutos.validation.ProdutoValidation;

@Component
public class ProdutoNomeObrigatorioValidation implements ProdutoValidation {

    @Override
    public void validar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new NomeProdutoObrigatorioException();
        }
    }
}

