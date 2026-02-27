// ENUNCIADO:
// Reescrever exercício 14 com passagem de parâmetros.

import java.util.Scanner;

public class Ex20_MenuVetorDouble_Param {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] vector = new double[10];

        fillVector(vector, scanner);

        int index = 0;
        char option;

        do {
            showCurrentElement(vector, index);

            showMenu();
            option = scanner.next().charAt(0);

            index = changeIndex(option, index, vector.length);

        } while (option != '.');

        System.out.println("Sistema Finalizado.");
        scanner.close();
    }

    public static void fillVector(double[] vector, Scanner scanner) {
        for (int i = 0; i < vector.length; i++) {
            System.out.print("Inserir valor do indice " + i + ": ");
            vector[i] = scanner.nextDouble();
        }
    }

    public static void showCurrentElement(double[] vector, int index) {
        System.out.println("\nIndice atual: " + index);
        System.out.println("Valor: " + vector[index]);
    }

    public static void showMenu() {
        System.out.println("Menu:");
        System.out.println(" + -> Proximo elemento");
        System.out.println(" - -> Elemento anterior");
        System.out.println(" . -> Exit");
        System.out.print("Escolha uma opcao: ");
    }

    public static int changeIndex(char option, int index, int size) {
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