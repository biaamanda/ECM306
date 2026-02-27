// ENUNCIADO:
// Reescrever o exercício anterior, porém colocando mensagens dentro do método recursivo, de valores de entrada e valores de retorno, afim de acompanhar-se a evolução da execução do programa. 

import java.util.Scanner;

public class Ex31_SerieRecursivaComLog {

    public static double calculateSeries(int term) {
        System.out.println("Inserindo (termo = " + term + ")");

        if (term == 1) {
            // Log de retorno do caso base
            System.out.println("Returno(termo = 1): 1.0");
            return 1.0;
        }
        double currentValue = 1.0 / Math.pow(2, term - 1);
        double result = currentValue + calculateSeries(term - 1);

        System.out.println(
            "Retorna(termo = " + term + "): " + result
        );

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Inserir termo possitivo: ");
        int term = scanner.nextInt();

        if (term <= 0) {
            System.out.println("Termo deve ser um número inteiro positivo.");
        } else {
            double result = calculateSeries(term);
            System.out.println("Resultado final: " + result);
        }

        scanner.close();
    }
}