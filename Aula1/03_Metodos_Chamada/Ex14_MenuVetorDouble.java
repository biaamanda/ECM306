// ENUNCIADO:
// Utilizando programação por chamadas de métodos, elaborar um programa, em linguagem Java, capaz de carregar, via teclado, os 10 elementos de um vetor do tipo double. Após isto, apresentar ao operador um menu contendo 3 opções e aguardar a digitação da opção por ele escolhida: Digitar ‘+’ para apresentar o próximo elemento do vetor; Digitar ‘-‘ para apresentar o elemento anterior do vetor; Digitar ‘. ‘ para sair. Dependendo da opção digitada, apresentar o respectivo elemento do vetor carregado. Obs.: O 1º elemento a ser apontado é o de índice 0;

import java.util.Scanner;

public class Ex14_MenuVetorDouble {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] values = new double[10];
        loadVector(values, scanner);

        int index = 0;
        char option;

        do {
            System.out.println("\nCurrent index: " + index);
            System.out.println("Valor: " + values[index]);
            System.out.println("Menu:");
            System.out.println(" +  -> proximo elemento");
            System.out.println(" -  -> elemento anterior");
            System.out.println(" .  -> exit");
            System.out.print("Proxima opcao: ");

            option = scanner.next().charAt(0);

            index = updateIndex(option, index, values.length);

        } while (option != '.');

        System.out.println("Programa finalizado.");
        scanner.close();
    }

    public static void loadVector(double[] values, Scanner scanner) {
        for (int i = 0; i < values.length; i++) {
            System.out.print("Valor do indice " + i + ": ");
            values[i] = scanner.nextDouble();
        }
    }

    public static int updateIndex(char option, int index, int size) {
        if (option == '+') {
            if (index < size - 1) {
                index++;
            } else {
                System.out.println("Ja no ultimo elemento.");
            }
        } else if (option == '-') {
            if (index > 0) {
                index--;
            } else {
                System.out.println("Ja no primeiro elemento.");
            }
        }
        return index;
    }
}