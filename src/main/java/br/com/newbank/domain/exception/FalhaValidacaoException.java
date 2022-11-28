package br.com.newbank.domain.exception;

public class FalhaValidacaoException extends RuntimeException {

    private final String codigo;
    private final String descricao;

    protected FalhaValidacaoException(String codigo, String descricao) {
        super(formatar(codigo, descricao));
        this.codigo = codigo;
        this.descricao = descricao;
    }

    private static String formatar(String codigo, String descricao) {
        return codigo.concat(":").concat(descricao);
    }
}
