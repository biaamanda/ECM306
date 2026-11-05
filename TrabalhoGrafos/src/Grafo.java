import java.util.ArrayList;
import java.util.List;

/**
 * Grafo representado por LISTA DE ADJACÊNCIA.
 *
 * Os vértices são números de 0 a n-1. Cada vértice guarda a lista das
 * arestas que saem dele. Para uma imagem isso é muito mais econômico que
 * uma matriz de adjacência, porque cada pixel só tem 4 vizinhos.
 */
public class Grafo {
    private final List<Aresta>[] adjacencia;
    private int numArestas;

    @SuppressWarnings("unchecked")
    public Grafo(int numVertices) {
        adjacencia = new List[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjacencia[i] = new ArrayList<>(6); // 4 vizinhos + S + T
        }
    }

    public int numVertices() {
        return adjacencia.length;
    }

    /** Número de arestas guardadas (contando as reversas). */
    public int numArestas() {
        return numArestas;
    }

    public List<Aresta> vizinhos(int u) {
        return adjacencia[u];
    }

    /** Aresta direcionada u -> v com capacidade cap (a reversa começa com 0). */
    public void addAresta(int u, int v, int cap) {
        ligar(u, v, cap, 0);
    }

    /**
     * Aresta não direcionada entre u e v: as duas direções têm capacidade cap.
     * Usada entre pixels vizinhos (o "custo de separar" os dois vale nos dois sentidos).
     */
    public void addArestaDupla(int u, int v, int cap) {
        ligar(u, v, cap, cap);
    }

    private void ligar(int u, int v, int capIda, int capVolta) {
        Aresta ida = new Aresta(v, capIda);
        Aresta volta = new Aresta(u, capVolta);
        ida.reversa = volta;
        volta.reversa = ida;
        adjacencia[u].add(ida);
        adjacencia[v].add(volta);
        numArestas += 2;
    }
}
