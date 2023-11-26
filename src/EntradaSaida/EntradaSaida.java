package EntradaSaida;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.util.ArrayList;


public class EntradaSaida {
    public static int menuInicialCriaConta(){
        String[] opcoes = {"Criar uma nova conta","Sair"};
        JComboBox<String> menu = new JComboBox<>(opcoes);

        JOptionPane.showMessageDialog(null, menu, "Selecione a opção deseja", JOptionPane.DEFAULT_OPTION);
        return menu.getSelectedIndex();
    }
    public static int menuInicial(){
        String[] opcoes = {"Cria uma nova conta","Depositar","Sacar","Saldo","Info da Conta","Extrato","Extrato de Depósitos","Extrato de Saques","Sair"};
        JComboBox<String> menu = new JComboBox<>(opcoes);

        JOptionPane.showMessageDialog(null, menu, "Selecione a opção desejada", JOptionPane.DEFAULT_OPTION);
        return menu.getSelectedIndex();
    }

    public static String coletaNomeTitularConta(){
        return JOptionPane.showInputDialog(null, "Qual o seu nome completo?:", "Criar uma nova conta:", JOptionPane.DEFAULT_OPTION);
    }

    public static int coletaTipoContaTitular(){
        String[] opcoes = {"Conta Poupança","Conta Corrente"};
        JComboBox<String> menu = new JComboBox<>(opcoes);

        JOptionPane.showMessageDialog(null, menu, "Qual o tipo de conta que você deseja?", JOptionPane.DEFAULT_OPTION);
        return menu.getSelectedIndex();
    }

    public static double coletaSaldoInicial(){
        String[] opcoes = {"R$100,00","R$500,00","R$1000,00","Outro valor"};
        JComboBox<String> menu = new JComboBox<>(opcoes);

        JOptionPane.showMessageDialog(null, menu, "Selecione ou digite o valor do seu primeiro depósito", JOptionPane.DEFAULT_OPTION);
        return switch (menu.getSelectedIndex()) {
            case 0 -> 100.00;
            case 1 -> 500.00;
            case 2 -> 1000.00;
            default -> Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do seu depósito:"));
        };
    }

    public static String coletaCpfTitular(){
        return JOptionPane.showInputDialog(null, "Digite o seu CPF:");
    }

    public static void validaCPF(){
        JOptionPane.showMessageDialog(null, "O seu cpf deve conter no maximo 11 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static int selecionaConta(ArrayList contas){
        String[] opcoes = new String[contas.size()];

        for(int x=0;x<contas.size();x++){
            opcoes[x] = contas.get(x).toString();
        }

        JComboBox menu = new JComboBox(opcoes);
        JOptionPane.showMessageDialog(null, menu, "Selecione a Conta que deseja:", JOptionPane.PLAIN_MESSAGE);

        return menu.getSelectedIndex();
    }

    public static double coletaDeposito(){
        double deposito;

        do{
            deposito = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor que deseja depositar", "Deposito", JOptionPane.PLAIN_MESSAGE));
            if(deposito<=0) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }while (deposito<=0);

        return deposito;
    }
    public static void mostraSaldo(double saldo, String nome){
        JOptionPane.showMessageDialog(null, "Saldo Atual: R$" + saldo, "Titular da Conta: " + nome, JOptionPane.PLAIN_MESSAGE);
    }

    public static double coletaSaque(){
        double saque;

        do{
            saque = Double.parseDouble(JOptionPane.showInputDialog(null, "Quanto você deseja sacar?", "Saque", JOptionPane.PLAIN_MESSAGE));
            if(saque<=0) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }while (saque<=0);

        return -saque;
    }

    public static void mensagemErro(double total, double saldoAtual){
        JOptionPane.showMessageDialog(null, "Você não pode sacar " + saldoAtual + ", por que seu saldo será de: R$" + total);
    }

    public static void mostraInfoConta(String nome, int tipo, double saldo, String cpf){
        String tipos = "";

        if(tipo == 0){
            tipos = "Conta poupança";
        }
        else{
            tipos = "Conta corrente";
        }


        JOptionPane.showMessageDialog(null, "Nome: " + nome + "\n" + "Tipo da Conta: " + tipos + "\n" +
                        "Saldo Atual: R$" + saldo + "\n" + "CPF: " + cpf, "Titular da Conta: " + nome,
                JOptionPane.PLAIN_MESSAGE);
    }

    public static void mostraMovimentacao(ArrayList<String> movimentacoes, String nome){
        String[] totalMenu = new String[movimentacoes.size()];

        for(int x=0; x<movimentacoes.size();x++){
            totalMenu[x] = movimentacoes.get(x) + "\n";
        }

        JOptionPane.showMessageDialog(null, totalMenu, "Conta: " + nome, JOptionPane.PLAIN_MESSAGE);
    }


}
