/* Unidade 18 - Grafos Eulerianos
 *
 * O PROBLEMA DAS PONTES DE KONIGSBERG (slides 1, 59-62) - a origem
 * historica da Teoria dos Grafos (Euler, 1736).
 *
 * A cidade tinha duas ilhas e as margens do rio Pregel, ligadas por 7
 * pontes. Pergunta: da para passear pela cidade cruzando CADA ponte
 * exatamente uma vez?
 *
 * Modelagem (slide 60-61): 4 regioes viram vertices - A (ilha grande),
 * B (margem sul), C (margem norte), D (ilha pequena) - e cada ponte vira
 * uma aresta:
 *   a: A-B      c: A-C      e: A-D
 *   b: A-B      d: A-C      f: B-D
 *                            g: C-D
 */

public class PontesDeKonigsberg {

    public static void main(String[] args) {
        Grafo g = new Grafo();
        g.adicionarAresta("a", "A", "B");
        g.adicionarAresta("b", "A", "B"); // paralela com a
        g.adicionarAresta("c", "A", "C");
        g.adicionarAresta("d", "A", "C"); // paralela com c
        g.adicionarAresta("e", "A", "D");
        g.adicionarAresta("f", "B", "D");
        g.adicionarAresta("g", "C", "D");

        System.out.println("Grafo das pontes de Konigsberg:");
        g.imprimir();

        System.out.println("\nvertices de grau impar = " + g.verticesGrauImpar());
        System.out.println("classificacao = " + GrafoEuleriano.classificar(g));

        System.out.println("\nConclusao de Euler: como ha " + g.verticesGrauImpar().size()
                + " vertices de grau impar (mais que 2), NAO existe passeio - nem aberto,");
        System.out.println("nem fechado - que cruze cada uma das 7 pontes exatamente uma vez.");
        System.out.println("Foi assim que Euler, em 1736, resolveu o problema PROVANDO que a");
        System.out.println("resposta e negativa - sem precisar testar rota por rota.");
    }
}
