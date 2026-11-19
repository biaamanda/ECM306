/*
 * BASE: EMPARELHAMENTO EM GRAFO BIPARTIDO (Teorema de Berge)
 * ---------------------------------------------------------------------------
 * USE QUANDO a questao pedir para ALOCAR / ATRIBUIR / DISTRIBUIR:
 *   professores x dias, casais x quartos, onibus x vagas, equipes x chamados,
 *   hospedes x quartos, tarefas x maquinas...
 *
 * MODELO:
 *   lado ESQUERDO = quem precisa ser alocado (professores, casais, onibus)
 *   lado DIREITO  = os recursos (dias, quartos, vagas)
 *   aresta        = "esse da esquerda ACEITA esse da direita"
 *
 * RESPOSTA:
 *   total == qtd da esquerda  -> todos alocados (emparelhamento COMPLETO/PERFEITO)
 *   total <  qtd da esquerda  -> "sem solucao" (Teorema de Hall violado)
 *
 * PARA ADAPTAR, procure os comentarios "ALTERE".
 *
 * GERAR O JAR:
 *   javac --release 8 -Xlint:-options *.java
 *   jar cfe Emparelhamento.jar BaseEmparelhamento *.class
 *   java -jar Emparelhamento.jar
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// ---------------------------------------------------------------------------
// Guarda o grafo: para cada vertice da ESQUERDA, a lista de vertices da DIREITA
// que ele aceita. (Nao precisa alterar.)
// ---------------------------------------------------------------------------
class GrafoBipartido {
    private final List<List<Integer>> adj = new ArrayList<>();
    private final int qtdDireita;

    GrafoBipartido(int qtdEsquerda, int qtdDireita) {
        this.qtdDireita = qtdDireita;
        for (int i = 0; i < qtdEsquerda; i++) adj.add(new ArrayList<>());
    }

    void addAresta(int esquerda, int direita) { adj.get(esquerda).add(direita); }
    List<Integer> vizinhos(int esquerda) { return adj.get(esquerda); }
    int qtdEsquerda() { return adj.size(); }
    int qtdDireita() { return qtdDireita; }
}

// ---------------------------------------------------------------------------
// O algoritmo. (Nao precisa alterar.)
// par[d] = qual vertice da ESQUERDA ficou com o vertice d da DIREITA (-1 = livre)
// ---------------------------------------------------------------------------
class Emparelhamento {
    private final GrafoBipartido g;
    private final int[] par;

    Emparelhamento(GrafoBipartido g) {
        this.g = g;
        par = new int[g.qtdDireita()];
        Arrays.fill(par, -1);
    }

    // Tenta arrumar um recurso para "e". Se o recurso estiver ocupado,
    // pede para o dono atual trocar (caminho M-aumentante).
    private boolean tenta(int e, boolean[] visitado) {
        for (int d : g.vizinhos(e)) {
            if (visitado[d]) continue;
            visitado[d] = true;
            if (par[d] == -1 || tenta(par[d], visitado)) {
                par[d] = e;
                return true;
            }
        }
        return false;
    }

    // Devolve quantos da ESQUERDA conseguiram recurso.
    int resolver() {
        int total = 0;
        for (int e = 0; e < g.qtdEsquerda(); e++)
            if (tenta(e, new boolean[g.qtdDireita()])) total++;
        return total;
    }

    int[] getPar() { return par; }
}

public class BaseEmparelhamento {

    // ALTERE: nomes do lado ESQUERDO (quem e alocado)
    static final String[] ESQUERDA = {"P1", "P2", "P3", "P4", "P5"};

    // ALTERE: nomes do lado DIREITO (os recursos)
    static final String[] DIREITA = {"2a", "3a", "4a", "5a", "6a"};

    // ALTERE: como o usuario digita o recurso.
    // Aqui ele digita 2..6 (dia da semana) e o indice e 0..4, por isso "- 2".
    // Se ele digitar 1..N (ex.: quarto 1..6), troque por "- 1".
    static int converteEntrada(int digitado) { return digitado - 2; }

    // ALTERE: texto de ajuda mostrado antes da leitura
    static final String AJUDA = "Digite os numeros separados por espaco (2=segunda 3=terca 4=quarta 5=quinta 6=sexta)";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GrafoBipartido g = new GrafoBipartido(ESQUERDA.length, DIREITA.length);

        // 1. LER as preferencias/disponibilidades de cada um da esquerda
        System.out.println(AJUDA);
        for (int e = 0; e < ESQUERDA.length; e++) {
            System.out.print("Opcoes de " + ESQUERDA[e] + ": ");
            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) continue;                    // ninguem aceito
            for (String parte : linha.split("\\s+")) {        // "\\s+" = um ou mais espacos
                parte = parte.replaceAll("\\D", "");          // ignora o que nao for numero
                if (parte.isEmpty()) continue;
                int d = converteEntrada(Integer.parseInt(parte));
                if (d >= 0 && d < DIREITA.length) g.addAresta(e, d);   // ignora numero invalido
            }
        }

        // ALTERE (opcional): em vez de ler do teclado, fixe as arestas para testar:
        // g.addAresta(0, 1); g.addAresta(0, 3); ...

        // 2. RESOLVER
        Emparelhamento m = new Emparelhamento(g);
        int total = m.resolver();

        // 3. MOSTRAR
        // ALTERE: se a questao so pedir "o maximo possivel" (nao precisa alocar todos),
        // tire o if e sempre mostre os pares, informando o total.
        if (total < ESQUERDA.length) {
            System.out.println("Sem solucao: so " + total + " de " + ESQUERDA.length + " conseguem ser alocados.");
        } else {
            int[] par = m.getPar();
            for (int d = 0; d < DIREITA.length; d++)
                System.out.println(DIREITA[d] + " -> " + (par[d] == -1 ? "-" : ESQUERDA[par[d]]));
        }
        sc.close();
    }
}
