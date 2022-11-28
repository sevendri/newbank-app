package br.com.newbank.services.impl;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Lancamento;
import br.com.newbank.services.TransferirService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransferirServiceImpl implements TransferirService {

    public boolean transferir(Conta conta, BigDecimal valor, UUID id_conta_transferencia){
        BigDecimal taxa = conta.getTaxa();
        BigDecimal valorTransferenciaTaxa = valor.add(valor.multiply(taxa));

        if((conta.getSaldo().add(valorTransferenciaTaxa).compareTo(new BigDecimal(0))) >= 0) {
            LocalDateTime ldt = LocalDateTime.now();
            Lancamento lancamento = new Lancamento("Transferencia", valor, ldt);
            conta.getListaLancamentos().add(lancamento);
            if (taxa.compareTo(new BigDecimal(0)) > 0.0) {
                Lancamento lancamentoTaxa = new Lancamento("Taxa Transferencia", (valor.multiply(taxa)), ldt);
                conta.getListaLancamentos().add(lancamentoTaxa);
            }
            conta.setSaldo(conta.getSaldo().add(valorTransferenciaTaxa));
            return true;
        }
        return false;
    }
}
