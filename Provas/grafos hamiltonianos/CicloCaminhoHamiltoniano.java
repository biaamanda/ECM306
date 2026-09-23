/* Unidade 19 - Grafos Hamiltonianos
 *
 * Busca por FORCA BRUTA (permutacoes dos vertices) de um CAMINHO ou CICLO
 * Hamiltoniano. So viavel para grafos pequenos (O(n!)), mas e exatamente
 * o tipo de grafo que cai em prova (4 a 8 vertices).
 *
 * Testado com os grafos G1..G5 dos "Exemplos 1 a 5" dos slides.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CicloCaminhoHamiltoniano {

    // Retorna a sequencia de vertices de um caminho Hamiltoniano, ou null se nao existir.
    public static List<String> buscarCaminho(Grafo g) {
        List<String> vertices = new ArrayList<>(g.getVertices());
        return permutar(vertices, 0, g, false);
    }

    // Retorna a sequencia de vertices de um ciclo Hamiltoniano (repete o
    // primeiro no fim, para deixar claro que fecha), ou null se nao existir.
    public static List<String> buscarCiclo(Grafo g) {
        List<String> vertices = new ArrayList<>(g.getVertices());
        List<String> achado = permutar(vertices, 0, g, true);
        if (achado == null) return null;
        List<String> comFechamento = new ArrayList<>(achado);
        comFechamento.add(achado.get(0));
        return comFechamento;
    }

    public static boolean eHamiltoniano(Grafo g) {
        return buscarCiclo(g) != null;
    }

    // Backtracking classico: fixa vertices[0..k-1], tenta cada vertice
    // restante na posicao k, verifica no final se a sequencia e valida.
    private static List<String> permutar(List<String> v, int k, Grafo g, boolean fechado) {
        int n = v.size();
        if (n == 0) return new ArrayList<>();

        if (k == n) {
            return valido(v, g, fechado) ? new ArrayList<>(v) : null;
        }
        for (int i = k; i < n; i++) {
            Collections.swap(v, k, i);
            List<String> resultado = permutar(v, k + 1, g, fechado);
            if (resultado != null) {
                return resultado;
            }
            Collections.swap(v, k, i); // desfaz para tentar a proxima opcao
        }
        return null;
    }

    private static boolean valido(List<String> ordem, Grafo g, boolean fechado) {
        for (int i = 0; i + 1 < ordem.size(); i++) {
            if (!g.eAdjacente(ordem.get(i), ordem.get(i + 1))) {
                return false;
            }
        }
        if (fechado && ordem.size() > 1) {
            return g.eAdjacente(ordem.get(ordem.size() - 1), ordem.get(0));
        }
        return true;
    }

    // ------------------------------------------------------------------
    // Testes - Exemplos 1 a 5 dos slides.
    // ------------------------------------------------------------------
    static Grafo grafoG1() { // slides 5-6: NAO tem caminho Hamiltoniano
        Grafo g = new Grafo();
        g.adicionarAresta("V1", "V2");
        g.adicionarAresta("V2", "V3");
        g.adicionarAresta("V2", "V4");
        return g;
    }

    static Grafo grafoG2() { // slides 7-11: tem caminho (V4 V1 V2 V3), nao tem ciclo
        Grafo g = new Grafo();
        g.adicionarAresta("V1", "V4");
        g.adicionarAresta("V1", "V2");
        g.adicionarAresta("V2", "V3");
        g.adicionarAresta("V2", "V4");
        return g;
    }

    static Grafo grafoG3() { // slides 12-15: tem caminho (V1 V2 V5 V4 V3), nao tem ciclo
        Grafo g = new Grafo();
        g.adicionarAresta("V1", "V4");
        g.adicionarAresta("V1", "V2");
        g.adicionarAresta("V2", "V3");
        g.adicionarAresta("V3", "V4");
        g.adicionarAresta("V2", "V5");
        g.adicionarAresta("V5", "V4");
        return g;
    }

    static Grafo grafoG4() { // slides 16-17: tem ciclo Hamiltoniano (V1 V2 V3 V4 V1)
        Grafo g = new Grafo();
        g.adicionarAresta("V1", "V4");
        g.adicionarAresta("V1", "V2");
        g.adicionarAresta("V2", "V3");
        g.adicionarAresta("V3", "V4");
        g.adicionarAresta("V2", "V4");
        return g;
    }

    static Grafo grafoG5() { // slides 18-19: "roda" - tem ciclo (V1 V5 V2 V3 V4 V1)
        Grafo g = new Grafo();
        g.adicionarAresta("V1", "V4");
        g.adicionarAresta("V1", "V2");
        g.adicionarAresta("V2", "V3");
        g.adicionarAresta("V3", "V4");
        g.adicionarAresta("V1", "V5");
        g.adicionarAresta("V2", "V5");
        g.adicionarAresta("V3", "V5");
        g.adicionarAresta("V4", "V5");
        return g;
    }

    private static void testar(String nome, Grafo g, String esperado) {
        List<String> caminho = buscarCaminho(g);
        List<String> ciclo = buscarCiclo(g);
        System.out.println("--- " + nome + " ---");
        System.out.println("caminho hamiltoniano: " + (caminho == null ? "NAO existe" : caminho));
        System.out.println("ciclo hamiltoniano:   " + (ciclo == null ? "NAO existe" : ciclo));
        System.out.println("esperado: " + esperado);
        System.out.println();
    }

    public static void main(String[] args) {
        testar("G1 (slide 6)", grafoG1(), "sem caminho, sem ciclo");
        testar("G2 (slide 8-9)", grafoG2(), "tem caminho (ex.: V4 V1 V2 V3), sem ciclo");
        testar("G3 (slide 13-14)", grafoG3(), "tem caminho (ex.: V1 V2 V5 V4 V3), sem ciclo");
        testar("G4 (slide 17)", grafoG4(), "tem ciclo (ex.: V1 V2 V3 V4 V1)");
        testar("G5 (slide 19)", grafoG5(), "tem ciclo (ex.: V1 V5 V2 V3 V4 V1)");
    }
}
