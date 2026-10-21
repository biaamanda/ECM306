import java.util.ArrayList;

public class BuscaProfundidade {

    enum Cor { BRANCO, CINZA, PRETO }

    int V;
    ArrayList<ArrayList<Integer>> adj;
    Cor[] cor;
    int[] pai, d, f;
    int tempo;

    public BuscaProfundidade(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    public void addAresta(int u, int v) {
        adj.get(u).add(v);
    }

    public void dfs() {
        cor = new Cor[V];
        pai = new int[V];
        d = new int[V];
        f = new int[V];
        for (int u = 0; u < V; u++) {
            cor[u] = Cor.BRANCO;
            pai[u] = -1;
        }
        tempo = 0;
        for (int u = 0; u < V; u++)
            if (cor[u] == Cor.BRANCO)
                visita(u);
    }

    private void visita(int u) {
        tempo++;
        d[u] = tempo;                 // u acaba de ser descoberto
        cor[u] = Cor.CINZA;
        System.out.print(" " + u);
        for (int v : adj.get(u)) {
            if (cor[v] == Cor.BRANCO) {
                pai[v] = u;
                visita(v);
            }
        }
        cor[u] = Cor.PRETO;           // todos os vizinhos de u foram explorados
        tempo++;
        f[u] = tempo;
    }

    public static void main(String[] args) {
        BuscaProfundidade g = new BuscaProfundidade(6);
        g.addAresta(0, 1);
        g.addAresta(0, 2);
        g.addAresta(1, 2);
        g.addAresta(1, 3);
        g.addAresta(3, 4);
        g.addAresta(2, 3);
        g.addAresta(4, 0);
        g.addAresta(4, 1);
        g.addAresta(4, 5);

        System.out.print("Ordem DFS:");
        g.dfs();
        System.out.println();
        for (int u = 0; u < g.V; u++)
            System.out.println(u + ": d=" + g.d[u] + " f=" + g.f[u] + " pai=" + g.pai[u]);
    }
}
