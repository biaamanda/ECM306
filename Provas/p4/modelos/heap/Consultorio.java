// Min-heap em vetor (indice 1): filho esq = 2i, filho dir = 2i+1, pai = i/2
class MinHeap {
    private int[] v = new int[2];
    private int n = 0;

    boolean vazio() { return n == 0; }
    void limpar() { n = 0; }

    void inserir(int x) {
        if (n + 1 == v.length) v = java.util.Arrays.copyOf(v, v.length * 2);
        v[++n] = x;
        int i = n;                                  // sobe (heapify-up)
        while (i > 1 && v[i / 2] > v[i]) { troca(i, i / 2); i /= 2; }
    }

    int remover() {
        int raiz = v[1];
        v[1] = v[n--];
        int i = 1;                                  // desce (heapify-down)
        while (2 * i <= n) {
            int f = 2 * i;
            if (f + 1 <= n && v[f + 1] < v[f]) f++;
            if (v[i] <= v[f]) break;
            troca(i, f); i = f;
        }
        return raiz;
    }
    private void troca(int a, int b) { int t = v[a]; v[a] = v[b]; v[b] = t; }
}

class Atendimento {
    private final MinHeap heap = new MinHeap();
    private final int[] contador = new int[5];  // contador por prioridade 1..4

    void inicio() { heap.limpar(); java.util.Arrays.fill(contador, 0); }

    // senha = prioridade*1000 + contador -> 1003 < 2001, entao min-heap ja ordena certo
    int novaSenha(int prioridade) {
        int senha = prioridade * 1000 + (++contador[prioridade]);
        heap.inserir(senha);
        return senha;
    }
    String proximo() { return heap.vazio() ? "Nao ha paciente (ED VAZIA)" : String.valueOf(heap.remover()); }
}

public class Consultorio {
    public static void main(String[] args) {
        Atendimento a = new Atendimento();
        System.out.println(a.proximo());
        for (int p : new int[]{4, 3, 2, 1, 1, 2}) System.out.println("senha " + a.novaSenha(p));
        System.out.println("prox " + a.proximo());
        System.out.println("senha " + a.novaSenha(1));
        for (int i = 0; i < 4; i++) System.out.println("prox " + a.proximo());
        System.out.println("senha " + a.novaSenha(2));
        System.out.println("prox " + a.proximo());
        System.out.println("senha " + a.novaSenha(4));
        System.out.println("prox " + a.proximo());
        System.out.println("prox " + a.proximo());
        a.inicio(); System.out.println("ED VAZIA");
        System.out.println(a.proximo());
    }
}
