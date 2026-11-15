/* Unidade 15 - Heaps e Filas de Prioridade
 *
 * Simula INSERCOES sucessivas em um max-heap, mostrando o array e a arvore
 * depois de cada chave inserida. Estilo da Questao 8 do simulado
 * ("dado o heap ..., inserindo as chaves X e Y, qual a configuracao final?").
 *
 * Heap 1-indexado (A[1..n]), igual aos slides:
 *   pai(i) = i/2 ,  esquerda(i) = 2*i ,  direita(i) = 2*i + 1
 * Insercao = coloca no fim e SOBE trocando com o pai enquanto for maior.
 */

import java.util.Arrays;

public class SimulaInsercaoHeap {

    private int[] A;
    private int n;

    public SimulaInsercaoHeap(int[] heapInicial) {
        A = new int[heapInicial.length + 32];
        n = heapInicial.length;
        for (int i = 0; i < heapInicial.length; i++) {
            A[i + 1] = heapInicial[i];
        }
    }

    public void inserir(int chave) {
        n++;
        A[n] = chave;
        int i = n;

        System.out.println("Inserindo " + chave + " na posicao " + n + ":");
        while (i > 1 && A[i / 2] < A[i]) {
            System.out.println("   " + A[i] + " > " + A[i / 2] + " (pai)  => troca");
            int aux = A[i];
            A[i] = A[i / 2];
            A[i / 2] = aux;
            i = i / 2;
        }
        if (i > 1) {
            System.out.println("   " + A[i] + " <= " + A[i / 2] + " (pai)  => para");
        } else {
            System.out.println("   " + A[i] + " chegou a raiz  => para");
        }
        imprimir();
        System.out.println();
    }

    public int[] estadoAtual() {
        return Arrays.copyOfRange(A, 1, n + 1);
    }

    public void imprimir() {
        System.out.println("   array: " + Arrays.toString(estadoAtual()));
        System.out.println("   arvore:");
        imprimirArvore(1, 1);
    }

    private void imprimirArvore(int i, int nivel) {
        if (i > n) return;
        imprimirArvore(2 * i + 1, nivel + 1);
        StringBuilder recuo = new StringBuilder("     ");
        for (int k = 0; k < nivel; k++) recuo.append("      ");
        System.out.println(recuo + "" + A[i]);
        imprimirArvore(2 * i, nivel + 1);
    }

    public static void main(String[] args) {
        // Questao 8: heap inicial e insercao de 93 e depois 19.
        int[] inicial = {92, 60, 78, 39, 28, 66, 70};

        System.out.println("Heap inicial: " + Arrays.toString(inicial));
        System.out.println();

        SimulaInsercaoHeap sim = new SimulaInsercaoHeap(inicial);
        sim.inserir(93);
        sim.inserir(19);

        System.out.println("Configuracao final: " + Arrays.toString(sim.estadoAtual()));
        System.out.println("Esperado (alternativa D): [93, 92, 78, 60, 28, 66, 70, 39, 19]");
    }
}
