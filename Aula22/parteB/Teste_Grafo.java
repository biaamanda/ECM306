/*
 * Grafo (Cormen):
 *       1 2 3 4 5
 *    1  0 1 0 0 1
 *    2  1 0 1 1 1
 *    3  0 1 0 1 0
 *    4  0 1 1 0 1
 *    5  1 1 0 1 0
 *
 * Como a matriz em Java começa no índice 0, o vértice k do Cormen
 * corresponde ao índice k - 1 (vértice 1 -> 0, ..., vértice 5 -> 4).
 */
public class Teste_Grafo {

    public static void main(String[] args) {

        // Exemplo do item 7 do enunciado (4 vértices)
        Grafo exemplo = new Grafo(4);
        exemplo.addAresta(0, 1);
        exemplo.addAresta(0, 2);
        exemplo.addAresta(0, 3);
        exemplo.addAresta(1, 2);
        System.out.println("Exemplo do enunciado:");
        System.out.print(exemplo.retornaGrafo());
        System.out.println();

        // Grafo do Cormen (5 vértices, 7 arestas)
        Grafo g = new Grafo(5);
        g.addAresta(0, 1);   // 1-2
        g.addAresta(0, 4);   // 1-5
        g.addAresta(1, 2);   // 2-3
        g.addAresta(1, 3);   // 2-4
        g.addAresta(1, 4);   // 2-5
        g.addAresta(2, 3);   // 3-4
        g.addAresta(3, 4);   // 4-5

        System.out.println("Grafo do Cormen:");
        System.out.print(g.retornaGrafo());
        System.out.println();

        // ehAresta
        System.out.println("ehAresta(0, 1) [1-2]: " + g.ehAresta(0, 1));   // true
        System.out.println("ehAresta(1, 0) [2-1]: " + g.ehAresta(1, 0));   // true (simétrica)
        System.out.println("ehAresta(0, 2) [1-3]: " + g.ehAresta(0, 2));   // false
        System.out.println("ehAresta(2, 3) [3-4]: " + g.ehAresta(2, 3));   // true
        System.out.println();

        // removeAresta
        System.out.println("Removendo a aresta 2-5 (indices 1 e 4)...");
        g.removeAresta(1, 4);
        System.out.println("ehAresta(1, 4) [2-5]: " + g.ehAresta(1, 4));   // false
        System.out.println("ehAresta(4, 1) [5-2]: " + g.ehAresta(4, 1));   // false
        System.out.print(g.retornaGrafo());
        System.out.println();

        // addAresta de volta
        System.out.println("Adicionando novamente a aresta 2-5...");
        g.addAresta(1, 4);
        System.out.println("ehAresta(1, 4) [2-5]: " + g.ehAresta(1, 4));   // true
        System.out.print(g.retornaGrafo());
    }
}
