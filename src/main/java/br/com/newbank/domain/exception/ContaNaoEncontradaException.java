package br.com.newbank.domain.exception;

import static br.com.newbank.domain.enums.TipoErroEnum.CODIGO;

public class ContaNaoEncontradaException extends FalhaValidacaoException {
    public ContaNaoEncontradaException(String codigo) {
        super(
                CODIGO.getCodigo(),
                CODIGO.getDescricao()
        );
    }
}
