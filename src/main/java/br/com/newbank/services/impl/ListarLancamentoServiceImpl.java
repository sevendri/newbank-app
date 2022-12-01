package br.com.newbank.services.impl;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.entities.Pessoa;
import br.com.newbank.domain.entities.perfil.ContaPerfil;
import br.com.newbank.services.ListarLancamentoService;

public class ListarLancamentoServiceImpl implements ListarLancamentoService {

    @Override
    public String listarLancamentos(Conta<ContaPerfil, Pessoa> conta) {
        String lancamento = "";
        for (int i = 0; i < conta.getListaLancamentos().size(); i++){
            lancamento = lancamento + "\n Lançamento de " + conta.getListaLancamentos().get(i).getNome() + " - Valor: " + conta.getListaLancamentos().get(i).getValor();
        }
        return lancamento;
    }
}
