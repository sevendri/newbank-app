package br.com.newbank.domain.enuns;

public enum TipoConta {
    CORRENTE(1),
    POUPANCA(2),
    INVESTIMENTO(3);

    public int tipo_conta;
    TipoConta(int tipo) {
        tipo_conta = tipo;
    }

    public int getTipo_conta() {
        return tipo_conta;
    }
}
