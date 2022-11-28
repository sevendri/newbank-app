package br.com.newbank.services.impl;

import br.com.newbank.domain.entities.*;
import br.com.newbank.domain.enums.TipoPessoa;
import br.com.newbank.services.MovimentarContaService;

import java.math.BigDecimal;

public class MovimentarContaServiceImpl implements MovimentarContaService {

    public BigDecimal calcularRendimento(Conta conta, TipoPessoa tipoPessoa){

        return new BigDecimal(0.2);
    }

    public BigDecimal calcularTaxa(Conta conta, TipoPessoa tipoPessoa){

        return new BigDecimal(0.005);
//        if (pessoa instanceof PessoaJuridica)
//            if (conta instanceof ContaCorrente) {
//                return new BigDecimal(0.005);
//            } else if (conta instanceof ContaPoupanca) {
//                return new BigDecimal(0.0);
//            } else {
//                return new BigDecimal(0.005);
//            }
//        else {
//            if (conta instanceof ContaCorrente) {
//                return new BigDecimal(0.0);
//            } else if (conta instanceof ContaPoupanca) {
//                return new BigDecimal(0.01);
//            } else {
//                return new BigDecimal(0.0);
//            }
//        }
    }
}
