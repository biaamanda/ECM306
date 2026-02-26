// ENUNCIADO:
//  Elaborar um programa, em linguagem Java, que utilize apenas métodos recursivos, capaz de receber, via teclado, dois números inteiros e positivos, calculando a multiplicação entre esses dois números, porém, não se utilizando a multiplicação para realizar o cálculo e sim a operação de soma sucessiva. 
//Exemplo: 4 * 3 é igual a 3 + 3 + 3 + 3.

import java.util.Scanner;

public class Ex28_MultiplicacaoPorSoma {

    public static int multiply (int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        } else if (b > 0) {
            return a + multiply(a, b - 1);
        } else {
            return -multiply(a, -b);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número inteiro e positivo: ");
        int num1 = scanner.nextInt();

        System.out.print("Digite o segundo número inteiro e positivo: ");
        int num2 = scanner.nextInt();

        if (num1 < 0 || num2 < 0) {
            System.out.println("Por favor, digite apenas números inteiros e positivos.");
        } else {
            int result = multiply(num1, num2);
            System.out.println("O resultado da multiplicação por soma é: " + result);
        }
        scanner.close();
    }
}