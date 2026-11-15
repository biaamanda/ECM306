/* Unidade 17 - Introducao a Teoria dos Grafos
 *
 * Representacao simples de um grafo NAO DIRECIONADO, que aceita LACOS e
 * ARESTAS PARALELAS (ou seja, um multigrafo) - exatamente o que os slides
 * usam nos exemplos (Grafo - Exemplo, Grafos - Definicoes).
 *
 * Guarda so uma lista de vertices (String) e uma lista de arestas (pares
 * nao ordenados de vertices). Nao ha necessidade de matriz/lista de
 * adjacencia para os exercicios desta unidade - eles pedem contagens
 * (grau, arestas, sequencia de graus), nao percursos.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Grafo {

    // Uma aresta e so um par nao ordenado {a, b}. Se a.equals(b), e um LACO.
    public static class Aresta {
        final String a, b;

        public Aresta(String a, String b) {
            this.a = a;
            this.b = b;
        }

        boolean incide(String v) {
            return a.equals(v) || b.equals(v);
        }

        boolean eLaco() {
            return a.equals(b);
        }

        // Duas arestas representam o mesmo par de vertices (para detectar paralelas).
        boolean mesmoPar(Aresta outra) {
            return (a.equals(outra.a) && b.equals(outra.b))
                    || (a.equals(outra.b) && b.equals(outra.a));
        }

        @Override
        public String toString() {
            return "(" + a + "," + b + ")";
        }
    }

    private final List<String> vertices = new ArrayList<>();
    private final List<Aresta> arestas = new ArrayList<>();

    public void adicionarVertice(String v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
        }
    }

    // Cria os vertices automaticamente se ainda nao existirem.
    public void adicionarAresta(String u, String v) {
        adicionarVertice(u);
        adicionarVertice(v);
        arestas.add(new Aresta(u, v));
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

    // ------------------------------------------------------------------
    // Grau: conta cada incidencia; um laco incide duas vezes no mesmo v.
    // ------------------------------------------------------------------
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
        for (String v : vertices) {
            soma += grau(v);
        }
        return soma;
    }

    // Teorema 1: soma dos graus = 2 * numero de arestas.
    public boolean verificaTeorema1() {
        return somaDosGraus() == 2 * numeroDeArestas();
    }

    // Sequencia de graus em ordem crescente (com repeticao).
    public List<Integer> sequenciaDeGraus() {
        List<Integer> seq = new ArrayList<>();
        for (String v : vertices) {
            seq.add(grau(v));
        }
        Collections.sort(seq);
        return seq;
    }

    public List<String> verticesImpares() {
        List<String> impares = new ArrayList<>();
        for (String v : vertices) {
            if (grau(v) % 2 != 0) impares.add(v);
        }
        return impares;
    }

    public List<String> verticesPares() {
        List<String> pares = new ArrayList<>();
        for (String v : vertices) {
            if (grau(v) % 2 == 0) pares.add(v);
        }
        return pares;
    }

    // Teorema 2: a quantidade de vertices de grau impar e sempre par.
    public boolean verificaTeorema2() {
        return verticesImpares().size() % 2 == 0;
    }

    // ------------------------------------------------------------------
    // Grafo simples: sem lacos e sem arestas paralelas.
    // ------------------------------------------------------------------
    public boolean eSimples() {
        for (int i = 0; i < arestas.size(); i++) {
            if (arestas.get(i).eLaco()) return false;
            for (int j = i + 1; j < arestas.size(); j++) {
                if (arestas.get(i).mesmoPar(arestas.get(j))) return false;
            }
        }
        return true;
    }

    // Retorna o grau comum k se o grafo for k-regular, ou -1 caso contrario.
    // Grafo sem vertices e considerado nao regular (-1) por convencao.
    public int grauSeRegular() {
        if (vertices.isEmpty()) return -1;
        int k = grau(vertices.get(0));
        for (String v : vertices) {
            if (grau(v) != k) return -1;
        }
        return k;
    }

    // ------------------------------------------------------------------
    // Vizinhanca N(v): conjunto de vertices ligados a v por alguma aresta.
    // Um laco em v faz v ser "vizinho de si mesmo" (definicao estrita).
    // ------------------------------------------------------------------
    public Set<String> vizinhanca(String v) {
        Set<String> n = new LinkedHashSet<>();
        for (Aresta e : arestas) {
            if (e.a.equals(v)) n.add(e.b);
            else if (e.b.equals(v)) n.add(e.a);
        }
        return n;
    }

    public boolean saoAdjacentesVertices(String u, String v) {
        return vizinhanca(u).contains(v);
    }

    public boolean saoAdjacentesArestas(Aresta e1, Aresta e2) {
        return e1.a.equals(e2.a) || e1.a.equals(e2.b)
                || e1.b.equals(e2.a) || e1.b.equals(e2.b);
    }

    public void imprimir() {
        System.out.println("Vertices (" + numeroDeVertices() + "): " + vertices);
        System.out.println("Arestas  (" + numeroDeArestas() + "): " + arestas);
        for (String v : vertices) {
            System.out.println("  d(" + v + ") = " + grau(v));
        }
    }

    // ------------------------------------------------------------------
    // Teste - grafo do "Exercicio 1" dos slides (V={v1..v5}, e1..e8),
    // ja resolvido em Aula17/Grafos_Exercicios_Resolucao.md:
    //   e1(v1,v5) e2(v1,v2) e3(v1,v2) e4(v5,v4) e5(v2,v4) e6(v2,v3) e7(v4,v3) e8(v3,v3)-laco
    //   graus esperados: v1=3 v2=4 v3=4 v4=3 v5=2  (soma 16 = 2*8)
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        Grafo g = new Grafo();
        g.adicionarAresta("v1", "v5"); // e1
        g.adicionarAresta("v1", "v2"); // e2
        g.adicionarAresta("v1", "v2"); // e3 (paralela com e2)
        g.adicionarAresta("v5", "v4"); // e4
        g.adicionarAresta("v2", "v4"); // e5
        g.adicionarAresta("v2", "v3"); // e6
        g.adicionarAresta("v4", "v3"); // e7
        g.adicionarAresta("v3", "v3"); // e8 (laco)

        g.imprimir();

        System.out.println("\nsoma dos graus         = " + g.somaDosGraus());
        System.out.println("Teorema 1 (soma = 2m)?  = " + g.verificaTeorema1());
        System.out.println("sequencia de graus      = " + g.sequenciaDeGraus());
        System.out.println("vertices impares        = " + g.verticesImpares());
        System.out.println("Teorema 2 (#impares par)= " + g.verificaTeorema2());
        System.out.println("e simples?              = " + g.eSimples()
                + " (tem laco em v3 e paralela v1-v2)");
        System.out.println("e k-regular?            = " + g.grauSeRegular()
                + " (-1 = nao e regular)");
        System.out.println("N(v3)                   = " + g.vizinhanca("v3")
                + " (v3 aparece por causa do laco e8)");
        System.out.println("v1 e v2 sao adjacentes? = " + g.saoAdjacentesVertices("v1", "v2"));
        System.out.println("e2 e e3 sao adjacentes? = "
                + g.saoAdjacentesArestas(g.getArestas().get(1), g.getArestas().get(2)));
    }
}
