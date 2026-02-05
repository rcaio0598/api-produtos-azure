package com.caio.apiprodutos.controller;

import com.caio.apiprodutos.dto.ProdutoRequestDTO;
import com.caio.apiprodutos.dto.ProdutoResponseDTO;
import com.caio.apiprodutos.service.ProdutoCommandService;
import com.caio.apiprodutos.service.ProdutoQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoQueryService queryService;
    private final ProdutoCommandService commandService;

    public ProdutoController(ProdutoQueryService queryService, ProdutoCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return queryService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(queryService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO salvo = commandService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO dto) {
        return commandService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        commandService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
