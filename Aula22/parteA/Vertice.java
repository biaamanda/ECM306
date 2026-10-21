import java.util.ArrayList;

public class Vertice {

    String dado;
    ArrayList<Aresta> listaArestas;

    // 4. Construtores
    public Vertice(String dado, ArrayList<Aresta> listaArestas) {
        this.dado = dado;
        this.listaArestas = listaArestas;
    }

    public Vertice(String dado) {
        this.dado = dado;
        this.listaArestas = null;
    }

    // 5. Métodos
    public void addAresta(Aresta a) {
        // vértice sem arestas tem listaArestas == null: cria a lista na primeira inclusão
        if (listaArestas == null)
            listaArestas = new ArrayList<Aresta>();
        listaArestas.add(a);
    }

    public boolean removeAresta(Aresta a) {
        if (listaArestas == null)
            return false;
        boolean removida = listaArestas.remove(a);
        // mantém a convenção: vértice sem arestas fica com listaArestas == null
        if (listaArestas.isEmpty())
            listaArestas = null;
        return removida;
    }
}
