/// ENUNCIADO:
// Elaborar um programa, capaz de, em um vetor do tipo int de 100 elementos, carregar seus índices pares com o número 0 (zero) e seus índices ímpares com o valor do próprio índice (ex.: [ 0, 1, 0, 3, 0, 5, 0, 7, ..., 97, 0, 99 ] ), utilizando-se de malhas;

public class Ex02_IndicesParesImpares {
    public static void main(String[] args) {
        int MaxVector = 100;
        int [] vector = new int[MaxVector];

        for (int i = 0; i < MaxVector; i++) {
            if (i % 2 == 0) {
                vector[i] = 0;
            } else {
                vector[i] = i;
            }
        }

         for (int i = 0; i < MaxVector; i++) {
            System.out.print(vector[i] + " ");
        }
    }
}
