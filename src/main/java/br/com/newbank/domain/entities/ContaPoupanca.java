package br.com.newbank.domain.entities;

public class ContaPoupanca extends Conta{

    @Override
    public double calcularRendimento(Pessoa pessoa){
       if (pessoa.getClass().getName().contains("PessoaJuridica"))
            return 0.0;
        else
            return 0.01;
    }
}
