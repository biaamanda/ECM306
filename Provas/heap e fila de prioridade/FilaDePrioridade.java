/* Unidade 15 - Heaps e Filas de Prioridade
 *
 * FILA DE PRIORIDADE generica, implementada sobre um MAX-HEAP (0-indexado).
 * Sai sempre o elemento de MAIOR prioridade (maior numero associado), como
 * define o slide 45.
 *
 * Operacoes pedidas no slide 47:
 *   1) inserir(item, prioridade)
 *   2) removerMaisPrioritario()
 *   3) alterarPrioridade(item, novaPrioridade)
 *   4) tamanho()
 *   5) existemMesmaPrioridade()
 *
 * Custos: inserir e remover O(lg n); alterar O(n) (busca linear) + O(lg n).
 */

import java.util.Arrays;

public class FilaDePrioridade {

    // Cada elemento da fila: um dado + a sua prioridade.
    private static class Elemento {
        String item;
        int prioridade;

        Elemento(String item, int prioridade) {
            this.item = item;
            this.prioridade = prioridade;
        }

        @Override
        public String toString() {
            return "(" + item + ", p=" + prioridade + ")";
        }
    }

    private Elemento[] heap;
    private int tamanho;

    public FilaDePrioridade(int capacidade) {
        heap = new Elemento[capacidade];
        tamanho = 0;
    }

    private static int pai(int i)      { return (i - 1) / 2; }
    private static int esquerda(int i) { return 2 * i + 1; }
    private static int direita(int i)  { return 2 * i + 2; }

    // ---------------------------------------------------------------
    // 1) Inserir com prioridade  (entra no fim e SOBE)
    // ---------------------------------------------------------------
    public void inserir(String item, int prioridade) {
        if (tamanho == heap.length) {
            heap = Arrays.copyOf(heap, Math.max(2, heap.length * 2));
        }
        heap[tamanho] = new Elemento(item, prioridade);
        subir(tamanho);
        tamanho++;
    }

    // ---------------------------------------------------------------
    // 2) Remover o elemento de mais alta prioridade  (a raiz)
    // ---------------------------------------------------------------
    public String removerMaisPrioritario() {
        if (tamanho == 0) {
            throw new RuntimeException("Fila vazia");
        }
        Elemento topo = heap[0];
        heap[0] = heap[tamanho - 1];
        heap[tamanho - 1] = null;
        tamanho--;
        if (tamanho > 0) {
            descer(0);
        }
        return topo.item;
    }

    public String espiarMaisPrioritario() {
        if (tamanho == 0) {
            throw new RuntimeException("Fila vazia");
        }
        return heap[0].item;
    }

    // ---------------------------------------------------------------
    // 3) Alterar a prioridade de um elemento (busca pelo item e reposiciona)
    // ---------------------------------------------------------------
    public void alterarPrioridade(String item, int novaPrioridade) {
        int idx = -1;
        for (int i = 0; i < tamanho; i++) {
            if (heap[i].item.equals(item)) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            throw new RuntimeException("Item nao encontrado: " + item);
        }

        int antiga = heap[idx].prioridade;
        heap[idx].prioridade = novaPrioridade;

        if (novaPrioridade > antiga) {
            subir(idx);      // ficou mais prioritario -> sobe
        } else {
            descer(idx);     // ficou menos prioritario -> desce
        }
    }

    // ---------------------------------------------------------------
    // 4) Numero de elementos
    // ---------------------------------------------------------------
    public int tamanho() {
        return tamanho;
    }

    public boolean vazia() {
        return tamanho == 0;
    }

    // ---------------------------------------------------------------
    // 5) Existem dois elementos com a mesma prioridade?
    // ---------------------------------------------------------------
    public boolean existemMesmaPrioridade() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = i + 1; j < tamanho; j++) {
                if (heap[i].prioridade == heap[j].prioridade) {
                    return true;
                }
            }
        }
        return false;
    }

    // ---------------------------------------------------------------
    // Rotinas internas do heap
    // ---------------------------------------------------------------
    private void subir(int i) {
        while (i > 0 && heap[pai(i)].prioridade < heap[i].prioridade) {
            trocar(i, pai(i));
            i = pai(i);
        }
    }

    private void descer(int i) {
        while (true) {
            int e = esquerda(i);
            int d = direita(i);
            int maior = i;

            if (e < tamanho && heap[e].prioridade > heap[maior].prioridade) maior = e;
            if (d < tamanho && heap[d].prioridade > heap[maior].prioridade) maior = d;

            if (maior == i) return;

            trocar(i, maior);
            i = maior;
        }
    }

    private void trocar(int i, int j) {
        Elemento aux = heap[i];
        heap[i] = heap[j];
        heap[j] = aux;
    }

    public void imprimir() {
        StringBuilder sb = new StringBuilder("Fila: ");
        for (int i = 0; i < tamanho; i++) {
            sb.append(heap[i]).append(" ");
        }
        System.out.println(sb);
    }

    // ---------------------------------------------------------------
    // Teste
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        FilaDePrioridade fila = new FilaDePrioridade(10);

        fila.inserir("relatorio",  2);
        fila.inserir("backup",     5);
        fila.inserir("email",      1);
        fila.inserir("deploy",     8);
        fila.inserir("limpeza",    3);
        fila.imprimir();

        System.out.println("\ntamanho .................. " + fila.tamanho());
        System.out.println("mesma prioridade? ........ " + fila.existemMesmaPrioridade());

        System.out.println("\nAlterando 'email' para prioridade 9:");
        fila.alterarPrioridade("email", 9);
        fila.imprimir();

        System.out.println("\nRemovendo por ordem de prioridade:");
        while (!fila.vazia()) {
            System.out.println("  -> " + fila.removerMaisPrioritario());
        }
    }
}
