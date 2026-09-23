/* Unidade 18 - Grafos Eulerianos
 *
 * Roda o Teorema de Euler sobre grafos "de prova" - as Questoes 2 e 15 do
 * Aula18/Simulado_03_Resolucao.md - conferindo os resultados que ja
 * tinham sido resolvidos na mao.
 */

public class VerificaEuleriano {

    // Questao 2 do Simulado_03: 11 vertices, 15 arestas.
    static Grafo grafoQuestao2() {
        Grafo g = new Grafo();
        g.adicionarAresta("e1", "v1", "v3");
        g.adicionarAresta("e2", "v1", "v2");
        g.adicionarAresta("e3", "v2", "v3");
        g.adicionarAresta("e4", "v3", "v4");
        g.adicionarAresta("e5", "v4", "v5");
        g.adicionarAresta("e6", "v5", "v6");
        g.adicionarAresta("e7", "v5", "v8");
        g.adicionarAresta("e8", "v6", "v8");
        g.adicionarAresta("e9", "v7", "v8");
        g.adicionarAresta("e10", "v7", "v10");
        g.adicionarAresta("e11", "v6", "v9");
        g.adicionarAresta("e12", "v9", "v10");
        g.adicionarAresta("e13", "v9", "v11");
        g.adicionarAresta("e14", "v6", "v11");
        g.adicionarAresta("e15", "v8", "v9");
        return g;
    }

    // Questao 15 do Simulado_03: 6 vertices, 8 arestas.
    static Grafo grafoQuestao15() {
        Grafo g = new Grafo();
        g.adicionarAresta("e1", "v1", "v2");
        g.adicionarAresta("e2", "v1", "v4");
        g.adicionarAresta("e3", "v2", "v4");
        g.adicionarAresta("e4", "v2", "v3");
        g.adicionarAresta("e5", "v4", "v5");
        g.adicionarAresta("e6", "v3", "v5");
        g.adicionarAresta("e7", "v3", "v6");
        g.adicionarAresta("e8", "v5", "v6");
        return g;
    }

    static void relatorio(String nome, Grafo g, String esperado) {
        System.out.println("=== " + nome + " ===");
        System.out.println("arestas = " + g.numeroDeArestas() + ", soma dos graus = " + g.somaDosGraus());
        System.out.println("vertices de grau impar = " + g.verticesGrauImpar()
                + " (" + g.verticesGrauImpar().size() + ")");
        System.out.println("e conexo? = " + g.eConexo());
        System.out.println("classificacao = " + GrafoEuleriano.classificar(g));
        System.out.println("esperado      = " + esperado);
        System.out.println();
    }

    public static void main(String[] args) {
        relatorio("Questao 2 (Simulado_03)", grafoQuestao2(),
                "SEMI-EULERIANO (impares: v3 e v5)");
        relatorio("Questao 15 (Simulado_03)", grafoQuestao15(),
                "NAO euleriano, nem semi (4 vertices de grau impar: v2,v3,v4,v5)");
    }
}
