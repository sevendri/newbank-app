package br.com.newbank.domain.entities;

import java.math.BigDecimal;

public class ContaCorrente extends Conta{

    @Override
    public BigDecimal calcularTaxa(Pessoa pessoa){
        if (pessoa.getClass().getName().contains("PessoaJuridica"))
            return new BigDecimal(0.005);
        else
            return new BigDecimal(0.0);
    }
}
