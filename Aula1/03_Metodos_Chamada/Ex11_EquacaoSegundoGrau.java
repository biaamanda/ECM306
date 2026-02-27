// ENUNCIADO:
//  Utilizando programação por chamadas de métodos, elaborar um programa, em linguagem Java, capaz de receber os valores de a, b e c de uma equação de 2º grau qualquer ( a x2 + b x + c = 0 ), calcular e apresentar o valor de  (delta) e informar se suas raízes são imaginárias, reais iguais ou reais diferentes, apresentando seus valores para os casos quando foram reais. 
// Dica: Criar um método para calcular a raiz 1 e outro método para calcular a raiz 2.

import java.util.Scanner;

public class Ex11_EquacaoSegundoGrau {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Valor de: ");
        float a = scanner.nextFloat();

        System.out.print("Valor de b: ");
        float b = scanner.nextFloat();

        System.out.print("Valor de c: ");
        float c = scanner.nextFloat();

        if (a == 0) {
            System.out.println("Inserir numero diferente de zero.");
        } else {
            float delta = calculateDelta(a, b, c);
            System.out.println("Delta = " + delta);

            if (delta < 0) {
                System.out.println("A equacao tem raizes imaginarias.");
            } else if (delta == 0) {
                float root = squareRoot1(a, b, delta);
                System.out.println("A equacao tem duas solucoes:");
                System.out.println("x = " + root);
            } else {
                float root1 = squareRoot1(a, b, delta);
                float root2 = squareRoot2(a, b, delta);
                System.out.println("x1 = " + root1);
                System.out.println("x2 = " + root2);
            }
        }
        scanner.close();
    }
        
    public static float calculateDelta(float a, float b, float c) {
        return (b * b) - (4 * a * c);
    }

    public static float squareRoot1(float a, float b, float delta) {
        return (float) ((-b + Math.sqrt(delta)) / (2 * a));
    }

    public static float squareRoot2(float a, float b, float delta) {
        return (float) ((-b - Math.sqrt(delta)) / (2 * a));
    }
}
