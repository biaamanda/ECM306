public class Grafo {

    int numVertices;
    boolean[][] adjMatrix;

    // 3. Construtor
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];   // inicia tudo com false (sem arestas)
    }

    // 4. Adiciona aresta entre i e j
    public void addAresta(int i, int j) {
        // grafo não-direcionado: a matriz é simétrica
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    // 5. Remove aresta entre i e j
    public void removeAresta(int i, int j) {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }

    // 6. Retorna true se existe aresta entre i e j
    public boolean ehAresta(int i, int j) {
        return adjMatrix[i][j];
    }

    // 7. Retorna a matriz de adjacência como String
    /* Exemplo de saída:
       0: 0 1 1 1
       1: 1 0 1 0
       2: 1 1 0 0
       3: 1 0 0 0
     */
    public String retornaGrafo() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            s.append(i + ":");
            for (boolean j : adjMatrix[i]) {
                s.append(" " + (j ? 1 : 0));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
