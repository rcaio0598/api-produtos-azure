package com.caio.apiprodutos.exception;

public class PrecoProdutoInvalidoException extends RegraNegocioException {
    public PrecoProdutoInvalidoException() {
        super("Preço do produto deve ser maior que zero");
    }
}

