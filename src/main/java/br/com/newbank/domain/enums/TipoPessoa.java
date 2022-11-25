package br.com.newbank.domain.enums;

public enum TipoPessoa {

    FISICA(1),
    JURIDICA(2);


    public int tipo_conta;
    TipoPessoa(int tipo) {
        tipo_conta = tipo;
    }

    public int getTipo_conta() {
        return tipo_conta;
    }
}
