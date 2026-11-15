/* Unidade 15 - Heaps e Filas de Prioridade
 *
 * MAX-HEAP implementado em array 1-indexado (A[1..tamanho]), exatamente como
 * nos slides. O indice 0 nao e usado.
 *
 * Enderecamento (slides 12, 15, 16):
 *   PAI(i)      = |_ i/2 _|
 *   ESQUERDA(i) = 2*i
 *   DIREITA(i)  = 2*i + 1
 */

import java.util.Arrays;

public class MaxHeap {

    private int[] A;      // A[1..tamanho] guarda o heap
    private int tamanho;  // quantidade de elementos atualmente no heap

    // Heap vazio com capacidade inicial.
    public MaxHeap(int capacidade) {
        A = new int[capacidade + 1];
        tamanho = 0;
    }

    // Constroi um max-heap a partir de um vetor qualquer de valores. O(n).
    public MaxHeap(int[] valores) {
        tamanho = valores.length;
        A = new int[tamanho + 1];
        for (int k = 0; k < valores.length; k++) {
            A[k + 1] = valores[k];        // copia para as posicoes 1..n
        }
        construirMaxHeap();
    }

    // ------------------------------------------------------------------
    // Enderecamento
    // ------------------------------------------------------------------
    private static int pai(int i)      { return i / 2; }
    private static int esquerda(int i) { return 2 * i; }
    private static int direita(int i)  { return 2 * i + 1; }

    // ------------------------------------------------------------------
    // MAX-HEAPIFY (slide 31): desce A[i] ate a propriedade de max-heap valer.
    // Pre-condicao: as subarvores de ESQUERDA(i) e DIREITA(i) ja sao max-heaps.
    // Custo O(lg tamanho).
    // ------------------------------------------------------------------
    public void maxHeapify(int i) {
        int e = esquerda(i);
        int d = direita(i);
        int maior;

        if (e <= tamanho && A[e] > A[i]) {
            maior = e;
        } else {
            maior = i;
        }

        if (d <= tamanho && A[d] > A[maior]) {
            maior = d;
        }

        if (maior != i) {
            trocar(i, maior);
            maxHeapify(maior);
        }
    }

    // Versao iterativa equivalente (util quando o enunciado pede "sem recursao").
    public void maxHeapifyIterativo(int i) {
        while (true) {
            int e = esquerda(i);
            int d = direita(i);
            int maior = i;

            if (e <= tamanho && A[e] > A[maior]) maior = e;
            if (d <= tamanho && A[d] > A[maior]) maior = d;

            if (maior == i) return;

            trocar(i, maior);
            i = maior;
        }
    }

    // ------------------------------------------------------------------
    // BUILD-MAX-HEAP (slide 43): transforma A[1..tamanho] em max-heap. O(n).
    // Vai de |_ n/2 _| ate 1 porque as posicoes |_ n/2 _|+1 .. n sao folhas.
    // ------------------------------------------------------------------
    public void construirMaxHeap() {
        for (int i = tamanho / 2; i >= 1; i--) {
            maxHeapify(i);
        }
    }

    // ------------------------------------------------------------------
    // Insercao: coloca a chave na ultima posicao e a faz SUBIR (sift-up),
    // trocando com o pai enquanto for maior que ele. O(lg n).
    // ------------------------------------------------------------------
    public void inserir(int chave) {
        if (tamanho + 1 >= A.length) {
            A = Arrays.copyOf(A, Math.max(2, A.length * 2));
        }
        tamanho++;
        A[tamanho] = chave;

        int i = tamanho;
        while (i > 1 && A[pai(i)] < A[i]) {
            trocar(i, pai(i));
            i = pai(i);
        }
    }

    // ------------------------------------------------------------------
    // Remocao do maximo (a raiz): troca a raiz pelo ultimo, encolhe e
    // aplica maxHeapify(1). O(lg n).
    // ------------------------------------------------------------------
    public int extrairMax() {
        if (tamanho < 1) {
            throw new RuntimeException("Heap vazio");
        }
        int max = A[1];
        A[1] = A[tamanho];
        tamanho--;
        if (tamanho > 0) {
            maxHeapify(1);
        }
        return max;
    }

    // Le o maximo sem remover. O(1).
    public int maximo() {
        if (tamanho < 1) {
            throw new RuntimeException("Heap vazio");
        }
        return A[1];
    }

