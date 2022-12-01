package br.com.newbank.services.impl;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Lancamento;
import br.com.newbank.services.AbrirContaService;
import br.com.newbank.services.DepositarService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositarServiceImpl implements DepositarService {
    public void depositar(Conta conta, BigDecimal valor){
        LocalDateTime ldt = LocalDateTime.now();
        Lancamento lancamento = new Lancamento("Deposito", valor, ldt);
        conta.getListaLancamentos().add(lancamento);
        BigDecimal ValorRendimento = new BigDecimal("0.00");
        if (conta.getRendimento().compareTo(new BigDecimal("0.00")) > 0) {
            ValorRendimento = valor.multiply(conta.getRendimento());
            Lancamento lancamentoRendimento = new Lancamento("Rendimento Deposito", ValorRendimento , ldt);
            conta.getListaLancamentos().add(lancamentoRendimento);
        }
        conta.setSaldo(conta.getSaldo().add(ValorRendimento.add(valor)));
    }
}
