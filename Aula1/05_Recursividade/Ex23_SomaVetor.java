// ENUNCIADO:
// Utilizando recursividade, criar um programa, em linguagem Java, que calcule e apresente a soma de todos os elementos de um vetor, inteiro, de tamanho 10, o qual deverá ser preenchido, anteriormente ao cálculo, pelo operador;

import java.util.Scanner;

public class Ex23_SomaVetor {

    public static int VectorSum(int[] vector, int index) {
        if (index == vector.length) {
            return 0;
        } else {
            return vector[index] + VectorSum(vector, index + 1);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] vector = new int[10];

        System.out.println("Digite 10 números inteiros:");
        for (int i = 0; i < vector.length; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            vector[i] = scanner.nextInt();
        }

        int sum = VectorSum(vector, 0);

        System.out.println("Soma dos elementos do vetor é: " + sum);

        scanner.close();
    }
}