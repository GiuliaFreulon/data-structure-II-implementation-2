import java.util.Scanner;
import Questoes.*;
import java.util.Random;

//DISCIPLINA: ESTRUTURA DE DADOS II SEGUNDA ATIVIDADE PRÁTICA
//ALUNA:
//Giulia de Araujo Freulon

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean loopMain = true;
        boolean loopPrimeira = false;
        boolean loopSegunda = false;
        int tamanho = 0;

        while(loopMain) {
            System.out.println("Selecione uma opção:\n"+
                    " 1 - 1ª Questão\n" +
                    " 2 - 2ª Questão\n"
            );
            int userInput0 = input.nextInt();

            switch (userInput0) {
                case 1 -> {
                    long comparacoes = 0L;
                    long movimentacoes = 0L;

                    System.out.println(
                            " 1 - Inserir 1.000 itens\n" +
                            " 2 - Inserir 100.000 itens\n" +
                            " 3 - Inserir 1.000.000 itens\n"
                    );
                    int userInput1 = input.nextInt();

                    switch (userInput1) {
                        case 1 -> {
                            tamanho = 1000;
                        }
                        case 2 -> {
                            tamanho = 100000;
                        }
                        case 3 -> {
                            tamanho = 1000000;
                        }

                        default -> System.out.println("Insira um valor válido\n");
                    }

                    loopPrimeira = true;

                    Primeira primeira = new Primeira(tamanho);
                    Random random = new Random();

                    long tempoinicial = System.currentTimeMillis();
                    while (tamanho > 0) {
                        primeira.put(random.nextInt(0, tamanho));
                        comparacoes += primeira.getComparacoes();
                        movimentacoes += primeira.getMovimentacoes();
                        tamanho--;
                    }
                    long tempofinal = System.currentTimeMillis();
                    long tempoDeExecucao = tempofinal - tempoinicial;

                    System.out.println("\n|Tempo de Execução: " + tempoDeExecucao + "ms" +
                                       "\n|Total de Movimentações: "     + movimentacoes +
                                       "\n|Total de Comparações: "       + comparacoes + "\n");

                    while (loopPrimeira) {
                        switch (userInput0) {
                            case 1 -> {
                                System.out.println(
                                        " 1 - Inserir chave\n" +
                                        " 2 - Encontrar valores pela chave\n" +
                                        " 3 - Trocar questão\n"
                                );
                                int userInput2 = input.nextInt();

                                switch (userInput2) {
                                    case 1 -> {
                                        System.out.println("Insira a chave desejada: ");
                                        int K = input.nextInt();
                                        primeira.put(K);

                                    }

                                    case 2 -> {
                                        System.out.println("Insira a chave desejada: ");
                                        int K = input.nextInt();
                                        primeira.findAll(K);
                                    }

                                    case 3 -> loopPrimeira = false;

                                    default -> System.out.println("Insira um valor válido\n");
                                }
                            }
                        }
                    }
                }


                case 2 -> {
                    loopSegunda = true;

                    while (loopSegunda == true) {
                        System.out.println(
                                " 1 - Procurar plágio\n" +
                                " 2 - Trocar questão\n"
                        );
                        int userInput3 = input.nextInt();
                        switch (userInput3) {
                            case 1 -> {

                                System.out.println("Quantos documentos você deseja inserir?");
                                int numeroDoc = input.nextInt();
                                input.nextLine();

                                System.out.println("Insira o nome dos documentos que deseja carregar no sistema:");
                                String[] nomesDoc = new String[numeroDoc];
                                for (int i = 0; i < numeroDoc; i++) {
                                    System.out.print("Documento" + i + ": ");
                                    nomesDoc[i] = input.nextLine();
                                }

                                System.out.println("Insira o nome do documento que deseja verificar o plágio:");
                                String nomeDoc = input.nextLine();

                                System.out.println("Insira o número de palavras consecutivas necessárias para detectar o plágio:");
                                int m = input.nextInt();

                                System.out.println(
                                        "\nComo deseja procurar o plágio?\n" +
                                                "1 - Letra A\n" +
                                                "2 - Letra B\n"
                                );
                                int userInput4 = input.nextInt();

                                Segunda segunda = new Segunda();

                                switch (userInput4) {
                                    case 1 -> {
                                        segunda.LetraA(nomesDoc, nomeDoc, m);
                                    }

                                    case 2 -> {
                                        segunda.LetraB(nomesDoc, nomeDoc, m);
                                    }

                                    default -> System.out.println("Insira um valor válido\n");
                                }
                            }

                            case 2 -> {
                                loopSegunda = false;
                            }
                        }
                    }
                }

                default -> System.out.println("Insira um valor válido\n");
            }
        }
    }
}
