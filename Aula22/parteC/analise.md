# Parte C — Análise do Algoritmo BFS (Busca em Largura)

Enunciado: efetuar a análise do algoritmo Busca em Largura e, com o emprego de Listas de Adjacências, aplicar o algoritmo BFS para o grafo direcionado abaixo.

- Arestas: 0→1, 0→2, 1→2, 1→3, 2→3, 3→4, 4→0, 4→1, 4→5
- Vértice inicial: 0
- Código fornecido: [GraphBFS.java](GraphBFS.java)
- Versão com distâncias, pais e a fila a cada passo: [BFSDistancias.java](BFSDistancias.java)

Para compilar e executar dentro desta pasta:

```bash
javac GraphBFS.java BFSDistancias.java && java GraphBFS && java BFSDistancias
```

Saída obtida com `GraphBFS`:

```
 0 2 1 3 4 5
```

---

## 1. Estrutura de dados: Listas de Adjacências

O código representa o grafo com três classes:

| Classe | Papel |
|---|---|
| `Node` | nó de uma lista encadeada: guarda o vértice destino (`dest`) e o próximo nó (`next`) |
| `adjList` | cabeça (`head`) da lista de adjacências de um vértice |
| `Graph` | número de vértices `V` e o vetor `array[V]`, que tem uma lista para cada vértice |

O método `addEdge(src, dest)` insere o novo nó **no início** da lista de `src`, o que custa O(1):

```java
Node n = new Node(dest);
n.next = array[src].head;
array[src].head = n;
```

Por isso cada lista fica na **ordem inversa** da inserção das arestas. Para o grafo do enunciado, as listas ficam assim:

| Vértice | Arestas inseridas (em ordem) | Lista resultante (`head → ...`) |
|---|---|---|
| 0 | 0→1, 0→2 | 2 → 1 |
| 1 | 1→2, 1→3 | 3 → 2 |
| 2 | 2→3 | 3 |
| 3 | 3→4 | 4 |
| 4 | 4→0, 4→1, 4→5 | 5 → 1 → 0 |
| 5 | — | (vazia) |

O grafo é **direcionado**, então cada aresta aparece em uma única lista. A soma dos tamanhos das listas é |E| = 9, e a memória total ocupada é Θ(V + E).

---

## 2. Funcionamento do método `BFS`

```java
public void BFS(int startVertex) {
    boolean[] visited = new boolean[V];
    Queue<Integer> s = new LinkedList<Integer>();

    s.add(startVertex);
    while (s.isEmpty() == false) {
        int n = s.poll();
        System.out.print(" " + n);
        visited[n] = true;
        Node head = array[n].head;
        while (head != null) {
            if (visited[head.dest] == false) {
                s.add(head.dest);
                visited[head.dest] = true;
            }
            head = head.next;
        }
    }
}
```

O método segue estes passos:

1. Coloca o vértice inicial na fila (`LinkedList` usada como `Queue`, ou seja, FIFO).
2. Enquanto a fila não estiver vazia, retira o primeiro vértice `n` com `poll()` e imprime `n`.
3. Percorre a lista de adjacências de `n`. Cada vizinho ainda não visitado é marcado como visitado e colocado no fim da fila.
4. Termina quando a fila esvazia, ou seja, quando todos os vértices alcançáveis a partir da origem foram visitados.

### 2.1 Comparação com o pseudocódigo do Cormen

| Cormen (`BFS(G, s)`) | `GraphBFS.BFS` |
|---|---|
| `u.color = WHITE` | `visited[u] == false` |
| `v.color = GRAY` e `ENQUEUE(Q, v)` | `visited[v] = true` e `s.add(v)` |
| `u = DEQUEUE(Q)` | `n = s.poll()` |
| `u.color = BLACK` | implícito: o vértice saiu da fila e sua lista foi percorrida |
| `v.d = u.d + 1` | **não calcula** |
| `v.π = u` | **não calcula** |

Observações sobre o código fornecido:

- **Marcar o vértice ao enfileirar** (e não ao retirar) é o que faz cada vértice entrar na fila **no máximo uma vez**. Isso corresponde a pintar o vértice de cinza no Cormen.
- A linha `visited[n] = true` logo após o `poll()` só tem efeito para o vértice inicial, porque todos os outros já foram marcados quando entraram na fila. O código ficaria mais claro marcando `startVertex` antes do laço, como faz o Cormen. O resultado não muda, porque nenhum vértice é processado antes de 0.
- O código só imprime a ordem de visita. Ele **não guarda** a distância nem o pai de cada vértice, então não dá para reconstruir a árvore BFS nem os caminhos mínimos. A versão [BFSDistancias.java](BFSDistancias.java) acrescenta essas duas informações (Seção 4).
- A busca só alcança os vértices **atingíveis** a partir da origem. Se o grafo tivesse vértices inalcançáveis, eles nunca seriam impressos.

---

## 3. Aplicação passo a passo (origem 0)

| Passo | Retira da fila | Vizinhos examinados (ordem da lista) | Entram na fila | Fila depois do passo | Ordem BFS |
|---|---|---|---|---|---|
| início | — | — | 0 | [0] | — |
| 1 | 0 | 2, 1 | 2, 1 | [2, 1] | 0 |
| 2 | 2 | 3 | 3 | [1, 3] | 0 2 |
| 3 | 1 | 3 (já visitado), 2 (já visitado) | — | [3] | 0 2 1 |
| 4 | 3 | 4 | 4 | [4] | 0 2 1 3 |
| 5 | 4 | 5, 1 (já visitado), 0 (já visitado) | 5 | [5] | 0 2 1 3 4 |
| 6 | 5 | (lista vazia) | — | [ ] | 0 2 1 3 4 5 |

