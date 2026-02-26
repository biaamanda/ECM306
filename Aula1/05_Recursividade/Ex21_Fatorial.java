// ENUNCIADO:
// Elaborar um programa, em linguagem Java, para calcular o fatorial de um número a ser digitado. Criar um método para cálculo do mesmo utilizando recursividade;

import java.util.Scanner;

public class Ex21_Fatorial {

    public static long factorial(int n) {
        if (n == 0 || n == 1) {
            return 1; 
        } else {
            return n * factorial(n - 1);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro: ");
        int number = scanner.nextInt();

        if (number < 0) {
            System.out.println("Não é possível calcular o fatorial de um número negativo.");
        } else {
            long result = factorial(number);
            System.out.println("Fatorial de " + number + " = " + result);
        }

        scanner.close();
    }
}