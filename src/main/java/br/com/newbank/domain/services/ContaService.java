package br.com.newbank.domain.services;

import br.com.newbank.database.entity.Conta;
import br.com.newbank.database.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    public void saveOrUpdate(Conta conta)
    {
        contaRepository.save(conta);
    }
}
