package br.com.newbank;

import br.com.newbank.domain.entities.Conta;
import br.com.newbank.domain.enuns.TipoConta;
import br.com.newbank.domain.enuns.TipoPessoa;
import br.com.newbank.services.ServicosConta;
import br.com.newbank.services.ServicosContaImpl;

import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Aplicacao {

    public static void main(String[] args) {

         Scanner sc = new Scanner(System.in);

        System.out.println("Bem vindo ao Newbank");
        // passar infos da abertura da conta
        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();

        System.out.println("Digite seu endereço:");
        String endereco = sc.nextLine();

        boolean validaEntradaTipoPessoa = false;
        int tipo_pessoa = 0;

        while (!validaEntradaTipoPessoa){
            System.out.println("Digite o numero da opção escolhida: : \n 1 - Pessoa Fisica \n 2 - Pessoa Juridica");
            tipo_pessoa = Integer.parseInt(sc.nextLine());

            try {
                if(validarEntrada(String.valueOf(tipo_pessoa), new String[] {"1", "2"})){
                    validaEntradaTipoPessoa = true;
                }
            }catch (Exception exception){
                System.out.println("Erro: " + exception.toString());
                System.out.println("Tente novamente");
            }

        }

        TipoPessoa tipoPessoa;
        if (tipo_pessoa == 1)
            tipoPessoa = TipoPessoa.FISICA;
       else
            tipoPessoa = TipoPessoa.JURIDICA;


        boolean valida = false;

        int tipo_conta = 0;
        while (!valida) {
            System.out.println("Digite o numero da opção escolhida:  \n 1 - Conta Corrente \n 2 - Conta Poupanca \n 3 - Conta Investimento");

            try {

                tipo_conta = Integer.parseInt(sc.nextLine());
                if(validarEntrada(String.valueOf(tipo_conta), new String[]{"1", "2", "3"})){

                    if ((tipo_conta == 2 && tipoPessoa.getTipo_conta() == 2))
                        System.out.println("Tipo de conta inválida pra pessoa Juridica");
                    else
                       valida = true;
                }

            }catch (Exception exception){
                System.out.println("O valor informado precisa ser numérico \nTente novamente...");
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
        Conta conta = servicosConta.abrirConta(nome, endereco, tipoPessoa, tipoConta);
        System.out.println("Conta aberta : " + conta.toString());

        int operacao = 0;
        // mensagem pra escolher operacoes da conta

        while (operacao != 6){
            //operacoes na conta

            boolean validaEntradaTipoOperacao = false;

            while (!validaEntradaTipoOperacao){

                System.out.println("Digite o numero da operação abaixo: \n 1 - DEPOSITO \n 2 - SAQUE \n 3 - INVESTIMENTO \n 4 - SALDO \n 5 - TRANSFERIR \n 6 - SAIR");

                try {
                    operacao = Integer.parseInt(sc.nextLine());

                    if(validarEntrada(String.valueOf(operacao), new String[]{"1", "2", "3", "4", "5", "6"})){
                        validaEntradaTipoOperacao = true;
                    }

                }catch (Exception exception){
                    System.out.println("O valor informado precisa ser numérico \nTente novamente...");
                }

            }

            double valor;
            boolean validaValorOperacao = false;


            switch(operacao) {
                case 1:  //DEPOSITO

                    while (!validaValorOperacao){

                        try {
                            System.out.println("Digite o valor do DEPOSITO: ");
                            valor = Double.parseDouble(sc.nextLine());
                            servicosConta.depositar(conta, valor);

                            validaValorOperacao = true;

                        }catch (Exception exception){
                            System.out.println("O valor informado precisa ser numérico \nTente novamente...");
                        }

                    }

                    System.out.println("Deposito efetuado");
                    System.out.println("Saldo atual: " + conta.getSaldo());
                    break;
                case 2: // SAQUE

                    while (!validaValorOperacao){

                        try {
                            System.out.println("Digite o valor do SAQUE: ");
                            valor = (Double.parseDouble(sc.nextLine())) * -1;
                            if(servicosConta.sacar(conta, valor)){
                                System.out.println("Saque efetuado");
                            }else{
                                System.out.println("Sem saldo pro saque");
                            }

                            validaValorOperacao = true;

                        }catch (Exception exception){
                            System.out.println("O valor informado precisa ser numérico \nTente novamente...");
                        }

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

                    while (!validaValorOperacao){

                        try {
                            System.out.println("Digite o valor do TRANSFERENCIA: ");
                            valor = (Double.parseDouble(sc.nextLine())) * -1;
                            System.out.println("Digite o ID da conta para TRANSFERENCIA: ");
                            UUID id_conta = UUID.fromString(sc.nextLine());
                            if(servicosConta.transferir(conta, valor, id_conta)){
                                System.out.println("Saldo efetuado");
                            }else{
                                System.out.println("Sem saldo pro saque");
                            }

                            validaValorOperacao = true;

                        }catch (Exception exception){
                            System.out.println("O valor informado precisa ser numérico \nTente novamente...");
                        }

                    }

                    System.out.println("Saldo atual: " + conta.getSaldo());
                    break;
                default:
                    operacao = 6;
            }
        }
    }

    public static boolean validarEntrada(String entrada, String[] rangeEntrada){

        boolean valorEntradaPermitido = Arrays.stream(rangeEntrada).anyMatch(entrada::equals);

        if(!valorEntradaPermitido){
            System.out.println("Tente novamente. Informe um valor válido");
        }
        return valorEntradaPermitido;
    }
}