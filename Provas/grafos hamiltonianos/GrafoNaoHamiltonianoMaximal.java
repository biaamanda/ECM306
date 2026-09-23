/* Unidade 19 - Grafos Hamiltonianos
 *
 * GRAFO NAO HAMILTONIANO MAXIMAL: G e simples, NAO e Hamiltoniano, mas
 * adicionar QUALQUER aresta entre vertices nao adjacentes o transforma
 * num grafo Hamiltoniano.
 */

import java.util.List;

public class GrafoNaoHamiltonianoMaximal {

    // Retorna null se G JA e hamiltoniano (a pergunta nao se aplica).
    // Caso contrario, devolve a lista de pares nao-adjacentes cuja adicao
    // NAO torna G hamiltoniano (lista vazia = G e nao-hamiltoniano maximal).
    public static List<String[]> paresQueFalham(Grafo g) {
        List<String[]> falhas = new java.util.ArrayList<>();
        if (CicloCaminhoHamiltoniano.eHamiltoniano(g)) {
            return null;
        }
        List<String> vs = g.getVertices();
        for (int i = 0; i < vs.size(); i++) {
            for (int j = i + 1; j < vs.size(); j++) {
                String u = vs.get(i), v = vs.get(j);
                if (g.eAdjacente(u, v)) continue;

                Grafo copia = g.copiar();
                copia.adicionarAresta(u, v);
                if (!CicloCaminhoHamiltoniano.eHamiltoniano(copia)) {
                    falhas.add(new String[]{u, v});
                }
            }
        }
        return falhas;
    }

    public static boolean eNaoHamiltonianoMaximal(Grafo g) {
        List<String[]> falhas = paresQueFalham(g);
        return falhas != null && falhas.isEmpty();
    }

    private static void relatorio(String nome, Grafo g) {
        System.out.println("--- " + nome + " ---");
        g.imprimir();

        List<String[]> falhas = paresQueFalham(g);
        if (falhas == null) {
            System.out.println("G ja e Hamiltoniano - a pergunta nao se aplica.");
        } else if (falhas.isEmpty()) {
            System.out.println("G e NAO HAMILTONIANO MAXIMAL "
                    + "(toda aresta que falta, se adicionada, cria um grafo Hamiltoniano).");
        } else {
            System.out.print("G NAO e maximal - pares cuja adicao NAO ajuda: ");
            for (String[] par : falhas) {
                System.out.print("(" + par[0] + "," + par[1] + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // G1 dos slides 24-26: triangulo V1V2V3 + pendente V4 (ligado so a V3).
        // NAO e hamiltoniano (V4 tem grau 1). As 2 arestas que faltam sao
        // V1-V4 e V2-V4; o slide mostra que AMBAS, isoladamente, criam um
        // ciclo Hamiltoniano -> G1 e nao hamiltoniano maximal.
        Grafo g1 = new Grafo();
        g1.adicionarAresta("V1", "V2");
        g1.adicionarAresta("V1", "V3");
        g1.adicionarAresta("V2", "V3");
        g1.adicionarAresta("V3", "V4");
        relatorio("G1 (slides 24-26): triangulo + pendente", g1);

        // Contraexemplo: caminho V1-V2-V3-V4-V5 com uma corda V1-V3.
        // V5 continua com grau 1 (so liga a V4), entao NENHUMA aresta que
        // nao envolva V5 resolve o problema -> NAO e maximal.
        Grafo semExemplo = new Grafo();
        semExemplo.adicionarAresta("V1", "V2");
        semExemplo.adicionarAresta("V2", "V3");
        semExemplo.adicionarAresta("V3", "V4");
        semExemplo.adicionarAresta("V4", "V5");
        semExemplo.adicionarAresta("V1", "V3");
        relatorio("Contraexemplo: caminho com uma corda (V5 preso em grau 1)", semExemplo);
    }
}
