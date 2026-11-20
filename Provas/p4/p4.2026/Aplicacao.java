import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite sua origem: ");

        System.out.print("Digite seu destino: ");

         // ---------- 1. MONTAR O GRAFO ----------
        // ALTERE: nomes dos vertices (ou leia do teclado)
        String[] nomes = {"A", "B", "C", "D", "E", "F", "G", "H"};

        // ALTERE: true se as arestas tiverem direcao
        boolean dirigido = false;

        Grafo g = new Grafo(nomes, dirigido);

        // ALTERE: leitura das arestas. Formato: "A B 4" (sem peso: "A B").
        // Linha vazia termina a leitura.
        System.out.println("Vertices: " + Arrays.toString(nomes));
        System.out.println("Digite as arestas (ex.: A B 4). Linha vazia para terminar:");
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine().trim();
            if (linha.isEmpty()) break;
            String[] p = linha.split("\\s+");
            int u = g.indice(p[0]);
            int v = p.length > 1 ? g.indice(p[1]) : -1;
            if (u == -1 || v == -1) { System.out.println("Vertice invalido, digite de novo."); continue; }
            int peso = p.length > 2 ? Integer.parseInt(p[2]) : 1;
            g.addAresta(u, v, peso);
        }

        // ---------- 2. RESPONDER ----------
        // ALTERE: apague o que a questao nao pedir.
        int origem = 0;                                       // ALTERE: vertice de partida
        int destino = g.n() - 1;                              // ALTERE: vertice de chegada
        int[] anterior = new int[g.n()];

        System.out.println("\nSequencia de graus: " + Arrays.toString(g.sequenciaGraus()));
        System.out.println("Bipartido: " + g.bipartido());

        int[] saltos = g.bfs(origem, anterior);
        System.out.println("BFS - saltos ate " + g.nome(destino) + ": " + saltos[destino]
                + "  rota: " + (saltos[destino] == -1 ? "ROTA INEXISTENTE" : g.nomes(g.caminho(destino, anterior))));

        System.out.println("DFS a partir de " + g.nome(origem) + ": " + g.nomes(g.dfs(origem)));

        int[] custo = g.dijkstra(origem, anterior);
        System.out.println("Dijkstra - custo ate " + g.nome(destino) + ": "
                + (custo[destino] == Integer.MAX_VALUE ? "ROTA INEXISTENTE"
                   : custo[destino] + "  rota: " + g.nomes(g.caminho(destino, anterior))));

      
        if (dirigido) {
            List<Integer> topo = g.ordemTopologica();
            System.out.println("Ordem topologica: " + (topo == null ? "DEPENDENCIA CIRCULAR" : g.nomes(topo)));
        }

        scanner.close();
    }
}