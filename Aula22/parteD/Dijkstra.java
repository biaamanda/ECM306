import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {

    static class Aresta {
        int destino, peso;

        Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    int V;
    ArrayList<ArrayList<Aresta>> adj;
    int[] dist, pai;

    public Dijkstra(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    public void addAresta(int u, int v, int peso) {
        adj.get(u).add(new Aresta(v, peso));
        adj.get(v).add(new Aresta(u, peso));      // grafo não-direcionado
    }

    public void caminhosMinimos(int s) {
        dist = new int[V];
        pai = new int[V];
        boolean[] fechado = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);     // INITIALIZE-SINGLE-SOURCE
        Arrays.fill(pai, -1);
        dist[s] = 0;

        // cada entrada da fila é {vértice, distância}
        PriorityQueue<int[]> fila = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        fila.add(new int[]{s, 0});

        while (!fila.isEmpty()) {
            int u = fila.poll()[0];               // EXTRACT-MIN
            if (fechado[u])
                continue;
            fechado[u] = true;
            for (Aresta e : adj.get(u)) {         // RELAX(u, v, w)
                int v = e.destino;
                if (!fechado[v] && dist[u] + e.peso < dist[v]) {
                    dist[v] = dist[u] + e.peso;
                    pai[v] = u;
                    fila.add(new int[]{v, dist[v]});
                }
            }
        }
    }

    public String caminho(int v, String nomes) {
        if (pai[v] == -1)
            return "" + nomes.charAt(v);
        return caminho(pai[v], nomes) + " -> " + nomes.charAt(v);
    }

    public static void main(String[] args) {
        String nomes = "abcdefghi";
        Dijkstra g = new Dijkstra(9);
        g.addAresta(0, 1, 4);
        g.addAresta(0, 7, 8);
        g.addAresta(1, 2, 8);
        g.addAresta(1, 7, 11);
        g.addAresta(2, 3, 7);
        g.addAresta(2, 5, 4);
        g.addAresta(2, 8, 2);
        g.addAresta(3, 4, 9);
        g.addAresta(3, 5, 14);
        g.addAresta(4, 5, 10);
        g.addAresta(5, 6, 2);
        g.addAresta(6, 7, 1);
        g.addAresta(6, 8, 6);
        g.addAresta(7, 8, 7);

        g.caminhosMinimos(0);
        for (int v = 0; v < g.V; v++)
            System.out.println(nomes.charAt(v) + ": dist=" + g.dist[v] + "  caminho: " + g.caminho(v, nomes));
    }
}
