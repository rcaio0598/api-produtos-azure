package com.caio.apiprodutos.validation.impl;

import org.springframework.stereotype.Component;

import com.caio.apiprodutos.exception.PrecoProdutoInvalidoException;
import com.caio.apiprodutos.model.Produto;
import com.caio.apiprodutos.validation.ProdutoValidation;

@Component
public class ProdutoPrecoMaiorQueZeroValidation implements ProdutoValidation {

    @Override
    public void validar(Produto produto) {
        if (produto.getPreco() == null || produto.getPreco() <= 0) {
            throw new PrecoProdutoInvalidoException();
        }
    }
}

