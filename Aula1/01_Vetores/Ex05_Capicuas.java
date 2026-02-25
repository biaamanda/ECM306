// ENUNCIADO:
// Dado um vetor int de 16 elementos digitados aleatoriamente, elaborar um programa capaz de apresentar a quantidade de capicuas de 4 elementos existentes ao longo do vetor.
//capicua: número que representa o mesmo valor quando lido da esquerda para a direita e vice-versa. 
// Exemplo: Vetor digitado 
// índices:      0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15 
// elementos: 0   1   1   0   3   2   4   4   2   2    4    7    7    7    7    7;  
// Total de Capicuas: 5 
// Obs.: Foram encontradas as seguintes capicuas: 0110 (índices 0, 1, 2 e 3), 2442 (índices 5, 6, 7 e 8), 4224 (índices 7, 8, 9 e 10), 7777 (índices 11, 12, 13 e 14) e 7777 (índices 12, 13, 14 e 15). 

import java.util.Scanner;

public class Ex05_Capicuas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] vector = new int[16];

        System.out.print("Digite um vetor de 16 elementos: ");
        for (int i = 0; i < vector.length; i++){
            vector[i] = scanner.nextInt();
        }

        int total = 0;

        for (int i = 0; i <= vector.length - 4; i++) {

            if (vector[i] == vector[i + 3] &&
                vector[i + 1] == vector[i + 2]) {
                total++;
            }
        }

        System.out.print("Numero de capicuas: " + total);
        scanner.close();   
    }
}
