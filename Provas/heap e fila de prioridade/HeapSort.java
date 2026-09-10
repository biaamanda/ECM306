/* Unidade 15 - Heaps e Filas de Prioridade
 *
 * HEAPSORT - implementacao dos slides 49 a 52 (0-indexada).
 *
 * Ideia:
 *   1) BUILD-MAX-HEAP: o maior elemento vai para A[0].
 *   2) Repete n-1 vezes: troca A[0] com o ultimo do heap, diminui o heap
 *      em 1 e chama maxHeapify(0) para consertar a raiz.
 *   Ao final o vetor esta ORDENADO EM ORDEM CRESCENTE.
 *
 * Complexidade: O(n lg n).  (build e O(n); cada uma das n-1 extracoes e O(lg n)).
 *
 * Obs.: 'tamanho' guarda o INDICE do ultimo elemento do heap (0-indexado),
 *       por isso as comparacoes usam "<= tamanho".
 */

import java.util.Arrays;

public class HeapSort {

    public static int tamanho;   // indice do ultimo elemento do heap

    // MAX-HEAPIFY (slide 50)
    public static void maxHeapify(int[] A, int pai) {
        int esq = 2 * pai + 1;
        int dir = 2 * pai + 2;
        int maior = pai;

        if (esq <= tamanho && A[esq] > A[maior]) {
            maior = esq;
        }
        if (dir <= tamanho && A[dir] > A[maior]) {
            maior = dir;
        }
        if (maior != pai) {
            int aux = A[pai];
            A[pai] = A[maior];
            A[maior] = aux;
            maxHeapify(A, maior);
        }
    }

    // BUILD-MAX-HEAP (slide 51)
    public static void buildMaxHeap(int[] A) {
        tamanho = A.length - 1;
        for (int pai = tamanho / 2; pai >= 0; pai--) {
            maxHeapify(A, pai);
        }
    }

    // HEAPSORT (slide 52)
    public static void heapSort(int[] A) {
        buildMaxHeap(A);
        for (int i = tamanho; i > 0; i--) {
            int aux = A[i];
            A[i] = A[0];
            A[0] = aux;
            tamanho--;
            maxHeapify(A, 0);
        }
    }

    // Mesma coisa, mas mostrando o vetor a cada extracao (para "explique o passo a passo").
    public static void heapSortPassoAPasso(int[] A) {
        buildMaxHeap(A);
        System.out.println("Apos build-max-heap: " + Arrays.toString(A));
        for (int i = tamanho; i > 0; i--) {
            int aux = A[i];
            A[i] = A[0];
            A[0] = aux;
            tamanho--;
            maxHeapify(A, 0);
            System.out.println("Fixou " + A[i] + " na pos " + i + ":  " + Arrays.toString(A));
        }
    }

    public static void main(String[] args) {
        int[] lista = {5, 6, 2, 1, 9, 10, 12, 0, 3, 7, 14, 99, 34, 77};

        System.out.println("Lista antes do HeapSort:");
        System.out.println(Arrays.toString(lista));

        heapSort(lista);

        System.out.println("\nLista apos o HeapSort:");
        System.out.println(Arrays.toString(lista));
        System.out.println("Ordenada? " + estaOrdenada(lista));

        System.out.println("\n--- Passo a passo com outro vetor ---");
        int[] outra = {8, 3, 7, 1, 2, 5, 6};
        System.out.println("Original: " + Arrays.toString(outra));
        heapSortPassoAPasso(outra);
    }

    private static boolean estaOrdenada(int[] v) {
        for (int i = 1; i < v.length; i++) {
            if (v[i - 1] > v[i]) return false;
        }
        return true;
    }
}
