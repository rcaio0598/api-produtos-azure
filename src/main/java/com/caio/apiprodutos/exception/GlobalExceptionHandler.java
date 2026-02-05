package com.caio.apiprodutos.exception;

import com.caio.apiprodutos.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidacao(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(e -> e.getDefaultMessage())
                .orElse("Dados inválidos");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(msg, HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProdutoNotFound(ProdutoNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErrorResponseDTO> handleRegraNegocio(RegraNegocioException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
