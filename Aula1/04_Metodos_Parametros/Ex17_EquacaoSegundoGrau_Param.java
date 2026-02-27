// ENUNCIADO:
// Reescrever exercício 11 com passagem de parâmetros.

import java.util.Scanner;

public class Ex17_EquacaoSegundoGrau_Param {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Valor de a: ");
        float a = scanner.nextFloat();

        System.out.print("Valor de b: ");
        float b = scanner.nextFloat();

        System.out.print("Valor de c: ");
        float c = scanner.nextFloat();

        if (a == 0) {
            System.out.println("Equacao invalida.");
        } else {
            // passagem de parâmetros para o método
            float delta = calculateDelta(a, b, c);
            System.out.println("Delta = " + delta);

            if (delta < 0) {
                System.out.println("A equacao tem raizes imaginarias.");
            } else if (delta == 0) {
                // passagem de parâmetros para o método da raiz
                float root = calculateRoot1(a, b, delta);
                System.out.println("A equacao tem duas solucoes iguais:");
                System.out.println("x = " + root);
            } else {
                // passagem de parâmetros para os dois métodos de raiz
                float root1 = calculateRoot1(a, b, delta);
                float root2 = calculateRoot2(a, b, delta);

                System.out.println("A equacao tem duas solucoes diferentes:");
                System.out.println("x1 = " + root1);
                System.out.println("x2 = " + root2);
            }
        }

        scanner.close();
    }

    public static float calculateDelta(float a, float b, float c) {
        return (b * b) - (4 * a * c);
    }

    public static float calculateRoot1(float a, float b, float delta) {
        return (float) ((-b + Math.sqrt(delta)) / (2 * a));
    }

    public static float calculateRoot2(float a, float b, float delta) {
        return (float) ((-b - Math.sqrt(delta)) / (2 * a));
    }
}
