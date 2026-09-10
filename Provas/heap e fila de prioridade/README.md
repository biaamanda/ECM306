# Unidade 15 - Heaps e Filas de Prioridade

## 1. Conceito

Um **heap (binário)** é uma **árvore binária quase (QUASE) completa**:

- completa até o **penúltimo** nível (nível `p` tem exatamente `2^p` nós);
- no **último** nível as folhas ficam o mais à **esquerda** possível;
- **max-heap:** o conteúdo de um nó é **maior ou igual** ao de toda a sua subárvore
  (a raiz guarda o **maior** elemento);
- **min-heap:** o conteúdo de um nó é **menor ou igual** ao de toda a sua subárvore
  (a raiz guarda o **menor** elemento).

Normalmente implementado com um **array** (não precisa criar nós/ponteiros).

Heapify: propriedade para reorganizar uma arvore desordenada em uma estrutura do tipo heap (elemento do topo/raiz eh o maior ou menor)

> Heap **não** é árvore binária de busca: só existe relação pai/filho, não há
> ordem entre irmãos nem entre subárvores esquerda e direita.

---

## 2. Representação em array

### Convenção dos slides: `A[1..m]` (índice 0 não usado)

| Operação    | Fórmula     |
| ----------- | ----------- |
| `PARENT(i)` | `⌊i / 2⌋`   |
| `LEFT(i)`   | `2 * i`     |
| `RIGHT(i)`  | `2 * i + 1` |

### Convenção 0-indexada: `A[0..n-1]`

| Operação      | Fórmula         |
| ------------- | --------------- |
| `pai(i)`      | `⌊(i - 1) / 2⌋` |
| `esquerda(i)` | `2 * i + 1`     |
| `direita(i)`  | `2 * i + 2`     |

---

## 3. Níveis, profundidade e altura

- **Nível / profundidade** do nó `i` (1-indexado): `⌊lg i⌋` — nº de arestas da raiz até `i`.(comeca em 0)
- **Altura de um nó** `i`: nº de arestas do caminho **mais longo** de `i` até uma folha.
  - folhas têm altura `0`;
  - pode-se mostrar que `h(i) = ⌊lg(m / i)⌋`.
- **Altura da árvore (raiz)**: `h = ⌊lg m⌋`.

### Fórmulas de árvore binária

- Nº **máximo** de nós numa árvore binária de altura `h`: `2^(h+1) - 1`.
  (altura 10 → `2^11 - 1 = 2047`).
- Árvore binária **cheia/completa (full)** com `N` nós: `N = 2^(h+1) - 1` ⇒ `h = lg(N + 1) - 1`.
  (`1023 = 2^10 - 1` ⇒ altura `9`).
- Nº de folhas de uma árvore full de altura `h`: `2^h`.
- Um heap com `n` nós tem `⌈n/2⌉` folhas (índices `⌊n/2⌋+1 .. n`).

---

## 4. Operações e complexidades

| Operação                        | O que faz                                          | Custo       |
| ------------------------------- | -------------------------------------------------- | ----------- |
| `MAX-HEAPIFY(A, m, i)`          | desce `A[i]` até restaurar a propriedade (peneira) | `O(lg m)`   |
| `BUILD-MAX-HEAP(A, n)`          | `heapify` de `⌊n/2⌋` até `1`                       | `O(n)`      |
| `inserir` (sobe / sift-up)      | põe no fim e sobe trocando com o pai               | `O(lg n)`   |
| `extrairMax` / `remover` (raiz) | tira a raiz, traz o último, `heapify(1)`           | `O(lg n)`   |
| `maximo` / `minimo`             | lê a raiz                                          | `O(1)`      |
| `aumentarChave` / `alterar`     | muda a chave e sobe/desce                          | `O(lg n)`   |
| `HEAPSORT`                      | `build` + `n-1` extrações trocando com o fim       | `O(n lg n)` |

### Pseudocódigo MAX-HEAPIFY

```
MAX-HEAPIFY(A, m, i)
1  e <- 2*i
2  d <- 2*i + 1
3  se e <= m e A[e] > A[i]
4      entao maior <- e
5      senao maior <- i
6  se d <= m e A[d] > A[maior]
7      entao maior <- d
8  se maior != i
9      entao A[i] <-> A[maior]
10         MAX-HEAPIFY(A, m, maior)
```

### Pseudocódigo BUILD-MAX-HEAP

```
BUILD-MAX-HEAP(A, n)
1  para i <- ⌊n/2⌋ decrescendo ate 1 faca
2      MAX-HEAPIFY(A, n, i)
```

---

## 5. Fila de prioridade

Fila em que cada elemento carrega uma **prioridade**. Na remoção sai sempre o
elemento de **maior prioridade** (aplicação clássica do heap).

Operações:

1. **Inserir** com prioridade;
2. **Remover** o elemento de mais alta prioridade;
3. **Alterar** a prioridade de um elemento;
4. **Retornar** o número de elementos;
5. **Testar** a existência de elementos de mesma prioridade.

---

## 6. Arquivos desta pasta

| Arquivo | Assunto |
| `MaxHeap.java` | Heap **1-indexado**: endereçamento, `maxHeapify`, `construirMaxHeap`, `inserir`, `extrairMax`, `aumentarChave`, altura/profundidade, impressão da árvore. |
| `MinHeap.java` | Min-heap **0-indexado**: mesma lógica com comparações invertidas.|
| `HeapSort.java` | HeapSort completo (0-indexado) |
| `FilaDePrioridade.java` | Fila de prioridade genérica sobre heap, com as 5 operações |
| `FilaPrioridadeHospital.java` | Aplicação com menu: pacientes com prioridade 0..3 |
| `VerificaHeap.java` | Dado um vetor, diz se é max-heap / min-heap e qual nó viola |
| `SimulaInsercaoHeap.java` | Simula inserções sucessivas num heap, mostrando cada passo |

Compilar e rodar (dentro da pasta):

```bash
javac *.java
java MaxHeap
java HeapSort
java FilaDePrioridade
java FilaPrioridadeHospital
java VerificaHeap
java SimulaInsercaoHeap
```

---

- **Profundidade ≠ altura.** Profundidade cresce de cima para baixo (raiz = 0);
  altura cresce de baixo para cima (folha = 0).
- `BUILD-MAX-HEAP` é **O(n)**, não `O(n lg n)` — só o HeapSort inteiro é `O(n lg n)`.
- Começa o `heapify` em `⌊n/2⌋` porque de `⌊n/2⌋+1` até `n` são **folhas** (já são heap).
- Max-heap **não** deixa o array ordenado; só garante o maior na raiz.
- Ao remover a raiz, quem sobe para o topo é o **último** elemento, seguido de `heapify(1)`.
- Ao inserir, o elemento entra na **última** posição e **sobe** (compara só com o pai).
- HeapSort ordena em ordem **crescente** usando um **max**-heap.
