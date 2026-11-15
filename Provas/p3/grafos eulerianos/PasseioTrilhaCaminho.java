/* Unidade 18 - Grafos Eulerianos
 *
 * Classifica uma sequencia  v0 e1 v1 e2 v2 ... vk-1 ek vk  como:
 *   - PASSEIO (sempre, se a sequencia for valida no grafo);
 *   - TRILHA, se nenhuma aresta se repete;
 *   - CAMINHO, se nenhum vertice se repete (exceto talvez v0 = vk);
 *   - aberto (v0 != vk) ou fechado (v0 == vk);
 *   - CIRCUITO = trilha fechada;
 *   - CICLO = caminho fechado.
 *
 * Testado com os passeios W1..W4 usados nos slides (paginas 10, 11, 15, 22),
 * sobre o grafo classico de Grafo.java (V={v1..v5}, E={e1..e10}).
 */

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class PasseioTrilhaCaminho {

    public static class Resultado {
        int comprimento;
        boolean fechado;
        boolean trilha;
        boolean caminho;
        boolean circuito;
        boolean ciclo;

        @Override
        public String toString() {
            return "comprimento=" + comprimento
                    + ", " + (fechado ? "FECHADO" : "ABERTO")
                    + ", trilha=" + trilha
                    + ", caminho=" + caminho
                    + ", circuito=" + circuito
                    + ", ciclo=" + ciclo;
        }
    }

    // tokens alternam vertice, aresta, vertice, aresta, ..., vertice.
    // Ex.: "v1","e1","v2","e5","v3"  ->  passeio v1 -e1-> v2 -e5-> v3
    public static Resultado classificar(Grafo g, String... tokens) {
        if (tokens.length % 2 == 0 || tokens.length < 1) {
            throw new RuntimeException("Sequencia invalida: precisa ser vertice,aresta,vertice,...,vertice");
        }

        int k = (tokens.length - 1) / 2; // numero de arestas
        List<String> idsArestas = new ArrayList<>();
        List<String> seqVertices = new ArrayList<>();
        seqVertices.add(tokens[0]);

        for (int i = 0; i < k; i++) {
            String vAntes = tokens[2 * i];
            String idAresta = tokens[2 * i + 1];
            String vDepois = tokens[2 * i + 2];

            Grafo.Aresta aresta = g.aresta(idAresta);
            boolean ok = (aresta.a.equals(vAntes) && aresta.b.equals(vDepois))
                    || (aresta.a.equals(vDepois) && aresta.b.equals(vAntes));
            if (!ok) {
                throw new RuntimeException("Aresta " + idAresta + " nao liga "
                        + vAntes + " a " + vDepois);
            }

            idsArestas.add(idAresta);
            seqVertices.add(vDepois);
        }

        Resultado r = new Resultado();
        r.comprimento = k;
        r.fechado = seqVertices.get(0).equals(seqVertices.get(seqVertices.size() - 1));

        Set<String> arestasVistas = new LinkedHashSet<>(idsArestas);
        r.trilha = (arestasVistas.size() == idsArestas.size());

        // Caminho: vertices distintos, exceto o par (primeiro, ultimo) se fechado.
        List<String> paraChecar = new ArrayList<>(seqVertices);
        if (r.fechado) {
            paraChecar.remove(paraChecar.size() - 1); // ignora a repeticao v0=vk
        }
        Set<String> verticesVistos = new LinkedHashSet<>(paraChecar);
        r.caminho = r.trilha && (verticesVistos.size() == paraChecar.size());

        r.circuito = r.trilha && r.fechado;
        r.ciclo = r.caminho && r.fechado;

        return r;
    }

    private static Grafo grafoClassico() {
        Grafo g = new Grafo();
        g.adicionarAresta("e1", "v1", "v2");
        g.adicionarAresta("e2", "v1", "v2");
        g.adicionarAresta("e3", "v2", "v5");
        g.adicionarAresta("e4", "v2", "v4");
        g.adicionarAresta("e5", "v2", "v3");
        g.adicionarAresta("e6", "v1", "v5");
        g.adicionarAresta("e7", "v5", "v3");
        g.adicionarAresta("e8", "v4", "v3");
        g.adicionarAresta("e9", "v1", "v3");
        g.adicionarAresta("e10", "v3", "v3");
        return g;
    }

    public static void main(String[] args) {
        Grafo g = grafoClassico();

        System.out.println("--- W1 (slide 10): v1 e1 v2 e5 v3 e10 v3 e5 v2 e3 v5 ---");
        Resultado w1 = classificar(g, "v1", "e1", "v2", "e5", "v3", "e10", "v3", "e5", "v2", "e3", "v5");
        System.out.println(w1);
        System.out.println("Esperado: comprimento=5, ABERTO, trilha=false (e5 repetida), caminho=false");

        System.out.println("\n--- W2 (slide 11): v1 e1 v2 e1 v1 e1 v2 ---");
        Resultado w2 = classificar(g, "v1", "e1", "v2", "e1", "v1", "e1", "v2");
        System.out.println(w2);
        System.out.println("Esperado: comprimento=3, ABERTO, trilha=false (e1 repetida 3x), caminho=false");

        System.out.println("\n--- W3 (slide 15): v1 e6 v5 e3 v2 e4 v4 e8 v3 e9 v1 ---");
        Resultado w3 = classificar(g, "v1", "e6", "v5", "e3", "v2", "e4", "v4", "e8", "v3", "e9", "v1");
        System.out.println(w3);
        System.out.println("Esperado: comprimento=5, FECHADO, trilha=true => CIRCUITO");

        System.out.println("\n--- W4 (slide 22): v2 e4 v4 e8 v3 e7 v5 e6 v1 ---");
        Resultado w4 = classificar(g, "v2", "e4", "v4", "e8", "v3", "e7", "v5", "e6", "v1");
        System.out.println(w4);
        System.out.println("Esperado: comprimento=4, ABERTO, trilha=true, caminho=true (vertices nao repetem)");
    }
}
