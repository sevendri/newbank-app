package br.com.newbank.services;

import br.com.newbank.domain.entities.*;
import br.com.newbank.domain.enuns.TipoConta;
import br.com.newbank.domain.enuns.TipoPessoa;

import java.util.Date;
import java.util.UUID;

public class ServicosContaImpl implements ServicosConta{

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
        return conta;
    }

    public double consultarSaldo(Conta conta) {
        return conta.getSaldo();
    }

    public void depositar(Conta conta, double valor){
        Lancamento lancamento = new Lancamento("Deposito", valor, new Date());
        conta.getListaLancamentos().add(lancamento);
        double ValorRendimento = 0;
        if (conta.calcularRendimento(conta.getPessoa()) > 0.0) {
            ValorRendimento = valor * conta.calcularRendimento(conta.getPessoa());
            Lancamento lancamentoRendimento = new Lancamento("Rendimento Deposito", ValorRendimento , new Date());
            conta.getListaLancamentos().add(lancamentoRendimento);
        }

        conta.atualizarSaldo(valor + ValorRendimento);
    }
    public boolean sacar(Conta conta, double valor){

        double taxa = conta.calcularTaxa(conta.getPessoa());
        double valorSaqueTaxa = valor + (valor * taxa);

        if((conta.getSaldo() + valorSaqueTaxa) >= 0) {
            Lancamento lancamento = new Lancamento("Saque", valor, new Date());
            conta.getListaLancamentos().add(lancamento);
            if (taxa > 0.0){
                Lancamento lancamentoTaxa = new Lancamento("Taxa Saque", (valor * taxa), new Date());
                conta.getListaLancamentos().add(lancamentoTaxa);
            }
            conta.atualizarSaldo(valorSaqueTaxa);
            return true;
        }
        return false;
    }

    public boolean transferir(Conta conta, double valor, UUID id_conta_tranferencia){
        double taxa = conta.calcularTaxa(conta.getPessoa());
        double valorTransferenciaTaxa = valor + (valor * taxa);

        if((conta.getSaldo() + valorTransferenciaTaxa) >= 0) {
            Lancamento lancamento = new Lancamento("Transferencia", valor, new Date());
            conta.getListaLancamentos().add(lancamento);
            if (taxa > 0.0) {
                Lancamento lancamentoTaxa = new Lancamento("Taxa Transferencia", (valor * taxa), new Date());
                conta.getListaLancamentos().add(lancamentoTaxa);
            }
            conta.atualizarSaldo(valorTransferenciaTaxa);
            return true;
        }
        return false;
    }
    public void investir(Conta conta, double valor) {
        Lancamento lancamento = new Lancamento("Investimento", valor, new Date());
        conta.getListaLancamentos().add(lancamento);
        double ValorRendimento = 0;
        if (conta.calcularRendimento(conta.getPessoa()) > 0.0) {
            ValorRendimento = valor * conta.calcularRendimento(conta.getPessoa());
            Lancamento lancamentoRendimento = new Lancamento("Rendimento Investimento", ValorRendimento, new Date());
            conta.getListaLancamentos().add(lancamentoRendimento);
        }
        conta.atualizarSaldo(valor + ValorRendimento);
    }

    public String listarLancamentos(Conta conta) {
        String lancamento = "";
        for (int i = 0; i < conta.getListaLancamentos().size(); i++){
            lancamento = lancamento + "\n Lançamento de " + conta.getListaLancamentos().get(i).getNome() + " - Valor: " + conta.getListaLancamentos().get(i).getValor();
        }
        return lancamento;
    }


}
