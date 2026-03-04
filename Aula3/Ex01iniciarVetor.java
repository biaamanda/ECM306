//Elaborar um programa, em linguagem Java, capaz inicializar com 0 (zero) um vetor do tipo int de N elementos, utilizando laços, onde N deve ser: 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000 e 500000.

public class Ex01iniciarVetor {
    public static void main(String[] args) {
        int[] size = {10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000};
        for (int n : size) {
            long startTime = System.nanoTime();
            int[] vector = new int[n];
            for (int i = 0; i < n; i++) {
                vector[i] = 0;
            }
            long endTime = System.nanoTime();
            System.out.println("Tamanho: " + n + " - Tempo: " + (endTime - startTime) + " ns");
        }
    }
}