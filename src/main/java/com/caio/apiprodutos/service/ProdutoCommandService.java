package com.caio.apiprodutos.service;

import com.caio.apiprodutos.dto.ProdutoRequestDTO;
import com.caio.apiprodutos.dto.ProdutoResponseDTO;

public interface ProdutoCommandService {

    ProdutoResponseDTO salvar(ProdutoRequestDTO dto);

    ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto);

    void remover(Long id);
}