**Resultado: `0 2 1 3 4 5`**. É a ordem que aparece no enunciado e a saída do programa.

**Por que 2 vem antes de 1?** A aresta 0→2 foi inserida depois da 0→1. Como `addEdge` insere no início da lista, a lista do vértice 0 ficou `2 → 1`. Se as arestas fossem inseridas no fim da lista, a ordem seria `0 1 2 3 4 5`. As duas ordens são buscas em largura válidas: a BFS garante que os vértices são visitados por camadas de distância, mas a ordem dentro de uma mesma camada depende da ordem das listas.

**Diferença em relação às figuras do enunciado.** A figura do *Step 5* mostra a fila `[3, 4]` depois de processar o vértice 1. Mas o grafo não tem aresta 1→4; a aresta é **4→1**. Então nada entra na fila nesse passo, e a fila correta é `[3]`, como mostra a tabela acima e a execução de `BFSDistancias`. O vértice 4 só entra na fila no passo seguinte, quando o vértice 3 é processado (aresta 3→4). A figura do *Step 6* já mostra a fila `[4]` corretamente. A ordem final de visita não muda.

---

## 4. Distâncias e árvore BFS

A versão [BFSDistancias.java](BFSDistancias.java) reaproveita as classes `Graph`, `Node` e `adjList` e acrescenta `d[]` (distância em número de arestas) e `pai[]` (π), exatamente como no Cormen. Saída para o grafo do enunciado:

```
Fila inicial: [0]
Processa 0 -> fila: [2 1]
Processa 2 -> fila: [1 3]
Processa 1 -> fila: [3]
Processa 3 -> fila: [4]
Processa 4 -> fila: [5]
Processa 5 -> fila: []
0: d=0 pai=NIL
1: d=1 pai=0
2: d=1 pai=0
3: d=2 pai=2
4: d=3 pai=3
5: d=4 pai=4
```

| Distância a partir de 0 | Vértices |
|---|---|
| 0 | 0 |
| 1 | 1, 2 |
| 2 | 3 |
| 3 | 4 |
| 4 | 5 |

A **árvore BFS** é formada pelas arestas (π[v], v): 0→2, 0→1, 2→3, 3→4 e 4→5. Ela tem V − 1 = 5 arestas. O caminho da árvore de 0 até qualquer vértice é um **caminho mínimo** em número de arestas. Por exemplo, 0 → 2 → 3 → 4 → 5 tem 4 arestas, e não existe caminho mais curto de 0 até 5.

As arestas 1→2, 1→3, 4→0 e 4→1 não entram na árvore, porque apontam para vértices que já tinham sido descobertos. O vértice 3 pode ser alcançado com 2 arestas tanto por 0→1→3 quanto por 0→2→3. O pai dele ficou sendo 2 porque 2 saiu da fila antes de 1.

### 4.1 Verificação com o exemplo do Cormen

Para validar a implementação, `BFSDistancias` também roda a BFS no grafo não direcionado do exemplo do enunciado (vértices r, s, t, u, v, w, x, y), com origem *s*:

```
r: d=1 pai=s
s: d=0 pai=NIL
t: d=2 pai=w
u: d=3 pai=t
v: d=2 pai=r
w: d=1 pai=s
x: d=2 pai=w
y: d=3 pai=x
```

As distâncias são as mesmas da figura final do enunciado (Cormen, fig. 22.3): r = 1, w = 1, t = 2, x = 2, v = 2, u = 3, y = 3. A ordem dentro da camada 2 saiu `x t` em vez de `t x` pelo mesmo motivo da Seção 3: a inserção no início da lista inverte a ordem dos vizinhos.

---

## 5. Análise de complexidade

Seja V o número de vértices e E o número de arestas.

**Tempo:**
- *Inicialização*: criar `visited[V]` custa O(V).
- *Operações na fila*: cada vértice é marcado ao entrar na fila e nunca é enfileirado de novo. Então há no máximo V operações `add` e V operações `poll`, cada uma O(1) na `LinkedList`, o que dá **O(V)**.
- *Percurso das listas*: a lista de cada vértice é percorrida uma única vez, quando ele sai da fila. A soma dos tamanhos de todas as listas é E (grafo direcionado) ou 2E (não direcionado), o que dá **O(E)**.
- **Total: O(V + E)**, linear no tamanho da representação do grafo.

**Espaço:**
- Listas de adjacências: Θ(V + E).
- Vetor `visited` e fila (no máximo V elementos): O(V).

**Comparação com a matriz de adjacências.** Com matriz (Atividade 02), descobrir os vizinhos de um vértice exige percorrer uma linha inteira, o que custa O(V). A BFS passaria a custar **O(V²)**, mesmo em grafos com poucas arestas. Por isso a lista de adjacências é a melhor representação para a BFS em grafos esparsos.

No grafo do enunciado, com V = 6 e E = 9, o algoritmo faz 6 retiradas da fila e examina 9 entradas das listas de adjacências.

---

## 6. Conclusão

O código fornecido implementa corretamente a busca em largura com listas de adjacências e visita os vértices na ordem **0 2 1 3 4 5**, em tempo O(V + E). A fila FIFO garante que os vértices são descobertos em camadas de distância crescente a partir da origem. Marcar cada vértice como visitado no momento em que ele entra na fila garante que ele seja processado uma única vez.

A ordem dentro de cada camada depende da ordem das listas de adjacências. Neste código, essa ordem é invertida, porque `addEdge` insere no início da lista. Acrescentando os vetores de distância e de pai, como em `BFSDistancias.java`, a mesma busca também produz a árvore BFS e os caminhos mínimos (em número de arestas) a partir da origem.
