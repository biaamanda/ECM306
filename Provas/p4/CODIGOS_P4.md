# Códigos — P4 ECM306

> **Para adaptar a uma questão nova**, use as bases comentadas de [BASES_P4.md](BASES_P4.md) (pasta [bases/](bases/)).

Códigos Java testados com os casos das provas antigas. Os arquivos `.java` também estão em [modelos/](modelos/). A teoria está em [TEORIA_P4.md](TEORIA_P4.md).

## Como gerar e rodar o .jar

> Passo a passo completo, inclusive para um computador sem nada configurado: veja o início de [TEORIA_P4.md](TEORIA_P4.md).

Na pasta dos arquivos `.java`, abra um terminal e rode:

```bash
javac --release 8 *.java
```
```bash
jar cfe Programa.jar NomeDaClasseComMain *.class
```
```bash
java -jar Programa.jar
```

- `cfe` = criar (**c**), nome do arquivo (**f**), classe que tem o `main` (**e**).
- `--release 8` faz o .jar rodar mesmo em computador com Java antigo.
- Se aparecer "jar não é reconhecido" (por exemplo, num computador do IMT), use o caminho completo do JDK, ex.: `"C:\Program Files\Java\jdk-25.0.2\bin\jar" cfe ...`, ou gere pela IDE (IntelliJ: *Build → Build Artifacts*; Eclipse: *Export → Runnable JAR file*).
- **Se usar `package`** (ex.: `package br.maua;`): `javac --release 8 -d out *.java` e depois `jar cfe Programa.jar br.maua.Main -C out .`

Escreva no .docx da prova o comando exato para executar.

---

## 1. Emparelhamento (P4 2024 — professores × dias)
**Use quando:** precisar alocar pessoas em recursos (casais/quartos, professores/dias, equipes/chamados).
**Ideia:** para cada professor, tenta um dia livre; se o dia estiver ocupado, tenta mudar o dono atual para outro dia (caminho aumentante).
**Entrada:** os dias de cada professor, ex.: `3 5` = terça e quinta.

```java
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
```

---

## 2. Heap — fila de prioridade (P4 2023 — consultório)
**Use quando:** precisar atender por prioridade.
**Truque:** senha = prioridade × 1000 + contador (1001, 2001…). Como 1003 < 2001, um min-heap simples já atende na ordem certa.
O `main` abaixo só roda os casos de teste. Se a questão pedir botões, troque o `main` por um menu com `JOptionPane`.

```java
// Min-heap em vetor (indice 1): filho esq = 2i, filho dir = 2i+1, pai = i/2
class MinHeap {
    private int[] v = new int[2];
    private int n = 0;

    boolean vazio() { return n == 0; }
    void limpar() { n = 0; }

    void inserir(int x) {
        if (n + 1 == v.length) v = java.util.Arrays.copyOf(v, v.length * 2);
        v[++n] = x;
        int i = n;                                  // sobe (heapify-up)
        while (i > 1 && v[i / 2] > v[i]) { troca(i, i / 2); i /= 2; }
    }

    int remover() {
        int raiz = v[1];
        v[1] = v[n--];
        int i = 1;                                  // desce (heapify-down)
        while (2 * i <= n) {
            int f = 2 * i;
            if (f + 1 <= n && v[f + 1] < v[f]) f++;
            if (v[i] <= v[f]) break;
            troca(i, f); i = f;
        }
        return raiz;
    }
    private void troca(int a, int b) { int t = v[a]; v[a] = v[b]; v[b] = t; }
}

class Atendimento {
    private final MinHeap heap = new MinHeap();
    private final int[] contador = new int[5];  // contador por prioridade 1..4

    void inicio() { heap.limpar(); java.util.Arrays.fill(contador, 0); }

    // senha = prioridade*1000 + contador -> 1003 < 2001, entao min-heap ja ordena certo
    int novaSenha(int prioridade) {
        int senha = prioridade * 1000 + (++contador[prioridade]);
        heap.inserir(senha);
        return senha;
    }
    String proximo() { return heap.vazio() ? "Nao ha paciente (ED VAZIA)" : String.valueOf(heap.remover()); }
}

public class Consultorio {
    public static void main(String[] args) {
        Atendimento a = new Atendimento();
        System.out.println(a.proximo());
        for (int p : new int[]{4, 3, 2, 1, 1, 2}) System.out.println("senha " + a.novaSenha(p));
        System.out.println("prox " + a.proximo());
        System.out.println("senha " + a.novaSenha(1));
        for (int i = 0; i < 4; i++) System.out.println("prox " + a.proximo());
        System.out.println("senha " + a.novaSenha(2));
        System.out.println("prox " + a.proximo());
        System.out.println("senha " + a.novaSenha(4));
        System.out.println("prox " + a.proximo());
        System.out.println("prox " + a.proximo());
        a.inicio(); System.out.println("ED VAZIA");
        System.out.println(a.proximo());
    }
}
```

