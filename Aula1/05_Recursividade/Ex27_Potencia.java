// ENUNCIADO:
// Elaborar um programa, em linguagem Java, que utilize apenas métodos recursivos, capaz de receber, via teclado, dois números inteiros e positivos, calculando o primeiro elevado ao segundo. 

import java.util.Scanner;

public class Ex27_Potencia {
    public static long power(int base, int exponent) {
        if (exponent == 0) {
            return 1; // Qualquer número elevado a 0 é 1
        } else {
            return base * power(base, exponent - 1); // Chamada recursiva
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite a base: ");
        int base = scanner.nextInt();
        
        System.out.print("Digite o expoente: ");
        int exponent = scanner.nextInt();
        
        long result = power(base, exponent);
        
        System.out.println(base + " elevado a " + exponent + " é: " + result);
        
        scanner.close();
    }
}
