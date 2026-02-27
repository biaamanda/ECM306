// ENUNCIADO:
// Reescrever exercício 12 com passagem de parâmetros.

import java.util.Scanner;

public class Ex18_NumeroPrimo_Param {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro maior que zero: ");
        int number = scanner.nextInt();

        // chamada do método com passagem de parâmetro
        if (isPrime(number)) {
            System.out.println("O número é primo.");
        } else {
            System.out.println("O número não é primo.");
        }

        scanner.close();
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}