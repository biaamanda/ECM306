import java.util.LinkedList;
import java.util.Queue;

// Versão do BFS no estilo do Cormen: além da ordem de visita, calcula
// a distância (d) e o pai (π) de cada vértice e mostra a fila a cada passo.
// Reaproveita as classes Node, adjList e Graph de GraphBFS.java.
public class BFSDistancias {

    static final int INFINITO = -1;

    static void bfs(Graph g, int s, String nomes) {
        boolean[] visitado = new boolean[g.V];      // visitado == cor diferente de BRANCO
        int[] d = new int[g.V];
        int[] pai = new int[g.V];
        for (int u = 0; u < g.V; u++) {
            d[u] = INFINITO;
            pai[u] = -1;
        }

        Queue<Integer> fila = new LinkedList<Integer>();
        visitado[s] = true;                          // s fica CINZA
        d[s] = 0;
        fila.add(s);

        System.out.println("Fila inicial: " + nomesDe(fila, nomes));
        while (!fila.isEmpty()) {
            int u = fila.poll();                     // DEQUEUE
            Node v = g.array[u].head;
            while (v != null) {
                if (!visitado[v.dest]) {             // v BRANCO
                    visitado[v.dest] = true;         // v fica CINZA
                    d[v.dest] = d[u] + 1;
                    pai[v.dest] = u;
                    fila.add(v.dest);                // ENQUEUE
                }
                v = v.next;
            }
            // u fica PRETO
            System.out.println("Processa " + nomes.charAt(u) + " -> fila: " + nomesDe(fila, nomes));
        }

        for (int u = 0; u < g.V; u++)
            System.out.println(nomes.charAt(u) + ": d=" + (d[u] == INFINITO ? "inf" : d[u])
                    + " pai=" + (pai[u] == -1 ? "NIL" : "" + nomes.charAt(pai[u])));
    }

    static String nomesDe(Queue<Integer> fila, String nomes) {
        StringBuilder sb = new StringBuilder("[");
        for (int x : fila)
            sb.append(sb.length() > 1 ? " " : "").append(nomes.charAt(x));
        return sb.append("]").toString();
    }

    static void addNaoDirecionada(Graph g, int u, int v) {
        g.addEdge(u, v);
        g.addEdge(v, u);
    }

    public static void main(String[] args) {
        // 1) Grafo direcionado do enunciado, a partir do vértice 0
        System.out.println("=== Grafo do enunciado (direcionado), origem 0 ===");
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(2, 3);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(4, 5);
        bfs(g, 0, "012345");

        // 2) Grafo do Cormen (não-direcionado), a partir de s
        //    índices: r=0 s=1 t=2 u=3 v=4 w=5 x=6 y=7
        System.out.println();
        System.out.println("=== Grafo do Cormen (nao-direcionado), origem s ===");
        Graph c = new Graph(8);
        addNaoDirecionada(c, 0, 1);  // r-s
        addNaoDirecionada(c, 0, 4);  // r-v
        addNaoDirecionada(c, 1, 5);  // s-w
        addNaoDirecionada(c, 5, 2);  // w-t
        addNaoDirecionada(c, 5, 6);  // w-x
        addNaoDirecionada(c, 2, 6);  // t-x
        addNaoDirecionada(c, 2, 3);  // t-u
        addNaoDirecionada(c, 6, 7);  // x-y
        addNaoDirecionada(c, 3, 7);  // u-y
        bfs(c, 1, "rstuvwxy");
    }
}
