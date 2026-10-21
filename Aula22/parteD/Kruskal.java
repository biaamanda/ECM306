import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

    static class Aresta implements Comparable<Aresta> {
        int origem, destino, peso;

        Aresta(int origem, int destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }

        public int compareTo(Aresta outra) {
            return this.peso - outra.peso;
        }
    }

    // Conjuntos disjuntos (Union-Find)
    static class ConjuntosDisjuntos {
        int[] pai, rank;

        ConjuntosDisjuntos(int n) {
            pai = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++)
                pai[i] = i;                       // MAKE-SET
        }

        int find(int x) {                         // FIND-SET com compressão de caminho
            if (pai[x] != x)
                pai[x] = find(pai[x]);
            return pai[x];
        }

        boolean union(int x, int y) {             // UNION por rank
            int rx = find(x), ry = find(y);
            if (rx == ry)
                return false;
            if (rank[rx] < rank[ry])
                pai[rx] = ry;
            else if (rank[rx] > rank[ry])
                pai[ry] = rx;
            else {
                pai[ry] = rx;
                rank[rx]++;
            }
            return true;
        }
    }

    int V;
    ArrayList<Aresta> arestas = new ArrayList<>();

    public Kruskal(int V) {
        this.V = V;
    }

    public void addAresta(int u, int v, int peso) {
        arestas.add(new Aresta(u, v, peso));
    }

    public ArrayList<Aresta> agm() {
        ArrayList<Aresta> arvore = new ArrayList<>();
        ConjuntosDisjuntos cd = new ConjuntosDisjuntos(V);
        Collections.sort(arestas);

        for (Aresta e : arestas) {
            if (cd.union(e.origem, e.destino))    // aceita só se não formar ciclo
                arvore.add(e);
            if (arvore.size() == V - 1)
                break;
        }
        return arvore;
    }

    public static void main(String[] args) {
        String nomes = "abcdefghi";
        Kruskal g = new Kruskal(9);
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

        int total = 0;
        for (Aresta e : g.agm()) {
            System.out.println(nomes.charAt(e.origem) + "-" + nomes.charAt(e.destino) + " (" + e.peso + ")");
            total += e.peso;
        }
        System.out.println("Peso total: " + total);
    }
}
