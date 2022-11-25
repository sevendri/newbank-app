package br.com.newbank.domain.entities;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta{

    @Override
    public BigDecimal calcularRendimento(Pessoa pessoa){
       if (pessoa instanceof PessoaJuridica)
            return new BigDecimal (0.0);
        else
            return new BigDecimal(0.01);
    }
}
