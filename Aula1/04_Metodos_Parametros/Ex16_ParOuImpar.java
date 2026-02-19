// ENUNCIADO:
// Elaborar um método, em linguagem Java, com passagem de parâmetros, dentro de um programa teste, capaz de informar se o número digitado é par ou impar. A digitação do valor e a apresentação do resultado deverá acontecer externamente a este método;

import java.util.Scanner;

public class Ex16_ParOuImpar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um numero: ");
        int number = scanner.nextInt();
        
        boolean result = EvenNumber(number);

        if (result) {
            System.out.print("O numero " + number + " e PAR");
        } else {
            System.out.print("O numero " + number + " e IMPAR");
        }

        scanner.close();
    }

    public static boolean EvenNumber(int n) {
        return n % 2 == 0;
    }
}
