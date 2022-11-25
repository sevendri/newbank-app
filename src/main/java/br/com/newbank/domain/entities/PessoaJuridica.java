package br.com.newbank.domain.entities;

import lombok.Data;

@Data
public class PessoaJuridica extends Pessoa{
    private String cnpj;
}
