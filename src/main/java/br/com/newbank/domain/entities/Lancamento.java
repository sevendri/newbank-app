package br.com.newbank.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Lancamento {

    private String nome;
    private BigDecimal valor;
    private LocalDateTime date;
}
