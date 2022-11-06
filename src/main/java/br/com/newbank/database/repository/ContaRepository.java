package br.com.newbank.database.repository;

import br.com.newbank.database.entity.Conta;
import org.springframework.data.repository.CrudRepository;

public interface ContaRepository extends CrudRepository<Conta, Integer> {
}
