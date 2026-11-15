/* Unidade 19 - Grafos Hamiltonianos
 *
 * PROBLEMA DO CAIXEIRO VIAJANTE (TSP) - slides 67-71.
 *
 * Dado um grafo completo com pesos (distancias) nas arestas, achar o
 * CICLO HAMILTONIANO de peso minimo (rota que visita cada cidade uma vez
 * e volta ao inicio, gastando o menos possivel).
 *
 * NAO existe algoritmo polinomial conhecido para isso (e NP-completo).
 * Esta solucao e por FORCA BRUTA: O((n-1)!) - so serve para poucas
 * cidades (ate uns 9-10 no maximo, em tempo razoavel). Na pratica usa-se
 * HEURISTICAS (vizinho mais proximo, 2-opt, algoritmos geneticos, etc.),
 * que acham boas rotas rapido, mas sem garantia de otimalidade.
 */

import java.util.ArrayList;
import java.util.List;

public class CaixeiroViajante {

    private final String[] cidades;
    private final int[][] distancias; // matriz simetrica de distancias

    public CaixeiroViajante(String[] cidades, int[][] distancias) {
        this.cidades = cidades;
        this.distancias = distancias;
    }

    // Forca bruta: fixa a cidade 0 como origem (rotacoes do mesmo ciclo
    // sao a mesma rota) e testa todas as permutacoes das demais.
    public int[] resolver() {
        int n = cidades.length;
        List<Integer> restantes = new ArrayList<>();
        for (int i = 1; i < n; i++) restantes.add(i);

        int[] melhorRota = null;
        int melhorCusto = Integer.MAX_VALUE;

        List<List<Integer>> todas = permutacoes(restantes);
        for (List<Integer> permutacao : todas) {
            int[] rota = new int[n];
            rota[0] = 0;
            for (int i = 0; i < permutacao.size(); i++) {
                rota[i + 1] = permutacao.get(i);
            }
            int custo = custoDaRota(rota);
            if (custo < melhorCusto) {
                melhorCusto = custo;
                melhorRota = rota;
            }
        }

        this.ultimoCusto = melhorCusto;
        return melhorRota;
    }

    private int ultimoCusto;

    public int custoDaRota(int[] rota) {
        int custo = 0;
        for (int i = 0; i < rota.length; i++) {
            int de = rota[i];
            int para = rota[(i + 1) % rota.length]; // volta pro inicio no final
            custo += distancias[de][para];
        }
        return custo;
    }

    public String rotaComoTexto(int[] rota) {
        StringBuilder sb = new StringBuilder();
        for (int cidade : rota) {
            sb.append(cidades[cidade]).append(" -> ");
        }
        sb.append(cidades[rota[0]]); // fecha o ciclo
        return sb.toString();
    }

    private static List<List<Integer>> permutacoes(List<Integer> itens) {
        List<List<Integer>> resultado = new ArrayList<>();
        permutar(itens, 0, resultado);
        return resultado;
    }

    private static void permutar(List<Integer> itens, int k, List<List<Integer>> resultado) {
        if (k == itens.size()) {
            resultado.add(new ArrayList<>(itens));
            return;
        }
        for (int i = k; i < itens.size(); i++) {
            java.util.Collections.swap(itens, k, i);
            permutar(itens, k + 1, resultado);
            java.util.Collections.swap(itens, k, i);
        }
    }

    public static void main(String[] args) {
        // Exemplo fictício: 5 cidades e suas distancias (matriz simetrica, km).
        String[] cidades = {"A", "B", "C", "D", "E"};
        int[][] distancias = {
                //      A    B    C    D    E
                /*A*/ {  0,  12,  10,  19,   8},
                /*B*/ { 12,   0,   3,   7,   6},
                /*C*/ { 10,   3,   0,   2,   9},
                /*D*/ { 19,   7,   2,   0,  11},
                /*E*/ {  8,   6,   9,  11,   0},
        };

        CaixeiroViajante tsp = new CaixeiroViajante(cidades, distancias);

        System.out.println("Cidades: " + java.util.Arrays.toString(cidades));
        System.out.println("Testando " + fatorial(cidades.length - 1) + " rotas possiveis (forca bruta)...\n");

        int[] melhor = tsp.resolver();

        System.out.println("Melhor rota encontrada: " + tsp.rotaComoTexto(melhor));
        System.out.println("Custo total: " + tsp.ultimoCusto);
    }

    private static long fatorial(int n) {
        long f = 1;
        for (int i = 2; i <= n; i++) f *= i;
        return f;
    }
}
