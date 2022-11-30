package br.com.newbank.services.impl;

import br.com.newbank.domain.entities.*;
import br.com.newbank.domain.enums.TipoConta;
import br.com.newbank.domain.enums.TipoPessoa;
import br.com.newbank.services.ServicosConta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ServicosContaImpl implements ServicosConta {

    public Conta abrirConta(String nome, String endereco, TipoPessoa tipo_pessoa, TipoConta tipo_conta){
        Pessoa pessoa;
        Conta conta;

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

        return conta;
    }

    public BigDecimal consultarSaldo(Conta conta) {
        return conta.getSaldo();
    }

    public void depositar(Conta conta, BigDecimal valor){
        LocalDateTime ldt = LocalDateTime.now();
        Lancamento lancamento = new Lancamento("Deposito", valor, ldt);
        conta.getListaLancamentos().add(lancamento);
        BigDecimal ValorRendimento = new BigDecimal("0.00");
        if (conta.getTaxaRendimento().compareTo(new BigDecimal("0.00")) > 0) {
            ValorRendimento = valor.multiply(conta.getTaxaRendimento());
            Lancamento lancamentoRendimento = new Lancamento("Rendimento Deposito", ValorRendimento , ldt);
            conta.getListaLancamentos().add(lancamentoRendimento);
        }
        conta.setSaldo(conta.getSaldo().add(ValorRendimento.add(valor)));
    }
    public boolean sacar(Conta conta, BigDecimal valor){

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

    public boolean transferir(Conta conta, BigDecimal valor, UUID id_conta_tranferencia){
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
    public void investir(Conta conta, BigDecimal valor) {
        LocalDateTime ldt = LocalDateTime.now();
        Lancamento lancamento = new Lancamento("Investimento", valor, ldt);
        conta.getListaLancamentos().add(lancamento);
        BigDecimal ValorRendimento = new BigDecimal(0);
        if (conta.getTaxaRendimento().compareTo(new BigDecimal(0)) > 0.0) {
            ValorRendimento = valor.multiply(conta.getTaxaRendimento());
            Lancamento lancamentoRendimento = new Lancamento("Rendimento Investimento", ValorRendimento, ldt);
            conta.getListaLancamentos().add(lancamentoRendimento);
        }

        conta.setSaldo(conta.getSaldo().add(ValorRendimento.add(valor)));
    }

    public String listarLancamentos(Conta conta) {
        String lancamento = "";
        for (int i = 0; i < conta.getListaLancamentos().size(); i++){
            lancamento = lancamento + "\n Lançamento de " + conta.getListaLancamentos().get(i).getNome() + " - Valor: " + conta.getListaLancamentos().get(i).getValor();
        }
        return lancamento;
    }


}
