// ENUNCIADO:
// Elaborar um programa, em linguagem Java, que utilize apenas métodos recursivos, capaz de receber, via teclado, dois números inteiros e positivos, calculando a subtração do segundo no primeiro, porém, não se utilizando da subtração para realizar o cálculo e sim a operação de comparação sucessivamente. 
//Exemplo: 5 – 3 => 3+1 = 4; 3+2=5. Portanto 5 – 3 = 2.

import java.util.Scanner;

public class Ex29_SubtracaoSemSubtrair {

    public static int subtract(int a, int b) {
        if (a == b) {
            return 0;
        } else if (a > b) {
            return 1 + subtract(a - 1, b);
        } else {
            return -1 + subtract(a, b - 1);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número inteiro e positivo: ");
        int num1 = scanner.nextInt();

        System.out.print("Digite o segundo número inteiro e positivo: ");
        int num2 = scanner.nextInt();

        int result = subtract(num1, num2);
        System.out.println("O resultado da subtraçao é: " + result);
        
        scanner.close();
    }
}
