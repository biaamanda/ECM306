import java.util.ArrayDeque;

/**
 * Fluxo máximo pelo algoritmo de Edmonds-Karp (Ford-Fulkerson com BFS).
 *
 * 1. BFS: acha o caminho mais curto de S até T que ainda tenha capacidade sobrando.
 * 2. Gargalo: a menor capacidade residual nesse caminho.
 * 3. Atualiza: empurra o gargalo pelo caminho (e subtrai nas reversas).
 * 4. Repete até não existir mais caminho.
 *
 * Pelo teorema do fluxo máximo / corte mínimo, quando o algoritmo para,
 * os vértices alcançáveis a partir de S no grafo residual formam um lado do
 * corte mínimo. Complexidade: O(V * E^2).
 */
public final class EdmondsKarp {

    private EdmondsKarp() {
    }

    public static long fluxoMaximo(Grafo g, int s, int t) {
        int n = g.numVertices();
        Aresta[] veioPor = new Aresta[n]; // aresta usada para chegar em cada vértice
        int[] visitadoNaRodada = new int[n]; // evita limpar um vetor de boolean a cada BFS
        int rodada = 0;
        long fluxoTotal = 0;

        while (true) {
            rodada++;

            // 1. BFS no grafo residual
            ArrayDeque<Integer> fila = new ArrayDeque<>();
            fila.add(s);
            visitadoNaRodada[s] = rodada;
            boolean chegouEmT = false;

            while (!fila.isEmpty() && !chegouEmT) {
                int u = fila.poll();
                for (Aresta a : g.vizinhos(u)) {
                    int v = a.destino;
                    if (a.residual() > 0 && visitadoNaRodada[v] != rodada) {
                        visitadoNaRodada[v] = rodada;
                        veioPor[v] = a;
                        if (v == t) {
                            chegouEmT = true;
                            break;
                        }
                        fila.add(v);
                    }
                }
            }

            if (!chegouEmT) {
                return fluxoTotal; // 4. não há mais caminho: o fluxo é máximo
            }

            // 2. gargalo do caminho (anda de T para trás até S)
            int gargalo = Integer.MAX_VALUE;
            for (int v = t; v != s; v = veioPor[v].reversa.destino) {
                gargalo = Math.min(gargalo, veioPor[v].residual());
            }

            // 3. empurra o gargalo por todo o caminho
            for (int v = t; v != s; v = veioPor[v].reversa.destino) {
                veioPor[v].empurrar(gargalo);
            }
            fluxoTotal += gargalo;
        }
    }

    /**
     * Depois do fluxo máximo: BFS a partir de S usando só arestas com residual > 0.
     * Os vértices marcados ficam do lado de S no corte mínimo.
     */
    public static boolean[] ladoDaFonte(Grafo g, int s) {
        boolean[] alcancado = new boolean[g.numVertices()];
        ArrayDeque<Integer> fila = new ArrayDeque<>();
        fila.add(s);
        alcancado[s] = true;
        while (!fila.isEmpty()) {
            int u = fila.poll();
            for (Aresta a : g.vizinhos(u)) {
                if (a.residual() > 0 && !alcancado[a.destino]) {
                    alcancado[a.destino] = true;
                    fila.add(a.destino);
                }
            }
        }
        return alcancado;
    }
}
