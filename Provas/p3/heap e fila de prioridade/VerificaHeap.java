/* Unidade 15 - Heaps e Filas de Prioridade
 *
 * Dado um vetor, verifica se ele representa um MAX-HEAP ou um MIN-HEAP e,
 * quando NAO representa, aponta o primeiro no que viola a propriedade.
 * Estilo da Questao 9 do simulado ("Verificar se a sequencia e ou nao um Heap").
 *
 * O vetor e tratado 0-indexado (como se digita normalmente em Java):
 *   filho esquerdo de i  -> 2*i + 1
 *   filho direito  de i  -> 2*i + 2
 * Entre parenteses tambem mostramos o indice 1-indexado (i+1), que e o
 * usado nos slides.
 */

import java.util.Arrays;

public class VerificaHeap {

    // Retorna -1 se e max-heap; senao retorna o indice (0-indexado) que viola.
    public static int violaMaxHeap(int[] a) {
        int n = a.length;
        for (int i = 0; 2 * i + 1 < n; i++) {
            int e = 2 * i + 1;
            int d = 2 * i + 2;
            if (e < n && a[i] < a[e]) return i;
            if (d < n && a[i] < a[d]) return i;
        }
        return -1;
    }

    // Retorna -1 se e min-heap; senao retorna o indice (0-indexado) que viola.
    public static int violaMinHeap(int[] a) {
        int n = a.length;
        for (int i = 0; 2 * i + 1 < n; i++) {
            int e = 2 * i + 1;
            int d = 2 * i + 2;
            if (e < n && a[i] > a[e]) return i;
            if (d < n && a[i] > a[d]) return i;
        }
        return -1;
    }

    // Imprime um laudo completo, com justificativa.
    public static void analisar(int[] a) {
        System.out.println("Sequencia: " + Arrays.toString(a));

        int vMax = violaMaxHeap(a);
        int vMin = violaMinHeap(a);

        if (vMax == -1) {
            System.out.println("  -> E um MAX-HEAP (todo pai >= filhos).");
        } else {
            explicar(a, vMax, true);
        }

        if (vMin == -1) {
            System.out.println("  -> E um MIN-HEAP (todo pai <= filhos).");
        } else {
            explicar(a, vMin, false);
        }

        if (vMax != -1 && vMin != -1) {
            System.out.println("  -> NAO e heap (nem max nem min).");
        }
        System.out.println();
    }

    private static void explicar(int[] a, int i, boolean max) {
        int n = a.length;
        int e = 2 * i + 1;
        int d = 2 * i + 2;
        String tipo = max ? "MAX" : "MIN";
        String rel  = max ? ">=" : "<=";

        System.out.println("  -> NAO e " + tipo + "-HEAP.");
        System.out.print("     No indice " + i + " (1-indexado: " + (i + 1) + "), valor "
                + a[i] + ", deveria ser " + rel + " que os filhos: ");
        StringBuilder sb = new StringBuilder();
        if (e < n) sb.append("esq[").append(e).append("]=").append(a[e]).append("  ");
        if (d < n) sb.append("dir[").append(d).append("]=").append(a[d]);
        System.out.println(sb.toString().trim() + ".");
    }

    public static void main(String[] args) {
        // Questao 9 do simulado: 33 32 28 31 26 29 25 30 27  -> NAO e heap.
        analisar(new int[]{33, 32, 28, 31, 26, 29, 25, 30, 27});

        // Exemplos extras
        analisar(new int[]{16, 14, 10, 8, 7, 9, 3, 2, 4, 1});   // max-heap classico dos slides
        analisar(new int[]{1, 2, 3, 4, 5, 6, 7});               // min-heap
        analisar(new int[]{10, 9, 8, 7, 6, 5, 4});              // max-heap (ordem decrescente)
        analisar(new int[]{5, 3, 8, 1, 4});                     // nem um nem outro
    }
}
