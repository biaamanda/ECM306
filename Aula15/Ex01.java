/*Implementar em Java o algoritmo de Ordenação HeapSort completo e
seu teste; */

public class Ex01 {

    // reorganiza a árvore
    public static void heapify(int[] vetor, int tamanho, int i) {

        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        // filho da esquerda é maior que o pai
        if (esquerda < tamanho && vetor[esquerda] > vetor[maior]) {
            maior = esquerda;
        }

        // filho da direita é maior
        if (direita < tamanho && vetor[direita] > vetor[maior]) {
            maior = direita;
        }

        // Se algum filho for maior, troca e continua reorganizando
        if (maior != i) {
            int temp = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = temp;

            heapify(vetor, tamanho, maior);
        }
    }

    public static void heapSort(int[] vetor) {

        int tamanho = vetor.length;

        for (int i = tamanho / 2 - 1; i >= 0; i--) {
            heapify(vetor, tamanho, i);
        }

        // Retirar o maior elemento um por um
        for (int i = tamanho - 1; i > 0; i--) {

            // Troca a raiz (maior elemento) com o último
            int temp = vetor[0];
            vetor[0] = vetor[i];
            vetor[i] = temp;

            heapify(vetor, i, 0);
        }
    }

    public static void imprimir(int[] vetor) {

        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int[] vetor = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};

        System.out.println("Vetor original:");
        imprimir(vetor);

        heapSort(vetor);

        System.out.println("Vetor ordenado:");
        imprimir(vetor);
    }
}