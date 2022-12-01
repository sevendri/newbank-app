package br.com.newbank.services;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Pessoa;
import br.com.newbank.domain.entities.perfil.ContaPerfil;

public interface ListarLancamentoService {

    public String listarLancamentos(Conta<ContaPerfil, Pessoa> conta);

}
