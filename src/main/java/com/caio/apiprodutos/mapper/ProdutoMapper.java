package com.caio.apiprodutos.mapper;

import org.springframework.stereotype.Component;

import com.caio.apiprodutos.dto.ProdutoRequestDTO;
import com.caio.apiprodutos.dto.ProdutoResponseDTO;
import com.caio.apiprodutos.model.Produto;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        return produto;
    }

    public ProdutoResponseDTO toResponseDTO(Produto produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        return dto;
    }
}
