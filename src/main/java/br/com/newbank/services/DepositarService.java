package br.com.newbank.services;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Pessoa;
import br.com.newbank.domain.entities.perfil.ContaPerfil;

import java.math.BigDecimal;

public interface DepositarService {

    public void depositar(Conta<ContaPerfil, Pessoa> conta, BigDecimal valor);
}