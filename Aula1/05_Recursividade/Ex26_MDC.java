// ENUNCIADO:
//  Elaborar um programa, em linguagem Java, que utilize apenas métodos recursivos, capaz de receber, via teclado, dois números inteiros e positivos, calculando o Máximo Divisor Comum entre eles. 

import java.util.Scanner;

public class Ex26_MDC {

        public static int mdc(int a, int b) {
        if (b == 0) {
            return a; // caso base
        }
        return mdc(b, a % b); // chamada recursiva
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número inteiro positivo: ");
        int num1 = scanner.nextInt();

        System.out.print("Digite o segundo número inteiro positivo: ");
        int num2 = scanner.nextInt();

        if (num1 <= 0 || num2 <= 0) {
            System.out.println("Os dois números devem ser inteiros e positivos.");
        } else {
            int result = mdc(num1, num2);
            System.out.println("O MDC de " + num1 + " e " + num2 + " é: " + result);
        }
        scanner.close();
    }
}
