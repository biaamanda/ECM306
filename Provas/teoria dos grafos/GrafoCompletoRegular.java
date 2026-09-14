/* Unidade 17 - Introducao a Teoria dos Grafos
 *
 * GRAFO COMPLETO Kn e GRAFO REGULAR.
 *
 * Kn: grafo simples com n vertices, com exatamente uma aresta entre CADA
 * par de vertices distintos.
 *   - numero de arestas: C(n,2) = n*(n-1)/2
 *   - Kn e (n-1)-regular (slide 65)
 *
 * Grafo k-regular: todo vertice tem o mesmo grau k.
 */

public class GrafoCompletoRegular {

    // Gera o grafo completo Kn com vertices rotulados "v1".."vn".
    public static Grafo gerarCompleto(int n) {
        Grafo g = new Grafo();
        for (int i = 1; i <= n; i++) {
            g.adicionarVertice("v" + i);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                g.adicionarAresta("v" + i, "v" + j);
            }
        }
        return g;
    }

    // Formula fechada: numero de arestas de Kn = n*(n-1)/2
    public static long arestasDeKn(int n) {
        return (long) n * (n - 1) / 2;
    }

    public static void main(String[] args) {
        System.out.println("--- Kn: arestas e regularidade (slide 65) ---");
        System.out.println("n | arestas (codigo) | formula n(n-1)/2 | grau (n-1)?");
        for (int n = 1; n <= 6; n++) {
            Grafo kn = gerarCompleto(n);
            System.out.printf("%d | %17d | %16d | grau = %d, simples = %b%n",
                    n, kn.numeroDeArestas(), arestasDeKn(n),
                    kn.grauSeRegular(), kn.eSimples());
        }
        System.out.println("Esperado (K1..K6): 0, 1, 3, 6, 10, 15 arestas.");

        System.out.println("\n--- Testando k-regular num grafo qualquer ---");
        Grafo cubo = new Grafo();
        // "Cubo" - grafo 3-regular classico (8 vertices, 12 arestas)
        String[][] arestasCubo = {
                {"000", "001"}, {"000", "010"}, {"000", "100"},
                {"011", "001"}, {"011", "010"}, {"011", "111"},
                {"101", "001"}, {"101", "100"}, {"101", "111"},
                {"110", "010"}, {"110", "100"}, {"110", "111"}
        };
        for (String[] a : arestasCubo) {
            cubo.adicionarAresta(a[0], a[1]);
        }
        System.out.println("Grafo cubo: " + cubo.numeroDeVertices() + " vertices, "
                + cubo.numeroDeArestas() + " arestas, grau constante = "
                + cubo.grauSeRegular() + " (esperado 3, grafo cubico)");

        System.out.println("\n--- Grafo NAO regular (para contraste) ---");
        Grafo estrela = new Grafo();
        estrela.adicionarAresta("centro", "p1");
        estrela.adicionarAresta("centro", "p2");
        estrela.adicionarAresta("centro", "p3");
        System.out.println("Estrela: grauSeRegular = " + estrela.grauSeRegular()
                + " (-1 = nao e regular; 'centro' tem grau 3, as pontas grau 1)");
    }
}
