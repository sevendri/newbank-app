package br.com.newbank.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum TipoErroEnum {

    CODIGO("NBA-M_001", "conta.nao.econtrada");

    private String codigo;
    private String descricao;

}
