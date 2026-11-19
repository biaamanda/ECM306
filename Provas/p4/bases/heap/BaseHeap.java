/*
 * BASE: HEAP / FILA DE PRIORIDADE (com botoes)
 * ---------------------------------------------------------------------------
 * USE QUANDO a questao pedir: fila de prioridade, atendimento por prioridade,
 * senhas, "o mais urgente primeiro", "o maior/menor primeiro", HeapSort.
 *
 * COMO FUNCIONA O HEAP (vetor comecando no indice 1):
 *   filho esquerdo = 2*i     filho direito = 2*i + 1     pai = i/2
 *   inserir: coloca no fim e SOBE trocando com o pai      -> O(log n)
 *   remover: tira a raiz, poe o ultimo na raiz e DESCE    -> O(log n)
 *
 * TRUQUE DAS SENHAS: senha = prioridade * 1000 + contador
 *   1001, 1002 (prioridade 1) < 2001 (prioridade 2) < 3001 ...
 *   -> um MIN-heap de inteiros ja atende na ordem certa.
 *
 * PARA ADAPTAR, procure os comentarios "ALTERE".
 *
 * GERAR O JAR (como tem janela, abre ate com duplo clique):
 *   javac --release 8 -Xlint:-options *.java
 *   jar cfe Heap.jar BaseHeap *.class
 *   java -jar Heap.jar
 */

import javax.swing.JOptionPane;

// ---------------------------------------------------------------------------
// Heap de inteiros. Funciona como MIN-heap ou MAX-heap.
// ---------------------------------------------------------------------------
class Heap {
    private int[] v = new int[11];      // posicao 0 nao e usada
    private int n = 0;                  // quantos elementos tem
    private final boolean min;          // true = min-heap, false = max-heap

    Heap(boolean min) { this.min = min; }

    boolean vazio() { return n == 0; }
    int tamanho() { return n; }
    void limpar() { n = 0; }
    int topo() { return v[1]; }          // olha sem tirar

    // "a deve ficar acima de b?"  min: a < b   |   max: a > b
    private boolean antes(int a, int b) { return min ? a < b : a > b; }

    void inserir(int x) {
        if (n + 1 == v.length) v = java.util.Arrays.copyOf(v, v.length * 2);   // aumenta o vetor
        v[++n] = x;
        int i = n;
        while (i > 1 && antes(v[i], v[i / 2])) {   // SOBE enquanto for "melhor" que o pai
            troca(i, i / 2);
            i = i / 2;
        }
    }

    int remover() {                                // chame so se !vazio()
        int raiz = v[1];
        v[1] = v[n--];
        int i = 1;
        while (2 * i <= n) {                       // DESCE enquanto tiver filho "melhor"
            int f = 2 * i;
            if (f + 1 <= n && antes(v[f + 1], v[f])) f++;   // escolhe o melhor filho
            if (!antes(v[f], v[i])) break;
            troca(i, f);
            i = f;
        }
        return raiz;
    }

    // Mostra o vetor do heap (util se a questao pedir para "apresentar a ED")
    String mostrar() {
        if (n == 0) return "ED VAZIA";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) sb.append(v[i]).append(i < n ? ", " : "");
        return sb.toString();
    }

    private void troca(int a, int b) { int t = v[a]; v[a] = v[b]; v[b] = t; }
}

// ---------------------------------------------------------------------------
// Regras do problema (ex.: consultorio). Aqui fica o que muda de prova para prova.
// ---------------------------------------------------------------------------
class Atendimento {
    // ALTERE: true = menor numero sai primeiro (senhas). false = maior sai primeiro.
    private final Heap heap = new Heap(true);

    // ALTERE: quantidade de prioridades (aqui 4 -> indices 1..4)
    private final int[] contador = new int[5];

    void inicio() {
        heap.limpar();
        java.util.Arrays.fill(contador, 0);
    }

    // ALTERE: formato da senha. Com 1000, cada prioridade aceita ate 999 pessoas.
    int novaSenha(int prioridade) {
        contador[prioridade]++;
        int senha = prioridade * 1000 + contador[prioridade];
        heap.inserir(senha);
        return senha;
    }

    String proximo() {
        if (heap.vazio()) return "Nao ha paciente para ser atendido (ED VAZIA)";
        return "Proximo: " + heap.remover();
    }

    String mostrar() { return heap.mostrar(); }
}

public class BaseHeap {
    public static void main(String[] args) {
        Atendimento a = new Atendimento();

        // ALTERE: os botoes que a questao pedir (o indice de cada um e usado no if abaixo)
        String[] botoes = {"INICIO", "PRIORIDADE 1", "PRIORIDADE 2", "PRIORIDADE 3",
                           "PRIORIDADE 4", "PROXIMO", "SAIR"};

        while (true) {
            int op = JOptionPane.showOptionDialog(null, "Fila: " + a.mostrar(), "Consultorio",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, botoes, botoes[0]);

            if (op == -1 || op == 6) break;                 // fechou a janela ou SAIR

            String msg;
            if (op == 0) {                                  // INICIO
                a.inicio();
                msg = "ED VAZIA";
            } else if (op >= 1 && op <= 4) {                // PRIORIDADE 1..4 (o indice e a prioridade)
                msg = "Senha: " + a.novaSenha(op);
            } else {                                        // PROXIMO
                msg = a.proximo();
            }
            JOptionPane.showMessageDialog(null, msg);
        }
    }
}
