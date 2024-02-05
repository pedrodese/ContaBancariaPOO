package Controladora;

// Aréa de imports

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Model.*;
import EntradaSaida.*;

public class Controladora {
    Banco banco = new Banco();
    int opcaoSelecionada = 0;
    int index;
    String nome;
    double saldoAtual;
    double saldo;
    public void mostraMenu(){
        // Validação onde é verificado se o Array List possui elementos   
        do
        {
            if(banco.getContas().isEmpty()){
                opcaoSelecionada = EntradaSaida.menuInicialCriaConta();
            }
            else {
                opcaoSelecionada = EntradaSaida.menuInicial();
            }

            switch (opcaoSelecionada)
            {
                case 0: // Criação da Conta
                    Conta conta = new Conta();
                    nome = EntradaSaida.coletaNomeTitularConta();

                    conta.setNomeConta(nome);
                    conta.setTipo(EntradaSaida.coletaTipoContaTitular());
                    do{
                        conta.setCpf(EntradaSaida.coletaCpfTitular());
                        if(conta.getCPF().length() != 11){
                            EntradaSaida.validaCPF();
                        }
                    }while (conta.getCPF().length() != 11);
                    conta.setSaldo(EntradaSaida.coletaSaldoInicial());

                    banco.setNomes(nome);
                    banco.setContas(conta);
                    break;

                case 1: // Deposito
                    if(banco.getContas().isEmpty()){
                        System.exit(0);
                    }
                    Movimentacao movimentacaoDeposito = new Movimentacao();

                    index = EntradaSaida.selecionaConta(banco.getNomes());
                    saldoAtual = EntradaSaida.coletaDeposito();

                    banco.getContas().get(index).setSaldo(saldoAtual);
                    movimentacaoDeposito.setMovimentacao("Deposito: R$" + saldoAtual + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));


                    banco.getContas().get(index).setMovimentacoesDeposito(movimentacaoDeposito.getMovimentacao());
                    banco.getContas().get(index).setMovimentacoesTotais(movimentacaoDeposito.getMovimentacao());
                    EntradaSaida.mostraSaldo(banco.getContas().get(index).getSaldo(), banco.getContas().get(index).getNomeConta());
                    break;

                case 2: // Saque
                    Movimentacao movimentacaoSaque = new Movimentacao();
                    int contador=0;
                    index = EntradaSaida.selecionaConta(banco.getNomes());
                    do {
                        saldoAtual = EntradaSaida.coletaSaque();
                        saldo = banco.getContas().get(index).getSaldo();

                        if((saldo+saldoAtual) > -1000){
                            banco.getContas().get(index).setSaldo(saldoAtual);
                            contador = 1;
                        }
                        else{
                            EntradaSaida.mensagemErro((saldo + saldoAtual), saldoAtual);
                        }
                    }while (contador!=1);

                    movimentacaoSaque.setMovimentacao("Saque: R$" + saldoAtual + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    banco.getContas().get(index).setMovimentacoesSaques(movimentacaoSaque.getMovimentacao());
                    banco.getContas().get(index).setMovimentacoesTotais(movimentacaoSaque.getMovimentacao());
                    EntradaSaida.mostraSaldo(banco.getContas().get(index).getSaldo(), banco.getContas().get(index).getNomeConta());
                    break;

                case 3: // Saldo
                    index = EntradaSaida.selecionaConta(banco.getNomes());

                    EntradaSaida.mostraSaldo(banco.getContas().get(index).getSaldo(), banco.getContas().get(index).getNomeConta());
                    break;

                case 4: // Informações da Conta
                    index = EntradaSaida.selecionaConta(banco.getNomes());
                    String nome = banco.getContas().get(index).getNomeConta();
                    int tipoConta = banco.getContas().get(index).getTipo();
                    double saldo = banco.getContas().get(index).getSaldo();
                    String cpf = banco.getContas().get(index).getCPF();


                    EntradaSaida.mostraInfoConta(nome, tipoConta, saldo, cpf);
                    break;

                case 5: // Extrato
                    index = EntradaSaida.selecionaConta(banco.getNomes());
                    ArrayList<String> movimentacoesTotais = banco.getContas().get(index).getMovimentacoesTotais();

                    EntradaSaida.mostraMovimentacao(movimentacoesTotais, banco.getContas().get(index).getNomeConta());
                    break;

                case 6: // Extrato de Depósitos
                    index = EntradaSaida.selecionaConta(banco.getNomes());
                    ArrayList<String> movimentacoesDeposito = banco.getContas().get(index).getMovimentacoesDeposito();

                    EntradaSaida.mostraMovimentacao(movimentacoesDeposito, banco.getContas().get(index).getNomeConta());
                    break;

                case 7: // Extrato de Saques
                    index = EntradaSaida.selecionaConta(banco.getNomes());
                    ArrayList<String> movimentacoesSaques = banco.getContas().get(index).getMovimentacoesSaques();

                    EntradaSaida.mostraMovimentacao(movimentacoesSaques, banco.getContas().get(index).getNomeConta());
                    break;

                case 8: // Opção de Sair
                    System.exit(0);
                    break;
            }
        }while (true);
    }
}
