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

    public BigDecimal calcularRendimento(Pessoa pessoa){
        return new BigDecimal(0);
    }

    public BigDecimal atualizarSaldo(BigDecimal valor){
        if (this.saldo==null) {
            this.saldo = new BigDecimal(0);
        }
        saldo = saldo.add(valor);
        return saldo;
    }

    public BigDecimal calcularTaxa(Pessoa pessoa){
        return new BigDecimal(0.0);
    }

}