---

## 3. Hash com encadeamento (P4 2023 — nomes de alunos)
**Use quando:** precisar de tabela hash; aqui o índice é a letra inicial (A=0 … Z=25).
**Colisão:** o nome entra no fim da lista daquela posição. Não usa nenhuma coleção pronta do Java.

```java
// Lista encadeada feita a mao (nao pode usar java.util)
class No {
    String nome; No prox;
    No(String nome) { this.nome = nome; }
}

class TabelaHash {
    private final No[] tabela = new No[26];

    private int hash(String nome) { return Character.toUpperCase(nome.charAt(0)) - 'A'; }

    void inserir(String nome) {            // encadeamento separado: insere no FIM
        int i = hash(nome);
        No novo = new No(nome);
        if (tabela[i] == null) { tabela[i] = novo; return; }
        No p = tabela[i];
        while (p.prox != null) p = p.prox;
        p.prox = novo;
    }

    int buscar(String nome) {              // retorna nr de tentativas (-1 se nao achou)
        int tentativas = 0;
        for (No p = tabela[hash(nome)]; p != null; p = p.prox) {
            tentativas++;
            if (p.nome.equals(nome)) return tentativas;
        }
        return -1;
    }

    void imprimir() {
        boolean vazia = true;
        StringBuilder sb = new StringBuilder("Indice Nome\n");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] == null) continue;
            vazia = false;
            sb.append(i).append("  ");
            for (No p = tabela[i]; p != null; p = p.prox)
                sb.append(p.nome).append(p.prox == null ? ";" : ", ");
            sb.append('\n');
        }
        System.out.print(vazia ? "VAZIA\n" : sb.toString());
    }
}

public class Alunos {
    public static void main(String[] args) {
        TabelaHash t = new TabelaHash();
        t.imprimir();
        for (String n : new String[]{"PAULO", "ZULEIDE", "ANTONIO", "PEDRO", "PALOMA"}) { t.inserir(n); t.imprimir(); }
        for (String n : new String[]{"PAULO", "ZULEIDE", "PALOMA"})
            System.out.println(n + " localizado em " + t.buscar(n) + " tentativa(s)");
    }
}
```

---

## 4. BFS, Dijkstra e Ordenação topológica (T25)
- **BFS:** menor número de saltos.
- **Dijkstra:** menor custo com pesos ≥ 0.
- **Kahn:** ordem de tarefas; lança erro se houver dependência circular.
- `caminho(destino, anterior)` reconstrói a rota.

