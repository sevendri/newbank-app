package br.com.newbank.services;

import br.com.newbank.domain.entities.Conta;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransferirService {
    public boolean transferir(Conta conta, BigDecimal valor, UUID id_conta_transferencia);

}
