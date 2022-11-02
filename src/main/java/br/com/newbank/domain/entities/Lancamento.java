package br.com.newbank.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class Lancamento {

    private String nome;
    private double valor;
    private Date date;
}
