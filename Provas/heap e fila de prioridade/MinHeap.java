/* Unidade 15 - Heaps e Filas de Prioridade
 *
 * MIN-HEAP implementado em array 0-indexado (A[0..tamanho-1]), no mesmo
 * estilo dos exercicios Ex02 da Aula 15.
 *
 * A unica diferenca para o max-heap e o sentido das comparacoes:
 * aqui o pai e sempre <= aos filhos, entao a raiz guarda o MENOR elemento.
 *
 * Enderecamento 0-indexado:
 *   pai(i)      = (i - 1) / 2
 *   esquerda(i) = 2*i + 1
 *   direita(i)  = 2*i + 2
 */

import java.util.Arrays;

public class MinHeap {

    private int[] heap;
    private int tamanho;

    public MinHeap(int capacidade) {
        heap = new int[capacidade];
        tamanho = 0;
    }

    public MinHeap(int[] valores) {
        heap = Arrays.copyOf(valores, valores.length);
        tamanho = valores.length;
        construirMinHeap();
    }

    private static int pai(int i)      { return (i - 1) / 2; }
    private static int esquerda(int i) { return 2 * i + 1; }
    private static int direita(int i)  { return 2 * i + 2; }

    // MIN-HEAPIFY: desce heap[i] enquanto for maior que algum filho.
    public void minHeapify(int i) {
        while (true) {
            int e = esquerda(i);
            int d = direita(i);
            int menor = i;

            if (e < tamanho && heap[e] < heap[menor]) menor = e;
            if (d < tamanho && heap[d] < heap[menor]) menor = d;

            if (menor == i) return;

            trocar(i, menor);
            i = menor;
        }
    }

    // BUILD-MIN-HEAP: O(n). Comeca no ultimo no interno = (tamanho/2 - 1).
    public void construirMinHeap() {
        for (int i = tamanho / 2 - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    // Insercao: entra no fim e SOBE enquanto for menor que o pai. O(lg n).
    public void inserir(int chave) {
        if (tamanho == heap.length) {
            heap = Arrays.copyOf(heap, Math.max(2, heap.length * 2));
        }
        heap[tamanho] = chave;
        int i = tamanho;
        tamanho++;

        while (i > 0 && heap[i] < heap[pai(i)]) {
            trocar(i, pai(i));
            i = pai(i);
        }
    }

    // Remocao do minimo (a raiz). O(lg n).
    public int extrairMin() {
        if (tamanho == 0) {
            throw new RuntimeException("Heap vazio");
        }
        int min = heap[0];
        heap[0] = heap[tamanho - 1];
        tamanho--;
        if (tamanho > 0) {
            minHeapify(0);
        }
        return min;
    }

    public int minimo() {
        if (tamanho == 0) {
            throw new RuntimeException("Heap vazio");
        }
        return heap[0];
    }

    public boolean eMinHeap() {
        for (int i = 0; esquerda(i) < tamanho; i++) {
            int e = esquerda(i);
            int d = direita(i);
            if (e < tamanho && heap[i] > heap[e]) return false;
            if (d < tamanho && heap[i] > heap[d]) return false;
        }
        return true;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean vazia() {
        return tamanho == 0;
    }

    private void trocar(int i, int j) {
        int aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
    }

    public void imprimirVetor() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 0; i < tamanho; i++) {
            sb.append(heap[i]).append(i < tamanho - 1 ? ", " : " ");
        }
        System.out.println(sb.append("]"));
    }

    public static void main(String[] args) {
        int[] valores = {40, 20, 15, 35, 80, 71, 16, 21, 70};

        System.out.println("Vetor original:");
        System.out.println(Arrays.toString(valores));

        MinHeap h = new MinHeap(valores);

        System.out.println("\nApos BUILD-MIN-HEAP:");
        h.imprimirVetor();
        System.out.println("e min-heap? " + h.eMinHeap());
        System.out.println("minimo = " + h.minimo());

        System.out.println("\nInserindo 5:");
        h.inserir(5);
        h.imprimirVetor();

        System.out.println("\nExtraindo o minimo repetidamente (sai ordenado crescente):");
        while (!h.vazia()) {
            System.out.print(h.extrairMin() + " ");
        }
        System.out.println();
    }
}