```java
import java.util.*;

class Aresta {
    final int destino, peso;
    Aresta(int destino, int peso) { this.destino = destino; this.peso = peso; }
}

class Grafo {
    private final List<List<Aresta>> adj = new ArrayList<>();
    Grafo(int n) { for (int i = 0; i < n; i++) adj.add(new ArrayList<>()); }
    int tamanho() { return adj.size(); }
    void addDirigida(int u, int v, int p) { adj.get(u).add(new Aresta(v, p)); }
    void addNaoDirigida(int u, int v, int p) { addDirigida(u, v, p); addDirigida(v, u, p); }
    List<Aresta> vizinhos(int u) { return adj.get(u); }

    // BFS: menor numero de arestas. O(V+E)
    int[] bfs(int origem, int[] anterior) {
        int[] dist = new int[tamanho()];
        Arrays.fill(dist, -1); Arrays.fill(anterior, -1);
        Queue<Integer> fila = new ArrayDeque<>();
        dist[origem] = 0; fila.add(origem);
        while (!fila.isEmpty()) {
            int u = fila.remove();
            for (Aresta a : vizinhos(u))
                if (dist[a.destino] == -1) {
                    dist[a.destino] = dist[u] + 1;
                    anterior[a.destino] = u;
                    fila.add(a.destino);
                }
        }
        return dist;
    }

    // Dijkstra: menor custo, pesos >= 0. O((V+E) log V)
    int[] dijkstra(int origem, int[] anterior) {
        int[] dist = new int[tamanho()];
        Arrays.fill(dist, Integer.MAX_VALUE); Arrays.fill(anterior, -1);
        PriorityQueue<int[]> fila = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        dist[origem] = 0; fila.add(new int[]{origem, 0});
        while (!fila.isEmpty()) {
            int[] at = fila.remove();
            int u = at[0];
            if (at[1] != dist[u]) continue;            // entrada velha
            for (Aresta a : vizinhos(u)) {
                int nova = dist[u] + a.peso;            // relaxamento
                if (nova < dist[a.destino]) {
                    dist[a.destino] = nova; anterior[a.destino] = u;
                    fila.add(new int[]{a.destino, nova});
                }
            }
        }
        return dist;
    }

    // Kahn: ordenacao topologica. O(V+E). Lanca excecao se houver ciclo
    List<Integer> ordemTopologica() {
        int n = tamanho();
        int[] entrada = new int[n];
        for (int u = 0; u < n; u++) for (Aresta a : vizinhos(u)) entrada[a.destino]++;
        Queue<Integer> fila = new ArrayDeque<>();
        for (int v = 0; v < n; v++) if (entrada[v] == 0) fila.add(v);
        List<Integer> ordem = new ArrayList<>();
        while (!fila.isEmpty()) {
            int u = fila.remove();
            ordem.add(u);
            for (Aresta a : vizinhos(u)) if (--entrada[a.destino] == 0) fila.add(a.destino);
        }
        if (ordem.size() != n) throw new IllegalStateException("Dependencia circular");
        return ordem;
    }

    static List<Integer> caminho(int destino, int[] anterior) {
        List<Integer> rota = new ArrayList<>();
        for (int v = destino; v != -1; v = anterior[v]) rota.add(v);
        Collections.reverse(rota);
        return rota;
    }
}

public class Algoritmos {
    public static void main(String[] args) {
        String nomes = "ABCDEF";
        // Exercicio 3 da T25: A-B=4, A-C=2, C-B=1, B-D=5, C-D=8, D-E=2, E-F=3, D-F=6
        Grafo g = new Grafo(6);
        int[][] e = {{0,1,4},{0,2,2},{2,1,1},{1,3,5},{2,3,8},{3,4,2},{4,5,3},{3,5,6}};
        for (int[] x : e) g.addNaoDirigida(x[0], x[1], x[2]);
        int[] ant = new int[6];
        int[] d = g.dijkstra(0, ant);
        StringBuilder sb = new StringBuilder();
        for (int v : Grafo.caminho(5, ant)) sb.append(nomes.charAt(v));
        System.out.println("Dijkstra dist=" + Arrays.toString(d) + " rota=" + sb);
        d = g.bfs(0, ant);
        System.out.println("BFS saltos=" + Arrays.toString(d));
        // Exercicio 5: A->C, B->C, C->D, B->E, D->F, E->F
        Grafo t = new Grafo(6);
        int[][] dep = {{0,2},{1,2},{2,3},{1,4},{3,5},{4,5}};
        for (int[] x : dep) t.addDirigida(x[0], x[1], 1);
        sb = new StringBuilder();
        for (int v : t.ordemTopologica()) sb.append(nomes.charAt(v));
        System.out.println("Topologica=" + sb);
        // Exercicio 6: A->B, B->C, C->A, C->D (ciclo)
        Grafo c = new Grafo(4);
        c.addDirigida(0,1,1); c.addDirigida(1,2,1); c.addDirigida(2,0,1); c.addDirigida(2,3,1);
        try { c.ordemTopologica(); } catch (IllegalStateException ex) { System.out.println("Ciclo: " + ex.getMessage()); }
    }
}
```

### Menu com botões (se a questão pedir)
```java
String[] botoes = {"INICIO", "PRIORIDADE 1", "PRIORIDADE 2", "PRIORIDADE 3", "PRIORIDADE 4", "PROXIMO", "SAIR"};
Atendimento a = new Atendimento();
while (true) {
    int op = javax.swing.JOptionPane.showOptionDialog(null, "Escolha uma opcao", "Consultorio",
            0, javax.swing.JOptionPane.PLAIN_MESSAGE, null, botoes, null);
    if (op == -1 || op == 6) break;
    String msg;
    if (op == 0) { a.inicio(); msg = "ED VAZIA"; }
    else if (op == 5) msg = "Proximo: " + a.proximo();
    else msg = "Sua senha: " + a.novaSenha(op);
    javax.swing.JOptionPane.showMessageDialog(null, msg);
}
```
