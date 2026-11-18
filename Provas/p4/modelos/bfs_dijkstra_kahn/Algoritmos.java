import java.util.*;

class Aresta {
    final int destino, peso;
    Aresta(int destino, int peso) { this.destino = destino; this.peso = peso; }
}

class Grafo {
    private final List<List<Aresta>> adj = new ArrayList<>();
    Grafo(int n) { for (int i = 0; i < n; i++) adj.add(new ArrayList<>()); }
    int tamanho() { return adj.size(); }
    void addDirigida(int u, int v, int p) { adj.get(u).add(new Aresta(v, p)); }
    void addNaoDirigida(int u, int v, int p) { addDirigida(u, v, p); addDirigida(v, u, p); }
    List<Aresta> vizinhos(int u) { return adj.get(u); }

    // BFS: menor numero de arestas. O(V+E)
    int[] bfs(int origem, int[] anterior) {
        int[] dist = new int[tamanho()];
        Arrays.fill(dist, -1); Arrays.fill(anterior, -1);
        Queue<Integer> fila = new ArrayDeque<>();
        dist[origem] = 0; fila.add(origem);
        while (!fila.isEmpty()) {
            int u = fila.remove();
            for (Aresta a : vizinhos(u))
                if (dist[a.destino] == -1) {
                    dist[a.destino] = dist[u] + 1;
                    anterior[a.destino] = u;
                    fila.add(a.destino);
                }
        }
        return dist;
    }

    // Dijkstra: menor custo, pesos >= 0. O((V+E) log V)
    int[] dijkstra(int origem, int[] anterior) {
        int[] dist = new int[tamanho()];
        Arrays.fill(dist, Integer.MAX_VALUE); Arrays.fill(anterior, -1);
        PriorityQueue<int[]> fila = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        dist[origem] = 0; fila.add(new int[]{origem, 0});
        while (!fila.isEmpty()) {
            int[] at = fila.remove();
            int u = at[0];
            if (at[1] != dist[u]) continue;            // entrada velha
            for (Aresta a : vizinhos(u)) {
                int nova = dist[u] + a.peso;            // relaxamento
                if (nova < dist[a.destino]) {
                    dist[a.destino] = nova; anterior[a.destino] = u;
                    fila.add(new int[]{a.destino, nova});
                }
            }
        }
        return dist;
    }

    // Kahn: ordenacao topologica. O(V+E). Lanca excecao se houver ciclo
    List<Integer> ordemTopologica() {
        int n = tamanho();
        int[] entrada = new int[n];
        for (int u = 0; u < n; u++) for (Aresta a : vizinhos(u)) entrada[a.destino]++;
        Queue<Integer> fila = new ArrayDeque<>();
        for (int v = 0; v < n; v++) if (entrada[v] == 0) fila.add(v);
        List<Integer> ordem = new ArrayList<>();
        while (!fila.isEmpty()) {
            int u = fila.remove();
            ordem.add(u);
            for (Aresta a : vizinhos(u)) if (--entrada[a.destino] == 0) fila.add(a.destino);
        }
        if (ordem.size() != n) throw new IllegalStateException("Dependencia circular");
        return ordem;
    }

    static List<Integer> caminho(int destino, int[] anterior) {
        List<Integer> rota = new ArrayList<>();
        for (int v = destino; v != -1; v = anterior[v]) rota.add(v);
        Collections.reverse(rota);
        return rota;
    }
}

public class Algoritmos {
    public static void main(String[] args) {
        String nomes = "ABCDEF";
        // Exercicio 3 da T25: A-B=4, A-C=2, C-B=1, B-D=5, C-D=8, D-E=2, E-F=3, D-F=6
        Grafo g = new Grafo(6);
        int[][] e = {{0,1,4},{0,2,2},{2,1,1},{1,3,5},{2,3,8},{3,4,2},{4,5,3},{3,5,6}};
        for (int[] x : e) g.addNaoDirigida(x[0], x[1], x[2]);
        int[] ant = new int[6];
        int[] d = g.dijkstra(0, ant);
        StringBuilder sb = new StringBuilder();
        for (int v : Grafo.caminho(5, ant)) sb.append(nomes.charAt(v));
        System.out.println("Dijkstra dist=" + Arrays.toString(d) + " rota=" + sb);
        d = g.bfs(0, ant);
        System.out.println("BFS saltos=" + Arrays.toString(d));
        // Exercicio 5: A->C, B->C, C->D, B->E, D->F, E->F
        Grafo t = new Grafo(6);
        int[][] dep = {{0,2},{1,2},{2,3},{1,4},{3,5},{4,5}};
        for (int[] x : dep) t.addDirigida(x[0], x[1], 1);
        sb = new StringBuilder();
        for (int v : t.ordemTopologica()) sb.append(nomes.charAt(v));
        System.out.println("Topologica=" + sb);
        // Exercicio 6: A->B, B->C, C->A, C->D (ciclo)
        Grafo c = new Grafo(4);
        c.addDirigida(0,1,1); c.addDirigida(1,2,1); c.addDirigida(2,0,1); c.addDirigida(2,3,1);
        try { c.ordemTopologica(); } catch (IllegalStateException ex) { System.out.println("Ciclo: " + ex.getMessage()); }
    }
}
