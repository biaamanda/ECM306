// ENUNCIADO:
// Reescrever o exercício anterior, porém colocando mensagens dentro do método recursivo, de valores de entrada e valores de retorno, afim de acompanhar-se a evolução da execução do programa. 

import java.util.Scanner;

public class Ex31_SerieRecursivaComLog {

    public static double calculateSeries(int term) {
        System.out.println("Entering calculateSeries(term = " + term + ")");

        if (term == 1) {
            // Log de retorno do caso base
            System.out.println("Returning from calculateSeries(term = 1): 1.0");
            return 1.0;
        }
        double currentValue = 1.0 / Math.pow(2, term - 1);
        double result = currentValue + calculateSeries(term - 1);

        System.out.println(
            "Returning from calculateSeries(term = " + term + "): " + result
        );

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive integer term: ");
        int term = scanner.nextInt();

        if (term <= 0) {
            System.out.println("The term must be a positive integer.");
        } else {
            double result = calculateSeries(term);
            System.out.println("Final result of the series: " + result);
        }

        scanner.close();
    }
}