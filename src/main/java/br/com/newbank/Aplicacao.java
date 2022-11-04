package br.com.newbank;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.enuns.TipoConta;
import br.com.newbank.services.ServicosConta;
import br.com.newbank.services.ServicosContaImpl;

import java.util.Scanner;
import java.util.UUID;

public class NewBankApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao Newbank");
        // passar infos da abertura da conta
        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();

        System.out.println("Digite seu endereço:");
        String endereco = sc.nextLine();

        System.out.println("Digite: F - Pessoa Fisica ou J - Pessoa Juridica:");
        char tipo_pessoa = sc.nextLine().toUpperCase().charAt(0);

        boolean valida = true;

        int tipo_conta = 0;
        while (valida) {
            System.out.println("Digite: 1 - Conta Corrente, Digite: 2 - Conta Poupanca, Digite: 3 - Conta Investimento");
            tipo_conta = Integer.parseInt(sc.nextLine());
            if ((tipo_conta == 1 && tipo_pessoa !='F')) {
                System.out.println("Tipo de conta inválida pra pessoa Juridica");
            }
            else {
                if (tipo_conta == 1 || tipo_conta == 2 || tipo_conta == 3) {
                    valida = false;
                }
             }
        }
        TipoConta tipoConta;
        if (tipo_conta == 1)
            tipoConta = TipoConta.CORRENTE;
        else if (tipo_conta == 2)
            tipoConta = TipoConta.POUPANCA;
        else
            tipoConta = TipoConta.INVESTIMENTO;


        ServicosConta servicosConta = new ServicosContaImpl();
        //ajustar enun
        Conta conta = servicosConta.abrirConta(nome, endereco, tipo_pessoa, tipoConta);
        System.out.println("Conta aberta : " + conta.getIdConta());

        int operacao = 0;
        // mensagem pra escolher operacoes da conta

        while (operacao != 6){
            //operacoes na conta
            System.out.println("Digite: 1 - DEPOSITO, Digite: 2 - SAQUE, Digite: 3 - INVESTIMENTO, Digite: 4 - SALDO, Digite: 5 - TRANSFERIR,  Digite: 6 - SAIR");
            operacao = Integer.parseInt(sc.nextLine());

            double valor;
            switch(operacao) {
                case 1:  //DEPOSITO
                    System.out.println("Digite o valor do DEPOSITO: ");
                    valor = Double.parseDouble(sc.nextLine());
                    servicosConta.depositar(conta, valor);
                    System.out.println("Deposito efetuado");
                    System.out.println("Saldo atual: " + conta.getSaldo());
                    break;
                case 2: // SAQUE
                    System.out.println("Digite o valor do SAQUE: ");
                    valor = (Double.parseDouble(sc.nextLine())) * -1;
                   if(servicosConta.sacar(conta, valor)){
                       System.out.println("Saldo efetuado");
                   }else{
                       System.out.println("Sem saldo pro saque");
                   }
                    System.out.println("Saldo atual: " + conta.getSaldo());
                    break;

                case 3: // INVESTIR
                    System.out.println("Digite o valor do INVESTIMENTO: ");
                    valor = Double.parseDouble(sc.nextLine());
                    servicosConta.investir(conta, valor);
                    System.out.println("Investimento efetuado");
                    System.out.println("Saldo atual: " + conta.getSaldo());
                    break;

                case 4: // SALDO
                    System.out.println("Saldo atual: " + servicosConta.consultarSaldo(conta));
                    break;

                case 5: // TRANSFERIR
                    System.out.println("Digite o valor do TRANSFERENCIA: ");
                    valor = (Double.parseDouble(sc.nextLine())) * -1;
                    System.out.println("Digite o ID da conta para TRANSFERENCIA: ");
                    UUID id_conta = UUID.fromString(sc.nextLine());
                    if(servicosConta.transferir(conta, valor, id_conta)){
                        System.out.println("Saldo efetuado");
                    }else{
                        System.out.println("Sem saldo pro saque");
                    }
                    System.out.println("Saldo atual: " + conta.getSaldo());
                    break;
                default:
                    operacao = 6;
            }
        }
    }
}