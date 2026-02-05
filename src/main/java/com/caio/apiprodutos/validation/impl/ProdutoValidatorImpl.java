package com.caio.apiprodutos.validation.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.caio.apiprodutos.model.Produto;
import com.caio.apiprodutos.validation.ProdutoValidation;
import com.caio.apiprodutos.validation.ProdutoValidator;

@Component
public class ProdutoValidatorImpl implements ProdutoValidator {

    private final List<ProdutoValidation> validations;

    public ProdutoValidatorImpl(List<ProdutoValidation> validations) {
        this.validations = validations;
    }

    @Override
    public void validar(Produto produto) {
        for (ProdutoValidation validation : validations) {
            validation.validar(produto);
        }
    }
}

