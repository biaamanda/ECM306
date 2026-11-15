/* Unidade 19 - Grafos Hamiltonianos
 *
 * FECHAMENTO c(G): enquanto existir um par de vertices NAO ADJACENTES
 * u,v com d(u)+d(v) >= n, adiciona a aresta u-v. Repete ate nao sobrar
 * nenhum par assim.
 *
 * TEOREMA DE BONDY: G e Hamiltoniano <=> c(G) e Hamiltoniano.
 * COROLARIO: se c(G) = Kn, entao G e Hamiltoniano.
 */

import java.util.List;

public class FechamentoDeGrafo {

    public static Grafo fechamento(Grafo g) {
        Grafo c = g.copiar();
        int n = c.numeroDeVertices();

        boolean mudou = true;
        while (mudou) {
            mudou = false;
            List<String> vs = c.getVertices();

            for (int i = 0; i < vs.size() && !mudou; i++) {
                for (int j = i + 1; j < vs.size() && !mudou; j++) {
                    String u = vs.get(i), v = vs.get(j);
                    if (!c.eAdjacente(u, v) && c.grau(u) + c.grau(v) >= n) {
                        c.adicionarAresta(u, v);
                        mudou = true; // reinicia a varredura (os graus mudaram)
                    }
                }
            }
        }
        return c;
    }

    private static void relatorio(String nome, Grafo g) {
        System.out.println("--- " + nome + " ---");
        System.out.println("G:  " + g.numeroDeVertices() + " vertices, " + g.numeroDeArestas() + " arestas");

        Grafo fechado = fechamento(g);
        int passos = fechado.numeroDeArestas() - g.numeroDeArestas();

        System.out.println("c(G): " + fechado.numeroDeArestas() + " arestas ("
                + passos + " passo(s) de fechamento)");
        System.out.println("c(G) e completo (Kn)? " + fechado.eCompleto());

        if (fechado.eCompleto()) {
            System.out.println("=> Pelo corolario de Bondy, G e Hamiltoniano!");
        }
        System.out.println("G e hamiltoniano (conferindo por forca bruta)? "
                + CicloCaminhoHamiltoniano.eHamiltoniano(g));
        System.out.println();
    }

    public static void main(String[] args) {
        // Grafo "pipa" dos slides 50-63: V1..V6, fechamento vira K6 em 7 passos.
        Grafo pipa = new Grafo();
        pipa.adicionarAresta("V1", "V2");
        pipa.adicionarAresta("V1", "V3");
        pipa.adicionarAresta("V1", "V4");
        pipa.adicionarAresta("V1", "V5");
        pipa.adicionarAresta("V2", "V4");
        pipa.adicionarAresta("V3", "V5");
        pipa.adicionarAresta("V4", "V6");
        pipa.adicionarAresta("V5", "V6");
        relatorio("Grafo 'pipa' (slides 50-63)", pipa);
        System.out.println("Esperado: c(G) = K6 (15 arestas) em 7 passos.");

        System.out.println();

        // Um caminho simples e bem esparso: nenhum par nao-adjacente chega
        // a d(u)+d(v) >= n, entao o fechamento nao faz nada (c(G) = G).
        Grafo caminho = new Grafo();
        caminho.adicionarAresta("V1", "V2");
        caminho.adicionarAresta("V2", "V3");
        caminho.adicionarAresta("V3", "V4");
        caminho.adicionarAresta("V4", "V5");
        relatorio("Caminho V1-V2-V3-V4-V5 (esparso)", caminho);
        System.out.println("Esperado: c(G) = G (0 passos) - fechamento nao ajuda aqui.");
    }
}