    // ------------------------------------------------------------------
    // HEAP-INCREASE-KEY: aumenta A[i] para novaChave e reposiciona subindo.
    // ------------------------------------------------------------------
    public void aumentarChave(int i, int novaChave) {
        if (i < 1 || i > tamanho) {
            throw new RuntimeException("Indice fora do heap");
        }
        if (novaChave < A[i]) {
            throw new RuntimeException("Nova chave e menor que a atual");
        }
        A[i] = novaChave;
        while (i > 1 && A[pai(i)] < A[i]) {
            trocar(i, pai(i));
            i = pai(i);
        }
    }

    // ------------------------------------------------------------------
    // Verifica se o conteudo atual satisfaz a propriedade de max-heap.
    // (Para checar um vetor externo, veja VerificaHeap.java.)
    // ------------------------------------------------------------------
    public boolean eMaxHeap() {
        for (int i = 1; 2 * i <= tamanho; i++) {
            int e = esquerda(i);
            int d = direita(i);
            if (e <= tamanho && A[i] < A[e]) return false;
            if (d <= tamanho && A[i] < A[d]) return false;
        }
        return true;
    }

    // ------------------------------------------------------------------
    // Medidas (slides 18-22)
    // ------------------------------------------------------------------
    public int tamanho() {
        return tamanho;
    }

    // Profundidade / nivel do no i = |_ lg i _|
    public static int profundidade(int i) {
        return (int) Math.floor(Math.log(i) / Math.log(2));
    }

    // Altura do no i = |_ lg(tamanho / i) _|
    public int alturaDoNo(int i) {
        return (int) Math.floor(Math.log((double) tamanho / i) / Math.log(2));
    }

    // Altura da arvore (raiz) = |_ lg(tamanho) _|
    public int altura() {
        return (int) Math.floor(Math.log(tamanho) / Math.log(2));
    }

    // ------------------------------------------------------------------
    // Impressao
    // ------------------------------------------------------------------
    private void trocar(int i, int j) {
        int aux = A[i];
        A[i] = A[j];
        A[j] = aux;
    }

    // Imprime o array na forma 1..tamanho.
    public void imprimirVetor() {
        StringBuilder sb = new StringBuilder("[ ");
        for (int i = 1; i <= tamanho; i++) {
            sb.append(A[i]).append(i < tamanho ? ", " : " ");
        }
        sb.append("]");
        System.out.println(sb);
    }

    // Imprime a arvore "deitada" (raiz a esquerda, filho direito em cima).
    public void imprimirArvore() {
        imprimirArvore(1, 0);
    }

    private void imprimirArvore(int i, int nivel) {
        if (i > tamanho) return;
        imprimirArvore(direita(i), nivel + 1);
        StringBuilder recuo = new StringBuilder();
        for (int k = 0; k < nivel; k++) recuo.append("      ");
        System.out.println(recuo + "" + A[i]);
        imprimirArvore(esquerda(i), nivel + 1);
    }

    // ------------------------------------------------------------------
    // Teste
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        // Exemplo do slide 33: constroi max-heap a partir do array.
        int[] valores = {40, 20, 15, 35, 80, 71, 16, 21, 70};

        System.out.println("Vetor original:");
        System.out.println(Arrays.toString(valores));

        MaxHeap h = new MaxHeap(valores);

        System.out.println("\nApos BUILD-MAX-HEAP:");
        h.imprimirVetor();
        System.out.println("Esperado (slide 42): [ 80, 70, 71, 40, 20, 15, 16, 21, 35 ]");

        System.out.println("\nArvore (deitada):");
        h.imprimirArvore();

        System.out.println("\ne max-heap? " + h.eMaxHeap());
        System.out.println("maximo       = " + h.maximo());
        System.out.println("altura       = " + h.altura());
        System.out.println("altura no 2  = " + h.alturaDoNo(2));
        System.out.println("profund. no 5= " + profundidade(5));

        System.out.println("\nInserindo 90:");
        h.inserir(90);
        h.imprimirVetor();

        System.out.println("\nExtraindo o maximo repetidamente:");
        while (h.tamanho() > 0) {
            System.out.print(h.extrairMax() + " ");
        }
        System.out.println();
    }
}
