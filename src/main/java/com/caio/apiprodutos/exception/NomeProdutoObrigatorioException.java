package com.caio.apiprodutos.exception;

public class NomeProdutoObrigatorioException extends RegraNegocioException {
    public NomeProdutoObrigatorioException() {
        super("Nome do produto é obrigatório");
    }
}

