// ENUNCIADO:
// Criar um método, em linguagem Java, que se utilize da recursividade, dentro de um programa capaz de receber, via teclado, um número inteiro qualquer e informar se o mesmo é ou não primo; 

import java.util.Scanner;

public class Ex24_PrimoRecursivo {

        public static boolean isPrimeRecursive(int n, int divisor) {
        if (n <= 1) {
            return false;
        }
        if (divisor > Math.sqrt(n)) {
            return true; // não encontrou divisores
        }
        if (n % divisor == 0) {
            return false; // encontrou um divisor
        }
        return isPrimeRecursive(n, divisor + 1); // chamada recursiva
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número inteiro: ");
        int number = scanner.nextInt();

        boolean isPrime = isPrimeRecursive(number, 2);

        if (isPrime) {
            System.out.println("O número " + number + " é primo.");
        } else {
            System.out.println("O número " + number + " não é primo.");
        }
        scanner.close();
    }
}