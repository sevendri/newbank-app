package br.com.newbank.services.impl;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Pessoa;
import br.com.newbank.domain.entities.perfil.ContaPerfil;
import br.com.newbank.services.ConsultarSaldoService;

import java.math.BigDecimal;

public class ConsultarSaldoServiceImpl implements ConsultarSaldoService {

    @Override
    public BigDecimal consultarSaldo(Conta<ContaPerfil, Pessoa> conta) {
        return conta.getSaldo();
    }
}
