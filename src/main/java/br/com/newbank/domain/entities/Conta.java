package br.com.newbank.domain.entities;
import br.com.newbank.domain.entities.perfil.ContaPerfil;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@Data
public abstract class Conta  <T extends ContaPerfil, U extends Pessoa> {

    private UUID idConta;
    private Pessoa pessoa;
    private BigDecimal saldo;
    private ArrayList<Lancamento> listaLancamentos  = new ArrayList<Lancamento>();
    private BigDecimal taxa;
    private BigDecimal rendimento;

}
