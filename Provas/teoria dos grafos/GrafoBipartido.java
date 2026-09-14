/* Unidade 17 - Introducao a Teoria dos Grafos
 *
 * GRAFO BIPARTIDO: V pode ser particionado em X e Y (nao vazios,
 * disjuntos) tal que toda aresta liga um vertice de X a um de Y (nunca
 * dois vertices do mesmo lado).
 *
 * Os slides so dao a definicao e exemplos prontos (Figuras 3.22/3.23); o
 * teste abaixo (coloracao com 2 cores via BFS) e o metodo padrao para
 * decidir se um grafo QUALQUER e bipartido, e tambem entrega a
 * biparticao {X,Y} quando ela existe.
 *
 * GRAFO BIPARTIDO COMPLETO Km,n: todo vertice de X (|X|=m) ligado a todo
 * vertice de Y (|Y|=n)  ->  m*n arestas; Kn,n e n-regular.
 */

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class GrafoBipartido {

    // Tenta colorir o grafo com 2 cores (0/1) tal que vertices adjacentes
    // tenham cores diferentes. Retorna o mapa de cores se conseguir, ou
    // null se o grafo NAO for bipartido (achou aresta dentro do mesmo lado).
    public static Map<String, Integer> tentarBipartir(Grafo g) {
        Map<String, Integer> cor = new HashMap<>();

        for (String inicio : g.getVertices()) {
            if (cor.containsKey(inicio)) continue; // ja visitado (outro componente)

            cor.put(inicio, 0);
            Queue<String> fila = new ArrayDeque<>();
            fila.add(inicio);

            while (!fila.isEmpty()) {
                String atual = fila.poll();
                for (String vizinho : g.vizinhanca(atual)) {
                    if (vizinho.equals(atual)) {
                        return null; // laco -> nunca e bipartido
                    }
                    if (!cor.containsKey(vizinho)) {
                        cor.put(vizinho, 1 - cor.get(atual));
                        fila.add(vizinho);
                    } else if (cor.get(vizinho).equals(cor.get(atual))) {
                        return null; // aresta dentro do mesmo lado -> nao bipartido
                    }
                }
            }
        }
        return cor;
    }

    public static boolean eBipartido(Grafo g) {
        return tentarBipartir(g) != null;
    }

    // Gera o grafo bipartido completo Km,n: X={x1..xm}, Y={y1..yn}.
    public static Grafo gerarCompletoBipartido(int m, int n) {
        Grafo g = new Grafo();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                g.adicionarAresta("x" + i, "y" + j);
            }
        }
        return g;
    }

    public static void main(String[] args) {
        System.out.println("--- Figura 3.22: triangulo (NAO bipartido) ---");
        Grafo triangulo = new Grafo();
        triangulo.adicionarAresta("a", "b");
        triangulo.adicionarAresta("b", "c");
        triangulo.adicionarAresta("c", "a");
        System.out.println("e bipartido? " + eBipartido(triangulo) + "  (esperado false)");

        System.out.println("\n--- Figura 3.23 (I): caminho x1-y1-x2-y2-x3-y3, x4 solto ---");
        Grafo fig23a = new Grafo();
        fig23a.adicionarAresta("x1", "y1");
        fig23a.adicionarAresta("y1", "x2");
        fig23a.adicionarAresta("x2", "y2");
        fig23a.adicionarAresta("y2", "x3");
        fig23a.adicionarAresta("x3", "y3");
        fig23a.adicionarVertice("x4");
        Map<String, Integer> particao = tentarBipartir(fig23a);
        System.out.println("e bipartido? " + (particao != null) + "  (esperado true)");
        System.out.println("particao (0=X, 1=Y): " + particao);

        System.out.println("\n--- Km,n: numero de arestas = m*n ---");
        for (int[] mn : new int[][]{{1, 1}, {2, 3}, {3, 3}, {3, 4}}) {
            Grafo kmn = gerarCompletoBipartido(mn[0], mn[1]);
            System.out.println("K" + mn[0] + "," + mn[1] + " -> arestas = "
                    + kmn.numeroDeArestas() + " (esperado " + (mn[0] * mn[1]) + ")"
                    + ", e bipartido = " + eBipartido(kmn));
        }

        System.out.println("\n--- K3,3: e 3-regular (m == n) ---");
        Grafo k33 = gerarCompletoBipartido(3, 3);
        System.out.println("grauSeRegular(K3,3) = " + k33.grauSeRegular() + " (esperado 3)");
    }
}
