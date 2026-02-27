// ENUNCIADO:
// Reescrever exercício 13 com passagem de parâmetros.

import java.util.Scanner;

public class Ex19_ControleQualidadePregos_Param {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] length = new double[10];
        double[] diameter = new double[10];

        readData(length, diameter, scanner);

        double averageLength = calculateAverage(length);
        double averageDiameter = calculateAverage(diameter);

        int longestIndex = findLongestIndex(length);
        int thinnestIndex = findThinnestIndex(diameter);

        showResults(
                averageLength,
                averageDiameter,
                longestIndex,
                thinnestIndex,
                length,
                diameter
        );

        scanner.close();
    }

    public static void readData(double[] length, double[] diameter, Scanner scanner) {
        for (int i = 0; i < length.length; i++) {
            System.out.print("Comprimento do prego " + (i + 1) + " (mm): ");
            length[i] = scanner.nextDouble();

            System.out.print("Diametro do prego " + (i + 1) + " (mm): ");
            diameter[i] = scanner.nextDouble();
        }
    }

    public static double calculateAverage(double[] values) {
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum / values.length;
    }

    public static int findLongestIndex(double[] length) {
        int index = 0;
        for (int i = 1; i < length.length; i++) {
            if (length[i] > length[index]) {
                index = i;
            }
        }
        return index;
    }

    public static int findThinnestIndex(double[] diameter) {
        int index = 0;
        for (int i = 1; i < diameter.length; i++) {
            if (diameter[i] < diameter[index]) {
                index = i;
            }
        }
        return index;
    }

    public static void showResults(
            double averageLength,
            double averageDiameter,
            int longestIndex,
            int thinnestIndex,
            double[] length,
            double[] diameter
    ) {
        System.out.println("Comprimento e diametro medios: " + averageLength + " mm; " + averageDiameter + " mm");

        System.out.println("Numero da maior amostra e seu comprimento: " + (longestIndex + 1) + "; " + length[longestIndex] + " mm");

        System.out.println("Numero da menor amostra e diametro: " + (thinnestIndex + 1) + "; " + diameter[thinnestIndex] + " mm");
    }
}