package br.com.newbank.domain.entities;
import lombok.*;

import java.util.UUID;

@Data
public abstract class  Pessoa {

    private UUID idCliente;
    private String nome;
    private String endereco;

}

