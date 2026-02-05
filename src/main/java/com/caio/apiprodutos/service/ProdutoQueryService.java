package com.caio.apiprodutos.service;

import java.util.List;

import com.caio.apiprodutos.dto.ProdutoResponseDTO;

public interface ProdutoQueryService {

    List<ProdutoResponseDTO> listar();

    ProdutoResponseDTO buscarPorId(Long id);
}

