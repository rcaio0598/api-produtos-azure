package com.caio.apiprodutos.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.caio.apiprodutos.dto.ProdutoRequestDTO;
import com.caio.apiprodutos.dto.ProdutoResponseDTO;
import com.caio.apiprodutos.exception.ProdutoNotFoundException;
import com.caio.apiprodutos.mapper.ProdutoMapper;
import com.caio.apiprodutos.model.Produto;
import com.caio.apiprodutos.repository.ProdutoRepository;
import com.caio.apiprodutos.validation.ProdutoValidator;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("null")
class ProdutoServiceImplTest {

    @Mock
    ProdutoRepository repository;

    @Mock
    ProdutoMapper mapper;

    @Mock
    ProdutoValidator validator;

    @InjectMocks
    ProdutoServiceImpl service;

    @Test
    void salvar_deveValidarSalvarEMapear() {
        ProdutoRequestDTO req = new ProdutoRequestDTO();
        req.setNome("Café");
        req.setPreco(Double.valueOf(10));

        Produto entity = new Produto();
        entity.setNome("Café");
        entity.setPreco(Double.valueOf(10));

        Produto saved = new Produto();
        saved.setId(Long.valueOf(1));
        saved.setNome("Café");
        saved.setPreco(Double.valueOf(10));

        ProdutoResponseDTO resp = new ProdutoResponseDTO(Long.valueOf(1), "Café", Double.valueOf(10));

        when(mapper.toEntity(req)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toResponseDTO(saved)).thenReturn(resp);

        ProdutoResponseDTO out = service.salvar(req);

        assertNotNull(out);
        assertEquals(Long.valueOf(1), out.getId());
        verify(validator).validar(entity);
        verify(repository).save(entity);
    }

    @Test
    void buscarPorId_quandoNaoExiste_deveLancarNotFound() {
        when(repository.findById(org.mockito.ArgumentMatchers.<Long>eq(Long.valueOf(99))))
                .thenReturn(Optional.<Produto>empty());

        assertThrows(ProdutoNotFoundException.class, () -> service.buscarPorId(Long.valueOf(99)));
    }

    @Test
    void atualizar_quandoNaoExiste_deveLancarNotFoundESemSalvar() {
        ProdutoRequestDTO req = new ProdutoRequestDTO();
        req.setNome("X");
        req.setPreco(Double.valueOf(1));

        when(repository.findById(org.mockito.ArgumentMatchers.<Long>eq(Long.valueOf(99))))
                .thenReturn(Optional.<Produto>empty());

        assertThrows(ProdutoNotFoundException.class, () -> service.atualizar(Long.valueOf(99), req));
        verify(repository, never()).save(org.mockito.ArgumentMatchers.<Produto>any());
    }

    @Test
    void listar_deveRetornarListaMapeada() {
        when(repository.findAll()).thenReturn(Collections.<Produto>emptyList());
        assertEquals(0, service.listar().size());
    }
}

