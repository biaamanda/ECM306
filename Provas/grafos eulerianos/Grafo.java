/* Unidade 18 - Grafos Eulerianos
 *
 * Multigrafo NAO DIRECIONADO com ARESTAS ROTULADAS (e1, e2, ...), igual a
 * notacao usada em todos os slides desta unidade (passeios sao escritos
 * como v0 e1 v1 e2 v2 ...). Aceita lacos e arestas paralelas.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Grafo {

    public static class Aresta {
        public final String id, a, b;

        public Aresta(String id, String a, String b) {
            this.id = id;
            this.a = a;
            this.b = b;
        }

        public boolean incide(String v) {
            return a.equals(v) || b.equals(v);
        }

        public boolean eLaco() {
            return a.equals(b);
        }

        // Dado um extremo v, retorna o outro extremo da aresta.
        public String outraPonta(String v) {
            if (a.equals(v)) return b;
            if (b.equals(v)) return a;
            throw new RuntimeException("Aresta " + id + " nao incide em " + v);
        }

        @Override
        public String toString() {
            return id + "(" + a + "," + b + ")";
        }
    }

    private final List<String> vertices = new ArrayList<>();
    private final List<Aresta> arestas = new ArrayList<>();
    private final Map<String, Aresta> porId = new LinkedHashMap<>();

    public void adicionarVertice(String v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
        }
    }

    public void adicionarAresta(String id, String a, String b) {
        adicionarVertice(a);
        adicionarVertice(b);
        Aresta e = new Aresta(id, a, b);
        arestas.add(e);
        porId.put(id, e);
    }

    public Aresta aresta(String id) {
        Aresta e = porId.get(id);
        if (e == null) {
            throw new RuntimeException("Nao existe aresta com id " + id);
        }
        return e;
    }

    public List<String> getVertices() {
        return vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public int numeroDeVertices() {
        return vertices.size();
    }

    public int numeroDeArestas() {
        return arestas.size();
    }

    // Grau: laco conta duas vezes (as duas "pontas" caem no mesmo vertice).
    public int grau(String v) {
        int g = 0;
        for (Aresta e : arestas) {
            if (e.a.equals(v)) g++;
            if (e.b.equals(v)) g++;
        }
        return g;
    }

    public int somaDosGraus() {
        int soma = 0;
        for (String v : vertices) soma += grau(v);
        return soma;
    }

    public List<String> verticesGrauImpar() {
        List<String> impares = new ArrayList<>();
        for (String v : vertices) {
            if (grau(v) % 2 != 0) impares.add(v);
        }
        return impares;
    }

    public boolean eSimples() {
        for (int i = 0; i < arestas.size(); i++) {
            if (arestas.get(i).eLaco()) return false;
            for (int j = i + 1; j < arestas.size(); j++) {
                Aresta e1 = arestas.get(i), e2 = arestas.get(j);
                boolean mesmoPar = (e1.a.equals(e2.a) && e1.b.equals(e2.b))
                        || (e1.a.equals(e2.b) && e1.b.equals(e2.a));
                if (mesmoPar) return false;
            }
        }
        return true;
    }

    public Set<String> vizinhanca(String v) {
        Set<String> n = new LinkedHashSet<>();
        for (Aresta e : arestas) {
            if (e.a.equals(v)) n.add(e.b);
            else if (e.b.equals(v)) n.add(e.a);
        }
        return n;
    }

    // Conexo: existe um passeio entre QUALQUER par de vertices (BFS a
    // partir do primeiro vertice tem que alcancar todos os demais).
    public boolean eConexo() {
        if (vertices.isEmpty()) return true;

        Set<String> visitados = new LinkedHashSet<>();
        Queue<String> fila = new ArrayDeque<>();
        fila.add(vertices.get(0));
        visitados.add(vertices.get(0));

        while (!fila.isEmpty()) {
            String atual = fila.poll();
            for (String vizinho : vizinhanca(atual)) {
                if (visitados.add(vizinho)) {
                    fila.add(vizinho);
                }
            }
        }
        return visitados.size() == vertices.size();
    }

    public void imprimir() {
        System.out.println("Vertices (" + numeroDeVertices() + "): " + vertices);
        System.out.println("Arestas  (" + numeroDeArestas() + "): " + arestas);
        for (String v : vertices) {
            System.out.println("  d(" + v + ") = " + grau(v));
        }
    }

    // ------------------------------------------------------------------
    // Teste - o grafo classico de V={v1..v5}, E={e1..e10} reusado em
    // quase todos os slides desta unidade (paginas 9, 10, 14, 21, 25, 40).
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        Grafo g = new Grafo();
        g.adicionarAresta("e1", "v1", "v2");
        g.adicionarAresta("e2", "v1", "v2"); // paralela com e1
        g.adicionarAresta("e3", "v2", "v5");
        g.adicionarAresta("e4", "v2", "v4");
        g.adicionarAresta("e5", "v2", "v3");
        g.adicionarAresta("e6", "v1", "v5");
        g.adicionarAresta("e7", "v5", "v3");
        g.adicionarAresta("e8", "v4", "v3");
        g.adicionarAresta("e9", "v1", "v3");
        g.adicionarAresta("e10", "v3", "v3"); // laco

        g.imprimir();

        System.out.println("\ne conexo?      = " + g.eConexo());
        System.out.println("e simples?     = " + g.eSimples() + " (tem laco e2 paralela)");
        System.out.println("soma dos graus = " + g.somaDosGraus() + " (= 2 * "
                + g.numeroDeArestas() + " arestas)");
        System.out.println("vertices impares = " + g.verticesGrauImpar());
        System.out.println("N(v3) = " + g.vizinhanca("v3") + " (inclui v3 por causa do laco e10)");
    }
}
