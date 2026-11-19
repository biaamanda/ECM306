/*
 * BASE: TECNICAS DE PROJETO DE ALGORITMOS (T23)
 * ---------------------------------------------------------------------------
 *   TECNICA               IDEIA                                         EXEMPLO AQUI
 *   Forca bruta           testa TODAS as possibilidades                 maximoForcaBruta, potenciaForcaBruta
 *   Divisao e conquista   DIVIDE ao meio, RESOLVE cada metade, JUNTA    maximoDC, somaDC, potenciaDC, mergeSort
 *   Prog. dinamica        subproblemas se REPETEM: resolve 1 vez e GUARDA  fibRecursivo (ruim) x fibPD
 *   Guloso                pega o MELHOR AGORA e nunca volta atras       troco
 *   Backtracking          tenta; se falhar, DESFAZ e tenta outro        (ver cicloHamiltoniano em BaseGrafo)
 *
 * GERAR O JAR:
 *   javac --release 8 -Xlint:-options *.java
 *   jar cfe Tecnicas.jar BaseTecnicas *.class
 *   java -jar Tecnicas.jar
 */

import java.util.Arrays;

class Tecnicas {

    // ===================================================== FORCA BRUTA
    // Olha um por um. O(n)
    static int maximoForcaBruta(int[] v) {
        int max = v[0];
        for (int i = 1; i < v.length; i++) if (v[i] > max) max = v[i];
        return max;
    }

    // Multiplica n vezes. O(n)
    static long potenciaForcaBruta(long x, int n) {
        long r = 1;
        for (int i = 0; i < n; i++) r *= x;
        return r;
    }

    // ===================================================== DIVISAO E CONQUISTA
    // Molde de TODO D&C:
    //   if (problema pequeno) return resposta direta;   <- caso base
    //   divide em metades;                               <- DIVIDIR
    //   resolve cada metade chamando a si mesmo;         <- CONQUISTAR
    //   junta as respostas;                              <- COMBINAR
    static int maximoDC(int[] v, int ini, int fim) {
        if (ini == fim) return v[ini];                    // caso base: 1 elemento
        int meio = (ini + fim) / 2;
        int m1 = maximoDC(v, ini, meio);
        int m2 = maximoDC(v, meio + 1, fim);
        return Math.max(m1, m2);                          // ALTERE: a forma de juntar (max, soma, min...)
    }

    static int somaDC(int[] v, int ini, int fim) {
        if (ini == fim) return v[ini];
        int meio = (ini + fim) / 2;
        return somaDC(v, ini, meio) + somaDC(v, meio + 1, fim);
    }

    // x^n dividindo o expoente ao meio. Guardando a metade em "p" -> O(log n)
    // (o slide calcula a metade DUAS vezes, o que volta a ser O(n))
    static long potenciaDC(long x, int n) {
        if (n == 0) return 1;
        long p = potenciaDC(x, n / 2);
        return (n % 2 == 0) ? p * p : p * p * x;
    }

    // MergeSort: divide ao meio, ordena cada metade, intercala. O(n log n)
    static void mergeSort(int[] v, int ini, int fim) {
        if (ini >= fim) return;
        int meio = (ini + fim) / 2;
        mergeSort(v, ini, meio);
        mergeSort(v, meio + 1, fim);
        merge(v, ini, meio, fim);
    }
    private static void merge(int[] v, int ini, int meio, int fim) {
        int[] tmp = new int[fim - ini + 1];
        int i = ini, j = meio + 1, k = 0;
        while (i <= meio && j <= fim) tmp[k++] = (v[i] <= v[j]) ? v[i++] : v[j++];
        while (i <= meio) tmp[k++] = v[i++];
        while (j <= fim) tmp[k++] = v[j++];
        System.arraycopy(tmp, 0, v, ini, tmp.length);
    }

    // ===================================================== PROGRAMACAO DINAMICA
    // Recursivo puro: recalcula Fib(2), Fib(3)... varias vezes -> exponencial O(2^n)
    static long fibRecursivo(int n) {
        if (n <= 1) return n;
        return fibRecursivo(n - 1) + fibRecursivo(n - 2);
    }

    // PD com TABELA: cada valor e calculado uma vez e guardado. O(n)
    static long fibPD(int n) {
        if (n <= 1) return n;
        long[] tabela = new long[n + 1];
        tabela[0] = 0;
        tabela[1] = 1;
        for (int i = 2; i <= n; i++) tabela[i] = tabela[i - 1] + tabela[i - 2];
        return tabela[n];
    }

    // ===================================================== GULOSO
    // Troco: sempre usa a MAIOR nota/moeda possivel. Nao volta atras.
    // Funciona com as notas brasileiras; pode FALHAR com moedas estranhas
    // (ex.: {4, 3, 1} para 6 -> guloso 4+1+1, otimo 3+3).
    // ALTERE: "valores" em ordem DECRESCENTE.
    static String troco(int valor, int[] valores) {
        StringBuilder sb = new StringBuilder();
        for (int nota : valores) {
            int qtd = valor / nota;
            if (qtd > 0) sb.append(qtd).append(" x ").append(nota).append("  ");
            valor = valor % nota;
        }
        return sb.toString().trim();
    }
}

public class BaseTecnicas {
    public static void main(String[] args) {
        int[] v = {4, 133, 1, 9, 5, 99, 2};
        System.out.println("Maximo (forca bruta): " + Tecnicas.maximoForcaBruta(v));
        System.out.println("Maximo (D&C):         " + Tecnicas.maximoDC(v, 0, v.length - 1));
        System.out.println("Soma (D&C):           " + Tecnicas.somaDC(v, 0, v.length - 1));
        System.out.println("2^10 forca bruta/D&C: " + Tecnicas.potenciaForcaBruta(2, 10) + " / " + Tecnicas.potenciaDC(2, 10));

        int[] w = {8, 3, 2, 9, 7, 1, 5, 4};
        Tecnicas.mergeSort(w, 0, w.length - 1);
        System.out.println("MergeSort:            " + Arrays.toString(w));

        System.out.println("Fib(30) recursivo/PD: " + Tecnicas.fibRecursivo(30) + " / " + Tecnicas.fibPD(30));
        System.out.println("Troco de 150:         " + Tecnicas.troco(150, new int[]{100, 50, 20, 10, 5, 2, 1}));
        System.out.println("Guloso falhando (6):  " + Tecnicas.troco(6, new int[]{4, 3, 1}) + "  (otimo: 2 x 3)");
    }
}
