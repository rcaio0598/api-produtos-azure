package com.caio.apiprodutos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.caio.apiprodutos.dto.ProdutoRequestDTO;
import com.caio.apiprodutos.dto.ProdutoResponseDTO;
import com.caio.apiprodutos.exception.ProdutoNotFoundException;
import com.caio.apiprodutos.mapper.ProdutoMapper;
import com.caio.apiprodutos.model.Produto;
import com.caio.apiprodutos.repository.ProdutoRepository;
import com.caio.apiprodutos.service.ProdutoService;
import com.caio.apiprodutos.validation.ProdutoValidator;

@Service
@SuppressWarnings("null")
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;
    private final ProdutoValidator validator;

    public ProdutoServiceImpl(
            ProdutoRepository repository,
            ProdutoMapper mapper,
            ProdutoValidator validator
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {

        Produto produto = mapper.toEntity(dto);

        validator.validar(produto);

        Produto salvo = repository.save(produto);

        return mapper.toResponseDTO(salvo);
    }

    @Override
    public List<ProdutoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        return mapper.toResponseDTO(produto);
    }

    @Override
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());

        validator.validar(produto);

        Produto salvo = repository.save(produto);
        return mapper.toResponseDTO(salvo);
    }

    @Override
    public void remover(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));
        repository.delete(produto);
    }
}
