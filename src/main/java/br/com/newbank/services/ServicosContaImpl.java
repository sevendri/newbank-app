package br.com.newbank.services;

import br.com.newbank.domain.entities.*;
import br.com.newbank.domain.enuns.TipoConta;

import java.util.Date;
import java.util.UUID;

public class ServicosContaImpl implements ServicosConta{

    public Conta abrirConta( String nome, String endereco, char tipo_pessoa,  TipoConta tipo_conta){
        Cliente cliente = new Cliente();
        cliente.setIdCliente(UUID.randomUUID());
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setTipo_pessoa(tipo_pessoa);
        Conta conta = new Conta();
        if (tipo_conta.equals(TipoConta.CORRENTE)) {
            conta.setTipoConta(TipoConta.CORRENTE);
        }else if(tipo_conta.equals(TipoConta.POUPANCA)) {
             conta.setTipoConta(TipoConta.POUPANCA);
        }else {
             conta.setTipoConta(TipoConta.INVESTIMENTO);
        }
        conta.setIdConta(UUID.randomUUID());
        conta.setCliente(cliente);
        return conta;
    }

    public double consultarSaldo(Conta conta) {
        return conta.getSaldo();
    }

    public void depositar(Conta conta, double valor){
        Lancamento lancamento = new Lancamento("Deposito", valor, new Date());
        conta.getListaLancamentos().add(lancamento);

        if(conta.getTipoConta().equals(TipoConta.POUPANCA)){
            valor = valor + (valor * 0.001);
        } else if (conta.getTipoConta().equals(TipoConta.INVESTIMENTO)) {

            char tipoPessoa = conta.getCliente().getTipo_pessoa();

            if(String.valueOf(tipoPessoa).toUpperCase().equals("F")){
                valor = valor + (valor * 0.015);
            }else {
                valor = valor + (valor * 0.035);
            }

        }

        conta.atualizaSaldo(valor);
    }
    public boolean sacar(Conta conta, double valor){
        if (conta.getCliente().getTipo_pessoa() == 'J')
            valor = valor + (valor * 0.005);

        if((conta.getSaldo() + valor) >= 0) {
            Lancamento lancamento = new Lancamento("Saque", valor, new Date());
            conta.getListaLancamentos().add(lancamento);
            conta.atualizaSaldo(valor);
            return true;
        }
        return false;
    }

    public boolean transferir(Conta conta, double valor, UUID id_conta_tranferencia){
        if (conta.getCliente().getTipo_pessoa() == 'J')
            valor = valor + (valor * 0.005);

        if((conta.getSaldo() + valor) >= 0) {
            Lancamento lancamento = new Lancamento("Transferência", valor, new Date());
            conta.getListaLancamentos().add(lancamento);
            conta.atualizaSaldo(valor);
            return true;
        }
        return false;
    }
    public void investir(Conta conta, double valor){
        if (conta.getCliente().getTipo_pessoa() == 'F' && conta.getTipoConta().equals(TipoConta.INVESTIMENTO))
            valor = valor + (valor * 0.015);
        else if  (conta.getCliente().getTipo_pessoa() == 'F' && conta.getTipoConta().equals(TipoConta.POUPANCA))
            valor = valor + (valor * 0.01);
        else if (conta.getCliente().getTipo_pessoa() == 'J' && conta.getTipoConta().equals(TipoConta.INVESTIMENTO))
            valor = valor + (valor * 0.035);

        Lancamento lancamento = new Lancamento("Investimento", valor, new Date());
        conta.getListaLancamentos().add(lancamento);
        conta.atualizaSaldo(valor);
    }


}
