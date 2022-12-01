package br.com.newbank.services.impl;

import br.com.newbank.domain.entities.*;
import br.com.newbank.domain.entities.perfil.ContaPerfil;
import br.com.newbank.domain.enums.TipoConta;
import br.com.newbank.domain.enums.TipoPessoa;
import br.com.newbank.services.AbrirContaService;
import br.com.newbank.services.MovimentarContaService;

import java.math.BigDecimal;
import java.util.UUID;

public class AbrirContaServiceImpl implements AbrirContaService {

    @Override
    public Conta<ContaPerfil, Pessoa> abrirConta(String nome, String endereco, TipoPessoa tipo_pessoa, TipoConta tipo_conta) {
        Pessoa pessoa;
        Conta conta;
        MovimentarContaService movimentarContaService = new MovimentarContaServiceImpl();

        if (tipo_pessoa.equals(TipoPessoa.FISICA))
            pessoa = new PessoaFisica();
        else
            pessoa = new PessoaJuridica();

        pessoa.setIdCliente(UUID.randomUUID());
        pessoa.setNome(nome);
        pessoa.setEndereco(endereco);

        if (tipo_conta.equals(TipoConta.CORRENTE)) {
            conta = new ContaCorrente();
        }else if(tipo_conta.equals(TipoConta.POUPANCA)) {
            conta = new ContaPoupanca();
        }else {
            conta = new ContaInvestimento();
        }

        conta.setIdConta(UUID.randomUUID());
        conta.setPessoa(pessoa);
        conta.setSaldo(new BigDecimal(0));
        conta.setTaxa(movimentarContaService.calcularTaxa(conta, tipo_pessoa));
        conta.setRendimento(movimentarContaService.calcularRendimento(conta, tipo_pessoa));

        return conta;
    }
}
