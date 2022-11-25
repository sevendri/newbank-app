package br.com.newbank.services;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.enums.TipoConta;
import br.com.newbank.domain.enums.TipoPessoa;

import java.math.BigDecimal;
import java.util.UUID;

public interface ServicosConta {

    public Conta abrirConta(String nome, String endereco, TipoPessoa tipo_pessoa, TipoConta tipo_conta);
    public BigDecimal consultarSaldo(Conta conta);
    public void depositar(Conta conta, BigDecimal valor);
    public boolean sacar(Conta conta, BigDecimal valor);
    public boolean transferir(Conta conta, BigDecimal valor, UUID  id_conta_tranferencia);
    public void investir(Conta conta, BigDecimal valor);
    public String listarLancamentos(Conta conta);


}
