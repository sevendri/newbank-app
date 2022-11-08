package br.com.newbank.domain.entities;

public class ContaInvestimento extends Conta{

    @Override
    public double calcularRendimento(Pessoa pessoa){
        if (pessoa.getClass().getName().contains("PessoaJuridica"))
            return 0.035;
        else
            return 0.015;
    }

    @Override
    public double calcularTaxa(Pessoa pessoa){
        if (pessoa.getClass().getName().contains("PessoaJuridica"))
            return 0.005;
        else
            return 0.0;
    }

}
