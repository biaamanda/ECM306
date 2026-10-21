import java.util.ArrayList;

public class Grafo {

    ArrayList<Vertice> listaVertices;

    // 7. Construtores
    public Grafo(ArrayList<Vertice> listaVertices) {
        this.listaVertices = listaVertices;
    }

    public Grafo() {
        this.listaVertices = null;
    }

    // Auxiliar: verifica se o vértice pertence ao grafo
    private boolean existeVertice(Vertice v) {
        return listaVertices != null && listaVertices.contains(v);
    }

    // 8a. Adiciona um novo vértice ao grafo
    public void addVertice(Vertice v) {
        // grafo sem vértices tem listaVertices == null: cria a lista na primeira inclusão
        if (listaVertices == null)
            listaVertices = new ArrayList<Vertice>();
        listaVertices.add(v);
    }

    // 8b. Imprime as arestas associadas ao vértice
    public void imprimeArestasDeVertice(Vertice v) {
        if (!existeVertice(v)) {
            System.out.println("Vértice inexistente no Grafo...");
            return;
        }
        if (v.listaArestas == null || v.listaArestas.isEmpty()) {
            System.out.println("Vertice sem arestas");
            return;
        }
        for (Aresta a : v.listaArestas)
            System.out.println("   " + a.origem.dado + " -> " + a.destino.dado);
    }

    // 8c. Imprime o dado de cada vértice do grafo
    public void imprimeVerticesDoGrafo() {
        if (listaVertices == null || listaVertices.isEmpty()) {
            System.out.println("Grafo sem vértices...");
            return;
        }
        for (Vertice v : listaVertices)
            System.out.println("   " + v.dado);
    }

    // 8d. Retorna o grau do vértice (null se a lista de arestas for nula)
    public Integer retornaGrauVertice(Vertice v) {
        if (v.listaArestas == null)
            return null;
        return v.listaArestas.size();
    }

    // 8e. Imprime o grau do vértice
    public void imprimeGrauVertice(Vertice v) {
        if (!existeVertice(v)) {
            System.out.println("Vértice não existente no Grafo...");
            return;
        }
        Integer grau = retornaGrauVertice(v);
        System.out.println("Grau de " + v.dado + ": " + (grau == null ? 0 : grau));
    }

    // 8f. Remove o vértice (e as arestas associadas em outros vértices)
    public boolean removeVertice(Vertice v) {
        if (!existeVertice(v)) {
            System.out.println("Vértice inexistente do Grafo...");
            return false;
        }

        // remove, nos outros vértices, as arestas que chegam em v
        for (Vertice w : listaVertices) {
            if (w == v || w.listaArestas == null)
                continue;
            ArrayList<Aresta> incidentes = new ArrayList<Aresta>();
            for (Aresta a : w.listaArestas)
                if (a.origem == v || a.destino == v)
                    incidentes.add(a);
            for (Aresta a : incidentes)
                w.removeAresta(a);
        }

        listaVertices.remove(v);
        if (listaVertices.isEmpty())
            listaVertices = null;

        System.out.println("Vértice removido com sucesso");
        return true;
    }

    // 8g. Imprime todos os vértices e as arestas de cada vértice
    public boolean imprimeGrafo() {
        if (listaVertices == null || listaVertices.isEmpty()) {
            System.out.println("Grafo sem vértices...");
            return false;
        }
        for (Vertice v : listaVertices) {
            System.out.println(v.dado + ":");
            imprimeArestasDeVertice(v);
        }
        return true;
    }
}
