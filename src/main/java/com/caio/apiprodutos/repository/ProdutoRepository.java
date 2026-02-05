package com.caio.apiprodutos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caio.apiprodutos.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
