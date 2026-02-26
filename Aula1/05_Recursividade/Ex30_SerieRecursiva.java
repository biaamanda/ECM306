// ENUNCIADO:
//  Elaborar um programa, em linguagem Java, que utilize apenas métodos recursivos, capaz de receber, via teclado, um número inteiro, positivo, que represente o termo da série abaixo. Calcular o valor da série, ou seja, a soma de todos os valores calculados, do 1º termo até o termo digitado. 
//S = (1/20) + (1/21) + (1/22) + (1/23) + (1/24) + ... + (1/2N-1) + (1/2N) 
//Exemplo: Digitado o termo: 4 => S = 1 + 1/2 + 1/4 + 1/8 => S = 1.875 

import java.util.Scanner;

public class Ex30_SerieRecursiva {

    public static double calculateSeries(int term) {
        if (term == 1) {
            return 1.0; // caso base
        }
        return (1.0 / Math.pow(2, term - 1)) + calculateSeries(term - 1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive integer term: ");
        int term = scanner.nextInt();

        if (term <= 0) {
            System.out.println("The term must be a positive integer.");
        } else {
            double result = calculateSeries(term);
            System.out.println("The value of the series is: " + result);
        }

        scanner.close();
        
    }
}
