import java.util.*;

// Grafo bipartido: X = professores, Y = dias. Aresta = disponibilidade.
class GrafoBipartido {
    private final List<List<Integer>> adj = new ArrayList<>();
    private final int nDireita;

    GrafoBipartido(int nEsquerda, int nDireita) {
        this.nDireita = nDireita;
        for (int i = 0; i < nEsquerda; i++) adj.add(new ArrayList<>());
    }
    void addAresta(int esq, int dir) { adj.get(esq).add(dir); }
    List<Integer> vizinhos(int esq) { return adj.get(esq); }
    int tamEsquerda() { return adj.size(); }
    int tamDireita() { return nDireita; }
}

// Emparelhamento maximo por caminhos M-aumentantes (Teorema de Berge)
class Emparelhamento {
    private final GrafoBipartido g;
    private final int[] parDoDia;      // parDoDia[d] = professor alocado no dia d (-1 = livre)

    Emparelhamento(GrafoBipartido g) {
        this.g = g;
        parDoDia = new int[g.tamDireita()];
        Arrays.fill(parDoDia, -1);
    }

    // procura caminho M-aumentante a partir do professor p
    private boolean tenta(int p, boolean[] visitado) {
        for (int d : g.vizinhos(p)) {
            if (visitado[d]) continue;
            visitado[d] = true;
            if (parDoDia[d] == -1 || tenta(parDoDia[d], visitado)) {
                parDoDia[d] = p;   // inverte as arestas do caminho
                return true;
            }
        }
        return false;
    }

    int resolver() {
        int total = 0;
        for (int p = 0; p < g.tamEsquerda(); p++)
            if (tenta(p, new boolean[g.tamDireita()])) total++;
        return total;
    }
    int[] getParDoDia() { return parDoDia; }
}

public class Horario {
    static final String[] DIAS = {"2a", "3a", "4a", "5a", "6a"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5;
        GrafoBipartido g = new GrafoBipartido(n, DIAS.length);
        System.out.println("Dias: 2=segunda 3=terca 4=quarta 5=quinta 6=sexta");
        for (int p = 0; p < n; p++) {
            System.out.print("Disponibilidade de P" + (p + 1) + " (ex: 3 5): ");
            String linha = sc.nextLine().trim();
            if (linha.isEmpty()) continue;
            for (String s : linha.split("[ ,;]+")) {
                s = s.replaceAll("\\D", "");          // ignora qualquer coisa que nao seja numero
                if (s.isEmpty()) continue;
                int dia = Integer.parseInt(s) - 2;   // 2..6 -> 0..4
                if (dia >= 0 && dia < DIAS.length) g.addAresta(p, dia);
            }
        }
        Emparelhamento m = new Emparelhamento(g);
        int total = m.resolver();
        if (total < n) {
            System.out.println("Disponibilidade SEM solucao (emparelhamento maximo = " + total + ")");
            return;
        }
        System.out.println("Uma solucao:");
        int[] par = m.getParDoDia();
        for (int d = 0; d < DIAS.length; d++)
            System.out.println(DIAS[d] + " -> " + (par[d] == -1 ? "-" : "P" + (par[d] + 1)));
    }
}
