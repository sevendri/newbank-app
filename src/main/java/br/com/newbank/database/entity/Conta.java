package br.com.newbank.database.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table
@Data
public class Conta {

    @Id
    @Column
    private int id_conta;
    @Column
    private String nome_cliente;
    @Column
    private String cpf_cnpj;

}
