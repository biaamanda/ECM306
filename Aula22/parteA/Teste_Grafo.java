import java.util.ArrayList;

/*
 * Grafo:
 *   V1: Campinas          - São Paulo, Poços de Caldas
 *   V2: São Paulo         - Campinas, Campos do Jordão, São Lourenço, Poços de Caldas
 *   V3: Campos do Jordão  - São Paulo, São Lourenço
 *   V4: São Lourenço      - São Paulo, Campos do Jordão, Poços de Caldas
 *   V5: Poços de Caldas   - Campinas, São Paulo, São Lourenço
 */
public class Teste_Grafo {

    // Grafo não-direcionado: a aresta (u,v) aparece na lista de u e na lista de v
    static void conecta(Vertice u, Vertice v) {
        u.addAresta(new Aresta(u, v));
        v.addAresta(new Aresta(v, u));
    }

    static void titulo(String t) {
        System.out.println();
        System.out.println("===== " + t + " =====");
    }

    public static void main(String[] args) {

        Grafo g = new Grafo();

        Vertice V1 = new Vertice("Campinas");
        Vertice V2 = new Vertice("São Paulo");
        Vertice V3 = new Vertice("Campos do Jordão");
        Vertice V4 = new Vertice("São Lourenço");
        Vertice V5 = new Vertice("Poços de Caldas");

        titulo("Grafo vazio");
        g.imprimeVerticesDoGrafo();
        System.out.println("imprimeGrafo retornou: " + g.imprimeGrafo());

        // addVertice
        g.addVertice(V1);
        g.addVertice(V2);
        g.addVertice(V3);
        g.addVertice(V4);
        g.addVertice(V5);

        // Arestas (listas de adjacências)
        conecta(V1, V2);   // Campinas - São Paulo
        conecta(V1, V5);   // Campinas - Poços de Caldas
        conecta(V2, V3);   // São Paulo - Campos do Jordão
        conecta(V2, V4);   // São Paulo - São Lourenço
        conecta(V2, V5);   // São Paulo - Poços de Caldas
        conecta(V3, V4);   // Campos do Jordão - São Lourenço
        conecta(V4, V5);   // São Lourenço - Poços de Caldas

        titulo("Vértices do grafo");
        g.imprimeVerticesDoGrafo();

        titulo("Grafo completo");
        System.out.println("imprimeGrafo retornou: " + g.imprimeGrafo());

        titulo("Arestas de São Paulo");
        g.imprimeArestasDeVertice(V2);

        titulo("Graus dos vértices");
        g.imprimeGrauVertice(V1);
        g.imprimeGrauVertice(V2);
        g.imprimeGrauVertice(V3);
        g.imprimeGrauVertice(V4);
        g.imprimeGrauVertice(V5);
        System.out.println("retornaGrauVertice(São Paulo) = " + g.retornaGrauVertice(V2));

        // Vértice que não está no grafo
        Vertice V6 = new Vertice("Ubatuba");
        titulo("Vértice fora do grafo (Ubatuba)");
        g.imprimeArestasDeVertice(V6);
        g.imprimeGrauVertice(V6);

        // Vértice no grafo, mas sem arestas
        titulo("Vértice sem arestas (Ubatuba adicionado ao grafo)");
        g.addVertice(V6);
        g.imprimeArestasDeVertice(V6);
        System.out.println("retornaGrauVertice(Ubatuba) = " + g.retornaGrauVertice(V6));
        g.imprimeGrauVertice(V6);

        // Vertice.removeAresta: remove a aresta Campos do Jordão - São Lourenço
        titulo("Vertice.removeAresta (Campos do Jordão - São Lourenço)");
        Aresta cjParaSl = V3.listaArestas.get(1);   // [0] = CJ -> SP, [1] = CJ -> SL
        Aresta slParaCj = V4.listaArestas.get(1);   // [0] = SL -> SP, [1] = SL -> CJ
        System.out.println("Removendo CJ -> SL: " + V3.removeAresta(cjParaSl));
        System.out.println("Removendo SL -> CJ: " + V4.removeAresta(slParaCj));
        System.out.println("Removendo CJ -> SL de novo: " + V3.removeAresta(cjParaSl));
        g.imprimeGrauVertice(V3);
        g.imprimeGrauVertice(V4);
        conecta(V3, V4);   // recoloca a aresta

        // removeVertice
        titulo("Removendo São Paulo");
        System.out.println("removeVertice retornou: " + g.removeVertice(V2));
        g.imprimeGrafo();

        titulo("Removendo São Paulo de novo");
        System.out.println("removeVertice retornou: " + g.removeVertice(V2));

        // Construtores com parâmetros: Vertice(String, ArrayList) e Grafo(ArrayList)
        titulo("Construtores com parâmetros");
        ArrayList<Vertice> lista = new ArrayList<Vertice>();
        Vertice a = new Vertice("Santos", new ArrayList<Aresta>());
        Vertice b = new Vertice("Guarujá");
        lista.add(a);
        lista.add(b);
        Grafo g2 = new Grafo(lista);
        conecta(a, b);
        g2.imprimeGrafo();
    }
}
