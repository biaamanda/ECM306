/* Unidade 19 - Grafos Hamiltonianos
 *
 * Grafo SIMPLES nao direcionado (sem laco, sem aresta paralela) - e o que
 * Dirac, Ore e o Fechamento exigem. Representado por uma lista de pares
 * de vertices (arestas nao rotuladas, ja que aqui nao precisamos escrever
 * passeios "v0 e1 v1 ...", so testar adjacencia).
 */

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Grafo {

    private static class Par {
        final String a, b;
        Par(String a, String b) { this.a = a; this.b = b; }

        boolean tem(String v) { return a.equals(v) || b.equals(v); }

        boolean liga(String u, String v) {
            return (a.equals(u) && b.equals(v)) || (a.equals(v) && b.equals(u));
        }
    }

    private final List<String> vertices = new ArrayList<>();
    private final List<Par> arestas = new ArrayList<>();

    public void adicionarVertice(String v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
        }
    }

    public void adicionarAresta(String a, String b) {
        if (a.equals(b)) {
            throw new RuntimeException("Grafo simples nao aceita laco: " + a);
        }
        if (eAdjacente(a, b)) {
            return; // ja existe - grafo simples nao aceita paralela
        }
        adicionarVertice(a);
        adicionarVertice(b);
        arestas.add(new Par(a, b));
    }

    public List<String> getVertices() {
        return vertices;
    }

    public int numeroDeVertices() {
        return vertices.size();
    }

    public int numeroDeArestas() {
        return arestas.size();
    }

    public int grau(String v) {
        int g = 0;
        for (Par p : arestas) {
            if (p.tem(v)) g++;
        }
        return g;
    }

    public boolean eAdjacente(String u, String v) {
        for (Par p : arestas) {
            if (p.liga(u, v)) return true;
        }
        return false;
    }

    public Set<String> vizinhanca(String v) {
        Set<String> n = new LinkedHashSet<>();
        for (Par p : arestas) {
            if (p.a.equals(v)) n.add(p.b);
            else if (p.b.equals(v)) n.add(p.a);
        }
        return n;
    }

    public boolean eCompleto() {
        int n = numeroDeVertices();
        return numeroDeArestas() == (long) n * (n - 1) / 2;
    }

    // Copia profunda (usada para testar "e se eu adicionar esta aresta?").
    public Grafo copiar() {
        Grafo copia = new Grafo();
        for (String v : vertices) copia.adicionarVertice(v);
        for (Par p : arestas) copia.adicionarAresta(p.a, p.b);
        return copia;
    }

    public void imprimir() {
        System.out.println("Vertices (" + numeroDeVertices() + "): " + vertices);
        System.out.print("Arestas  (" + numeroDeArestas() + "): ");
        for (Par p : arestas) System.out.print("(" + p.a + "," + p.b + ") ");
        System.out.println();
        for (String v : vertices) {
            System.out.println("  d(" + v + ") = " + grau(v));
        }
    }
}
