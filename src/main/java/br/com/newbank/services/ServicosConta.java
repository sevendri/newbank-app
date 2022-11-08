package br.com.newbank.services;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.enuns.TipoConta;
import br.com.newbank.domain.enuns.TipoPessoa;

import java.util.UUID;

public interface ServicosConta {

    public Conta abrirConta(String nome, String endereco, TipoPessoa tipo_pessoa, TipoConta tipo_conta);
    public double consultarSaldo(Conta conta);
    public void depositar(Conta conta, double valor);
    public boolean sacar(Conta conta, double valor);
    public boolean transferir(Conta conta, double valor, UUID  id_conta_tranferencia);
    public void investir(Conta conta, double valor);
    public String listarLancamentos(Conta conta);


}
