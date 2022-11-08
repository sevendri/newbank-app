package br.com.newbank.domain.entities;

public class ContaCorrente extends Conta{

    @Override
    public double calcularTaxa(Pessoa pessoa){
        if (pessoa.getClass().getName().contains("PessoaJuridica"))
            return 0.005;
        else
            return 0.0;
    }
}
