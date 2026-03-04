// Elaborar um programa, em linguagem Java, capaz copiar o conteúdo de um vetor do tipo int de N elementos para outro vetor de mesmo tamanho, utilizando laços, onde N deve ser: 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000 e 500000

public class Ex02copiarVetor {
    public static void main(String[] args){
        int[] sizes = {10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000};
        for (int n : sizes) {
            int[] original = new int[n];
            long startTime = System.nanoTime();
            int[] copy = new int[n];
            for (int i = 0; i < n; i++) {
                copy[i] = original[i];
            }
            long endTime = System.nanoTime();
            System.out.println("Tamanho: " + n + " - Tempo: " + (endTime - startTime) + " ns");
        }
    }
}