/*
 *   PERGUNTA DA QUESTAO                              METODO
 *   graus / sequencia de graus / regular?            grau(), sequenciaGraus()
 *   e bipartido? (2 cores, sem ciclo impar)          bipartido()
 *   menor numero de conexoes / saltos                bfs() + caminho()
 *   percorrer em profundidade                        dfs()
 *   menor CUSTO (pesos >= 0)                         dijkstra() + caminho()
 *   ordem de tarefas com dependencias                ordemTopologica()
 *
 * VERTICES: guardados como numeros 0..n-1 e mostrados com nome (A, B, C... ou o
 * que voce passar em "nomes").
 *
 * PARA ADAPTAR, procure os comentarios "ALTERE".
 *
 * GERAR O JAR:
 *   javac --release 8 -Xlint:-options *.java
 *   jar cfe Grafo.jar BaseGrafo *.class
 *   java -jar Grafo.jar
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Aresta {
    final int destino;
    final int peso;
    Aresta(int destino, int peso) { this.destino = destino; this.peso = peso; }
}

class Grafo {
    private final List<List<Aresta>> adj = new ArrayList<>();
    private final String[] nomes;
    private final boolean dirigido;

    // ALTERE: dirigido = true para setas (dependencias, "A depende de B", rede de mao unica)
    Grafo(String[] nomes, boolean dirigido) {
        this.nomes = nomes;
        this.dirigido = dirigido;
        for (int i = 0; i < nomes.length; i++) adj.add(new ArrayList<>());
    }

    int n() { return nomes.length; }
    String nome(int v) { return nomes[v]; }
    List<Aresta> vizinhos(int v) { return adj.get(v); }

    // Converte nome -> indice (-1 se nao existe)
    int indice(String nome) {
        for (int i = 0; i < nomes.length; i++) if (nomes[i].equalsIgnoreCase(nome)) return i;
        return -1;
    }

    // Sem peso: use peso 1
    void addAresta(int u, int v, int peso) {
        adj.get(u).add(new Aresta(v, peso));
        if (!dirigido) adj.get(v).add(new Aresta(u, peso));
    }

    // ---------------------------------------------------------------- GRAUS
    // Nao dirigido: grau = numero de arestas que tocam (laco conta 2).
    // Dirigido: isto e o grau de SAIDA (setas que saem de v).
    int grau(int v) {
        int g = 0;
        for (Aresta a : adj.get(v)) g += (a.destino == v ? 2 : 1);
        return g;
    }

    // Graus em ordem decrescente, ex.: [4, 4, 3, 3, 2]
    int[] sequenciaGraus() {
        Integer[] s = new Integer[n()];
        for (int v = 0; v < n(); v++) s[v] = grau(v);
        Arrays.sort(s, Collections.reverseOrder());
        int[] r = new int[n()];
        for (int i = 0; i < n(); i++) r[i] = s[i];
        return r;
    }

    // ---------------------------------------------------------------- BIPARTIDO
    // Pinta com 2 cores via BFS. Se dois vizinhos tiverem a mesma cor -> nao e bipartido.
    boolean bipartido() {
        int[] cor = new int[n()];
        Arrays.fill(cor, -1);
        for (int s = 0; s < n(); s++) {
            if (cor[s] != -1) continue;
            cor[s] = 0;
            Queue<Integer> fila = new ArrayDeque<>();
            fila.add(s);
            while (!fila.isEmpty()) {
                int u = fila.remove();
                for (Aresta a : adj.get(u)) {
                    if (cor[a.destino] == -1) { cor[a.destino] = 1 - cor[u]; fila.add(a.destino); }
                    else if (cor[a.destino] == cor[u]) return false;   // ciclo impar
                }
            }
        }
        return true;
    }

    // ---------------------------------------------------------------- BFS
    // Menor numero de ARESTAS da origem ate cada vertice. -1 = inalcancavel. O(V+E)
    // "anterior" e preenchido para reconstruir o caminho com caminho().
    int[] bfs(int origem, int[] anterior) {
        int[] dist = new int[n()];
        Arrays.fill(dist, -1);
        Arrays.fill(anterior, -1);
        Queue<Integer> fila = new ArrayDeque<>();
        dist[origem] = 0;
        fila.add(origem);
        while (!fila.isEmpty()) {
            int u = fila.remove();
            for (Aresta a : adj.get(u)) {
                if (dist[a.destino] == -1) {
                    dist[a.destino] = dist[u] + 1;
                    anterior[a.destino] = u;
                    fila.add(a.destino);
                }
            }
        }
        return dist;
    }

    // ---------------------------------------------------------------- DFS
    // Ordem de visita em profundidade (vai fundo e volta: backtracking). O(V+E)
    List<Integer> dfs(int origem) {
        List<Integer> ordem = new ArrayList<>();
        dfsVisita(origem, new boolean[n()], ordem);
        return ordem;
    }
    private void dfsVisita(int u, boolean[] visitado, List<Integer> ordem) {
        visitado[u] = true;
        ordem.add(u);
        for (Aresta a : adj.get(u))
            if (!visitado[a.destino]) dfsVisita(a.destino, visitado, ordem);
    }

    // ---------------------------------------------------------------- DIJKSTRA
    // Menor CUSTO da origem ate cada vertice. So funciona com pesos >= 0.
    // Integer.MAX_VALUE = inalcancavel. O((V+E) log V)
    int[] dijkstra(int origem, int[] anterior) {
        int[] dist = new int[n()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(anterior, -1);
        PriorityQueue<int[]> fila = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        dist[origem] = 0;
        fila.add(new int[]{origem, 0});
        while (!fila.isEmpty()) {
            int[] atual = fila.remove();
            int u = atual[0];
            if (atual[1] != dist[u]) continue;           // entrada velha, ignora
            for (Aresta a : adj.get(u)) {
                int nova = dist[u] + a.peso;              // RELAXAMENTO
                if (nova < dist[a.destino]) {
                    dist[a.destino] = nova;
                    anterior[a.destino] = u;
                    fila.add(new int[]{a.destino, nova});
                }
            }
        }
        return dist;
    }

    // Reconstroi o caminho da origem ate "destino" usando "anterior" (BFS ou Dijkstra)
    List<Integer> caminho(int destino, int[] anterior) {
        List<Integer> rota = new ArrayList<>();
        for (int v = destino; v != -1; v = anterior[v]) rota.add(v);
        Collections.reverse(rota);
        return rota;
    }

    // ---------------------------------------------------------------- TOPOLOGICA
    // Algoritmo de Kahn (grafo DIRIGIDO; aresta A->B = "B depende de A"). O(V+E)
    // Devolve null se houver dependencia circular (ciclo).
    List<Integer> ordemTopologica() {
        int[] entrada = new int[n()];
        for (int u = 0; u < n(); u++) for (Aresta a : adj.get(u)) entrada[a.destino]++;
        Queue<Integer> fila = new ArrayDeque<>();
        for (int v = 0; v < n(); v++) if (entrada[v] == 0) fila.add(v);
        List<Integer> ordem = new ArrayList<>();
        while (!fila.isEmpty()) {
            int u = fila.remove();
            ordem.add(u);
            for (Aresta a : adj.get(u)) if (--entrada[a.destino] == 0) fila.add(a.destino);
        }
        return ordem.size() == n() ? ordem : null;
    }

    // ---------------------------------------------------------------- HAMILTON
    // Backtracking (forca bruta): tenta montar um ciclo que passe por TODOS os
    // vertices uma vez e volte ao inicio. Devolve o ciclo ou null. Cresce fatorialmente.

    private boolean hamilton(List<Integer> caminho, boolean[] usado) {
        int u = caminho.get(caminho.size() - 1);
        if (caminho.size() == n()) return temAresta(u, 0);          // fecha o ciclo?
        for (Aresta a : adj.get(u)) {
            if (usado[a.destino]) continue;
            usado[a.destino] = true;
            caminho.add(a.destino);
            if (hamilton(caminho, usado)) return true;
            caminho.remove(caminho.size() - 1);                       // BACKTRACKING: desfaz
            usado[a.destino] = false;
        }
        return false;
    }
    boolean temAresta(int u, int v) {
        for (Aresta a : adj.get(u)) if (a.destino == v) return true;
        return false;
    }

    // Lista os nomes de uma lista de indices: [0, 2, 1] -> "A -> C -> B"
    String nomes(List<Integer> vs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vs.size(); i++) sb.append(nome(vs.get(i))).append(i < vs.size() - 1 ? " -> " : "");
        return sb.toString();
    }
}
