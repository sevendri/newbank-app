package br.com.newbank.services;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Pessoa;
import br.com.newbank.domain.enums.TipoPessoa;

import java.math.BigDecimal;

public interface MovimentarContaService {

    public BigDecimal calcularRendimento(Conta conta, TipoPessoa tipoPessoa);

    public BigDecimal calcularTaxa(Conta conta, TipoPessoa tipoPessoa);
}
