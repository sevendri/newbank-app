package br.com.newbank.domain.entities;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Data
public abstract class Conta {

    private UUID idConta;
    private Pessoa pessoa;
    private BigDecimal saldo;
    private ArrayList<Lancamento> listaLancamentos  = new ArrayList<Lancamento>();
    private BigDecimal taxa;
    private BigDecimal taxaRendimento;
}
