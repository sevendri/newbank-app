package br.com.newbank.domain.entities;
import lombok.*;

import java.util.ArrayList;
import java.util.UUID;

@Data
public abstract class Conta {

    private UUID idConta;
    private Pessoa pessoa;
    private double saldo;
    private ArrayList<Lancamento> listaLancamentos  = new ArrayList<Lancamento>();

    public double calcularRendimento(Pessoa pessoa){
        return 0.0;
    }

    public double atualizarSaldo(double valor){
        saldo = saldo + valor;
        return saldo;
    }

    public double calcularTaxa(Pessoa pessoa){
        return 0.0;
    }

}
