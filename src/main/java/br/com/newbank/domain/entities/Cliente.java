package br.com.newbank.domain.entities;
import lombok.*;
import java.util.UUID;

@Data
public class Cliente extends Pessoa {

    private UUID idCliente;

}
