package br.com.newbank.domain.entities;
import br.com.newbank.domain.enuns.TipoConta;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Conta {

    private UUID idConta;
    private Cliente cliente;
    private TipoConta tipoConta;
    private double saldo;
    private ArrayList<Lancamento> listaLancamentos  = new ArrayList<Lancamento>();

    public double calcularInvestimento(double valorDeposito, double rendimento){
        return valorDeposito * rendimento;
    }

    public double atualizaSaldo(double valor){
        saldo = saldo + valor;
        return saldo;
    }

}
