package com.caio.apiprodutos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.caio.apiprodutos.dto.ProdutoRequestDTO;
import com.caio.apiprodutos.dto.ProdutoResponseDTO;
import com.caio.apiprodutos.exception.ProdutoNotFoundException;
import com.caio.apiprodutos.mapper.ProdutoMapper;
import com.caio.apiprodutos.model.Produto;
import com.caio.apiprodutos.service.ProdutoService;
import com.caio.apiprodutos.validation.ProdutoValidator;

/**
 * Implementação alternativa para demonstrar LSP.
 * Ative com: spring.profiles.active=inmemory
 */
@Service
@Profile("inmemory")
@Primary
public class ProdutoServiceInMemoryImpl implements ProdutoService {

    private final ProdutoMapper mapper;
    private final ProdutoValidator validator;

    private final AtomicLong seq = new AtomicLong(1);
    private final Map<Long, Produto> db = new ConcurrentHashMap<Long, Produto>();

    public ProdutoServiceInMemoryImpl(ProdutoMapper mapper, ProdutoValidator validator) {
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = mapper.toEntity(dto);
        validator.validar(produto);

        Long id = Long.valueOf(seq.getAndIncrement());
        produto.setId(id);
        db.put(id, produto);
        return mapper.toResponseDTO(produto);
    }

    @Override
    public List<ProdutoResponseDTO> listar() {
        List<ProdutoResponseDTO> out = new ArrayList<ProdutoResponseDTO>();
        for (Produto produto : db.values()) {
            out.add(mapper.toResponseDTO(produto));
        }
        return out;
    }

    @Override
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = db.get(id);
        if (produto == null) {
            throw new ProdutoNotFoundException("Produto não encontrado");
        }
        return mapper.toResponseDTO(produto);
    }

    @Override
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = db.get(id);
        if (produto == null) {
            throw new ProdutoNotFoundException("Produto não encontrado");
        }

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        validator.validar(produto);

        db.put(id, produto);
        return mapper.toResponseDTO(produto);
    }

    @Override
    public void remover(Long id) {
        Produto produto = db.remove(id);
        if (produto == null) {
            throw new ProdutoNotFoundException("Produto não encontrado");
        }
    }
}

