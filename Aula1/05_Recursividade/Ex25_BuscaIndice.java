// ENUNCIADO:
// Elaborar um programa, em linguagem Java, que utilize apenas métodos recursivos, capaz de receber, via teclado, 10 elementos tipo float, formando um vetor, e um outro elemento tipo float, o qual deverá ser comparado à cada elemento do vetor. O programa deverá informar o valor do primeiro índice do vetor, a partir do 0, que contém um elemento igual ao valor digitado. Ex.: 
//Índice:                         0     1     2     3     4    5    6     7    8     9 
//Elementos Digitados:  1     2     3     4     5    6    7     8    9    10 
//Número Digitado:  4 → Resposta: O número 4 está localizado no índice 3 do vetor. 

import java.util.Scanner;

public class Ex25_BuscaIndice {

    public static void completeVector(float[] vector, int index, Scanner scanner) {
        if (index == vector.length) {
            return; // caso base
        }
        System.out.print("Digite o elemento " + index + ": ");
        vector[index] = scanner.nextFloat();
        completeVector(vector, index + 1, scanner); // chamada recursiva
    }

    // Método recursivo para buscar o índice do elemento
    public static int indexLookUp(float[] vector, float value, int index) {
        if (index == vector.length) {
            return -1; // valor não encontrado
        }
        if (vector[index] == value) {
            return index; // encontrou o valor
        }
        return indexLookUp(vector, value, index + 1); // chamada recursiva
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float[] vector = new float[10];

        // Preenchimento do vetor (recursivo)
        completeVector(vector, 0, scanner);

        // Leitura do valor a ser buscado
        System.out.print("Digite o número a ser procurado no vetor: ");
        float value = scanner.nextFloat();

        // Busca recursiva
        int foundIndex = indexLookUp(vector, value, 0);

        // Resultado
        if (foundIndex != -1) {
            System.out.println("O número " + value + " está localizado no índice " + foundIndex + " do vetor.");
        } else {
            System.out.println("O número " + value + " não foi encontrado no vetor.");
        }

        scanner.close();
    }
}
