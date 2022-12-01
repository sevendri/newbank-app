package br.com.newbank.services.impl;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Lancamento;
import br.com.newbank.domain.entities.Pessoa;
import br.com.newbank.domain.entities.perfil.ContaPerfil;
import br.com.newbank.services.SacarService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SacarServiceImpl implements SacarService {

    @Override
    public boolean sacar(Conta<ContaPerfil, Pessoa> conta, BigDecimal valor) {
        BigDecimal taxa = conta.getTaxa();
        BigDecimal valorSaqueTaxa = valor.add(valor.multiply(taxa));

        if((conta.getSaldo().add(valorSaqueTaxa).compareTo(new BigDecimal(0)) >= 0)) {
            LocalDateTime ldt = LocalDateTime.now();
            Lancamento lancamento = new Lancamento("Saque", valor, ldt);
            conta.getListaLancamentos().add(lancamento);
            if (taxa.compareTo(new BigDecimal(0)) > 0.0){
                Lancamento lancamentoTaxa = new Lancamento("Taxa Saque", (valor.multiply(taxa)), ldt);
                conta.getListaLancamentos().add(lancamentoTaxa);
            }
            conta.setSaldo(conta.getSaldo().add(valorSaqueTaxa));
            return true;
        }
        return false;
    }
}
