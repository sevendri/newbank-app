package br.com.newbank.services;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Pessoa;
import br.com.newbank.domain.entities.perfil.ContaPerfil;
import br.com.newbank.domain.enums.TipoConta;
import br.com.newbank.domain.enums.TipoPessoa;

public interface AbrirContaService {

    public Conta<ContaPerfil, Pessoa> abrirConta(String nome, String endereco, TipoPessoa tipo_pessoa, TipoConta tipo_conta);

}

