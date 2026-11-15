/* Unidade 17 - Introducao a Teoria dos Grafos
 *
 * SUBGRAFO (Definicao 3.10): G2=(V2,E2) e subgrafo de G1=(V1,E1) se
 *   V2 subconjunto de V1,  E2 subconjunto de E1,
 * e toda aresta de E2 tem as duas extremidades em V2 (automatico, ja que
 * as arestas vem de G1). Nesse caso G1 e chamado supergrafo de G2.
 *
 * Casos particulares (nota do slide 70):
 *   - todo grafo e subgrafo de si mesmo;
 *   - um subgrafo de um subgrafo de G e subgrafo de G;
 *   - um unico vertice de G (sem arestas) ja e um subgrafo de G;
 *   - uma unica aresta de G, com seus vertices-extremidade, e subgrafo de G.
 */

import java.util.List;

public class Subgrafo {

    // V2 subconjunto de V1 ?
    public static boolean verticesContidos(Grafo g1, Grafo g2) {
        return g1.getVertices().containsAll(g2.getVertices());
    }

    // E2 subconjunto de E1 ? (cada aresta de g2 precisa ter uma correspondente
    // em g1 com o MESMO par de vertices - conta multiplicidade de paralelas).
    public static boolean arestasContidas(Grafo g1, Grafo g2) {
        List<Grafo.Aresta> disponiveis = new java.util.ArrayList<>(g1.getArestas());

        for (Grafo.Aresta e2 : g2.getArestas()) {
            Grafo.Aresta achada = null;
            for (Grafo.Aresta e1 : disponiveis) {
                if (e1.mesmoPar(e2)) {
                    achada = e1;
                    break;
                }
            }
            if (achada == null) return false;
            disponiveis.remove(achada); // consome, para respeitar paralelas
        }
        return true;
    }

    public static boolean eSubgrafo(Grafo g1, Grafo g2) {
        return verticesContidos(g1, g2) && arestasContidas(g1, g2);
    }

    public static void main(String[] args) {
        // Grafo G (baseado na Figura 3.28): v1..v5, com v3-v5 em paralela.
        Grafo g = new Grafo();
        g.adicionarAresta("v1", "v2");
        g.adicionarAresta("v1", "v3");
        g.adicionarAresta("v2", "v3");
        g.adicionarAresta("v2", "v4");
        g.adicionarAresta("v3", "v5");
        g.adicionarAresta("v3", "v5"); // paralela

        System.out.println("G: ");
        g.imprimir();

        // G1: subgrafo valido (v1,v3,v5 com so a aresta v1-v3)
        Grafo g1 = new Grafo();
        g1.adicionarAresta("v1", "v3");
        g1.adicionarVertice("v5");

        // G2: NAO e subgrafo - tem uma aresta (v1,v4) que nao existe em G.
        Grafo g2 = new Grafo();
        g2.adicionarAresta("v1", "v4");

        // G3: subgrafo valido - so o vertice v2, sem arestas.
        Grafo g3 = new Grafo();
        g3.adicionarVertice("v2");

        // G4: NAO e subgrafo - tem so UMA das duas paralelas de v3-v5 (isso
        // ESTA ok, mas aqui pedimos as duas MAIS uma terceira inexistente).
        Grafo g4 = new Grafo();
        g4.adicionarAresta("v3", "v5");
        g4.adicionarAresta("v3", "v5");
        g4.adicionarAresta("v3", "v5"); // essa 3a paralela nao existe em G

        System.out.println("\nG1 (v1-v3, +v5 isolado) e subgrafo de G? "
                + eSubgrafo(g, g1) + "  (esperado true)");
        System.out.println("G2 (aresta v1-v4, que nao existe em G) e subgrafo de G? "
                + eSubgrafo(g, g2) + "  (esperado false)");
        System.out.println("G3 (so o vertice v2) e subgrafo de G? "
                + eSubgrafo(g, g3) + "  (esperado true)");
        System.out.println("G4 (3 paralelas v3-v5, so existem 2 em G) e subgrafo de G? "
                + eSubgrafo(g, g4) + "  (esperado false)");
        System.out.println("G e subgrafo de si mesmo? " + eSubgrafo(g, g) + "  (esperado true)");
    }
}
