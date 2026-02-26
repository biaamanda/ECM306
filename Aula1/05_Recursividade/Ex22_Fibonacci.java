// ENUNCIADO:
// Elaborar um programa, em linguagem Java, para calcular o N-ésimo elemento da série de Fibonacci. O índice desse elemento deverá ser digitado para a realização do cálculo. Criar um método que calcula o resultado da série, utilizando-se da recursividade
//Série de Fibonacci: O próximo elemento tem o valor igual à soma dos dois elementos anteriores da série:  1, 1, 2, 3, 5, 8, 13, 21, ..., ;

import java.util.Scanner;

public class Ex22_Fibonacci {

        public static long fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o índice do elemento da série de Fibonacci: ");
        int index = scanner.nextInt();

        if (index <= 0) {
            System.out.println("O índice deve ser um número inteiro positivo.");
        } else {
            long result = fibonacci(index);
            System.out.println("O " + index + "º elemento da série de Fibonacci é: " + result);
        }
        scanner.close();
    }
}