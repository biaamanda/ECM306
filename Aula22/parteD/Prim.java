import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim {

    static class Aresta {
        int origem, destino, peso;

        Aresta(int origem, int destino, int peso) {
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }
    }

    int V;
    ArrayList<ArrayList<Aresta>> adj;

    public Prim(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    public void addAresta(int u, int v, int peso) {
        adj.get(u).add(new Aresta(u, v, peso));
        adj.get(v).add(new Aresta(v, u, peso));
    }

    public ArrayList<Aresta> agm(int raiz) {
        boolean[] naArvore = new boolean[V];
        ArrayList<Aresta> arvore = new ArrayList<>();
        PriorityQueue<Aresta> fila = new PriorityQueue<>((a, b) -> a.peso - b.peso);

        naArvore[raiz] = true;
        fila.addAll(adj.get(raiz));

        while (!fila.isEmpty() && arvore.size() < V - 1) {
            Aresta e = fila.poll();               // aresta leve que cruza o corte
            if (naArvore[e.destino])
                continue;                         // formaria ciclo: descarta
            naArvore[e.destino] = true;
            arvore.add(e);
            for (Aresta a : adj.get(e.destino))
                if (!naArvore[a.destino])
                    fila.add(a);
        }
        return arvore;
    }

    public static void main(String[] args) {
        String nomes = "abcdefghi";
        Prim g = new Prim(9);
        g.addAresta(0, 1, 4);   // a-b
        g.addAresta(0, 7, 8);   // a-h
        g.addAresta(1, 2, 8);   // b-c
        g.addAresta(1, 7, 11);  // b-h
        g.addAresta(2, 3, 7);   // c-d
        g.addAresta(2, 5, 4);   // c-f
        g.addAresta(2, 8, 2);   // c-i
        g.addAresta(3, 4, 9);   // d-e
        g.addAresta(3, 5, 14);  // d-f
        g.addAresta(4, 5, 10);  // e-f
        g.addAresta(5, 6, 2);   // f-g
        g.addAresta(6, 7, 1);   // g-h
        g.addAresta(6, 8, 6);   // g-i
        g.addAresta(7, 8, 7);   // h-i

        int total = 0;
        for (Aresta e : g.agm(0)) {
            System.out.println(nomes.charAt(e.origem) + "-" + nomes.charAt(e.destino) + " (" + e.peso + ")");
            total += e.peso;
        }
        System.out.println("Peso total: " + total);
    }
}
