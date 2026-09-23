/* Unidade 19 - Grafos Hamiltonianos
 *
 * TEOREMA DE DIRAC:  n>=3 e d(v) >= n/2 para TODO v  =>  G e Hamiltoniano.
 * TEOREMA DE ORE:     n>=3 e d(u)+d(v) >= n para todo par NAO ADJACENTE
 *                      u,v  =>  G e Hamiltoniano.
 *
 * As duas condicoes sao SUFICIENTES, mas NAO NECESSARIAS - por isso os
 * testes abaixo tambem mostram um grafo Hamiltoniano (o ciclo C5) que
 * passa longe de satisfazer qualquer uma delas.
 */

import java.util.List;

public class TeoremaDiracOre {

    public static boolean satisfazDirac(Grafo g) {
        int n = g.numeroDeVertices();
        if (n < 3) return false;
        for (String v : g.getVertices()) {
            if (g.grau(v) < n / 2.0) {
                return false;
            }
        }
        return true;
    }

    public static boolean satisfazOre(Grafo g) {
        int n = g.numeroDeVertices();
        if (n < 3) return false;
        List<String> vs = g.getVertices();
        for (int i = 0; i < vs.size(); i++) {
            for (int j = i + 1; j < vs.size(); j++) {
                String u = vs.get(i), v = vs.get(j);
                if (!g.eAdjacente(u, v) && g.grau(u) + g.grau(v) < n) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void relatorio(String nome, Grafo g) {
        System.out.println("--- " + nome + " ---");
        g.imprimir();
        boolean dirac = satisfazDirac(g);
        boolean ore = satisfazOre(g);
        boolean hamiltoniano = CicloCaminhoHamiltoniano.eHamiltoniano(g);
        System.out.println("satisfaz Dirac? " + dirac);
        System.out.println("satisfaz Ore?   " + ore);
        System.out.println("e hamiltoniano (forca bruta)? " + hamiltoniano);
        if (hamiltoniano) {
            System.out.println("ciclo encontrado: " + CicloCaminhoHamiltoniano.buscarCiclo(g));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // K3,3 - cada vertice tem grau 3 = 6/2 -> satisfaz Dirac (slide 38).
        Grafo k33 = new Grafo();
        String[] x = {"A", "B", "C"};
        String[] y = {"U", "V", "E"};
        for (String xi : x) for (String yj : y) k33.adicionarAresta(xi, yj);
        relatorio("K3,3 (slide 38)", k33);

        // C5 (pentagono) - grau 2 < 5/2 em todo mundo: Dirac e Ore FALHAM,
        // mas o proprio ciclo ja e Hamiltoniano (slides 42 e 48).
        Grafo c5 = new Grafo();
        c5.adicionarAresta("v1", "v2");
        c5.adicionarAresta("v2", "v3");
        c5.adicionarAresta("v3", "v4");
        c5.adicionarAresta("v4", "v5");
        c5.adicionarAresta("v5", "v1");
        relatorio("C5 - contraexemplo (slides 42, 48)", c5);

        // Grafo de 5 vertices do slide 46: K5 menos as arestas v1-v5 e v3-v5.
        // Nao satisfaz Dirac (v5 tem grau 2 < 5/2) mas satisfaz Ore.
        Grafo g5 = new Grafo();
        g5.adicionarAresta("v1", "v2");
        g5.adicionarAresta("v1", "v3");
        g5.adicionarAresta("v1", "v4");
        g5.adicionarAresta("v2", "v3");
        g5.adicionarAresta("v2", "v5");
        g5.adicionarAresta("v3", "v4");
        g5.adicionarAresta("v4", "v5");
        relatorio("Grafo do slide 46 (Ore ok, Dirac nao)", g5);
    }
}
