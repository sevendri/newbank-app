package br.com.newbank.services;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Pessoa;
import br.com.newbank.domain.entities.perfil.ContaPerfil;

import java.math.BigDecimal;

public interface ConsultarSaldoService {
    public BigDecimal consultarSaldo(Conta<ContaPerfil, Pessoa> conta);
}
