# Unidade 19 - Grafos Hamiltonianos

Códigos-base para a prova (P3). Segue os slides do Prof. Calvetti
(`IMT-2026-ECM306-T19-GrafosHamiltonianos`). Pressupõe a Unidade 17
(conceitos de grafo, grau, subgrafo, supergrafo) e a Unidade 18 (passeio,
trilha, caminho, ciclo) — ver as outras pastas de `Provas/`.

---

## 1. Definições

- **Caminho Hamiltoniano** em `G`: um **caminho** (não repete vértice) que
  contém **todo** vértice de `G` — não precisa usar todas as arestas.
- **Ciclo Hamiltoniano**: um **ciclo** (caminho fechado) que contém **todo**
  vértice de `G`.
- **Grafo Hamiltoniano**: um grafo `G` que **tem** um ciclo Hamiltoniano.

> Diferença chave para a Unidade 18: Euleriano é sobre usar **toda aresta**
> uma vez; Hamiltoniano é sobre visitar **todo vértice** uma vez. São
> propriedades independentes — um grafo pode ser as duas coisas, nenhuma,
> ou só uma delas.

**Observação (supergrafo):** se `G` é Hamiltoniano e `G*` é um supergrafo
de `G` (mesmos vértices, arestas de `G` mais outras), então `G*` também é
Hamiltoniano — o próprio ciclo de `G` continua valendo em `G*`.

## 2. Grafo não hamiltoniano maximal

`G` (simples) é **não hamiltoniano maximal** se:

1. `G` **não** é Hamiltoniano, **e**
2. ao **adicionar qualquer aresta** entre dois vértices não adjacentes de
   `G`, o grafo resultante **passa a ser** Hamiltoniano.

Ou seja: `G` está "a uma aresta de distância" de ser Hamiltoniano, **não
importa qual** aresta faltante você escolha adicionar. Ver
`GrafoNaoHamiltonianoMaximal.java`.

## 3. Teorema de Dirac (condição suficiente)

```
Seja G = (V,E) um grafo simples com n vertices, n >= 3.
Se para TODO vertice v, d(v) >= n/2, entao G e Hamiltoniano.
```

- `n >= 3` é necessário (com 2 vértices não dá pra fechar um ciclo).
- A condição é **suficiente, mas NÃO necessária**: existem grafos
  Hamiltonianos que não satisfazem `d(v) >= n/2` para todo `v` (ex.: o
  ciclo `C5`, onde todo grau é `2 < 5/2`, mas o próprio `C5` já é um ciclo
  Hamiltoniano).

## 4. Teorema de Ore (corolário do de Dirac)

```
Seja G = (V,E) um grafo simples com n vertices, n >= 3.
Se para TODO PAR de vertices NAO ADJACENTES u, v,  d(u) + d(v) >= n,
entao G e Hamiltoniano.
```

- Também **suficiente, não necessária** (o mesmo `C5` contra-exemplifica:
  `d(v1) + d(v3) = 2 + 2 = 4 < 5`, mas `C5` é Hamiltoniano).
- Dirac é um caso particular de Ore (se todo `d(v) >= n/2`, então qualquer
  par soma `>= n` automaticamente).

## 5. Fechamento de um grafo `c(G)` e Teorema de Bondy(-Chvátal)

**Fechamento `c(G)`:** enquanto existir um par de vértices **não
adjacentes** `u, v` com `d(u) + d(v) >= n`, adicione a aresta `u-v`.
Repita até não sobrar nenhum par assim. O resultado é `c(G)` (é um
teorema, fora do escopo da disciplina, que o resultado final **não
depende da ordem** em que as arestas são adicionadas).

```
Teorema de Bondy:  G e Hamiltoniano  <=>  c(G) e Hamiltoniano.

Corolario:  se c(G) = Kn (o fechamento e o grafo completo),
            entao G e Hamiltoniano.
```

Isso é muito mais forte que Dirac/Ore isolados: mesmo que `G` não
satisfaça Dirac/Ore diretamente, seu fechamento pode "ligar os pontos"
até virar `Kn`, provando que `G` é Hamiltoniano sem achar o ciclo na mão.
Ver `FechamentoDeGrafo.java`.

## 6. O Problema do Caixeiro Viajante (TSP)

Modelagem: vértices = cidades, arestas ponderadas = estradas (peso =
distância). Pergunta: existe um ciclo Hamiltoniano de peso **mínimo**
(rota que visita cada cidade uma vez e volta ao início, gastando o menos
possível)?

- **Não existe** (até hoje) algoritmo que resolva isso em **tempo
  polinomial** — é um problema **NP-completo**.
- Na prática, ataca-se com **heurísticas**: algoritmos que costumam achar
  boas soluções rapidamente, mas **sem garantia** de acharem a ótima.
- `Caixeiro Viajante` é o exemplo clássico citado para separar problemas
  **P** (tratáveis, tempo polinomial) de **NP-completos** (aparentemente
  intratáveis em geral).

Ver `CaixeiroViajante.java` para uma solução **exata por força bruta**
(só viável para poucas cidades, já que é `O((n-1)!)`).

## 7. Arquivos desta pasta

| Arquivo                             | Assunto                                                                 |
|---------------------------------------|--------------------------------------------------------------------------|
| `Grafo.java`                          | Grafo simples (sem laço/paralela): grau, vizinhança, adjacência, cópia. |
| `CicloCaminhoHamiltoniano.java`       | Busca por força bruta (permutações) de caminho/ciclo Hamiltoniano. Testado nos grafos G1-G5 dos slides (exemplos 1-5). |
| `TeoremaDiracOre.java`                | Confere as condições de Dirac e de Ore. Testado com `K3,3` (Dirac ok), o ciclo `C5` (nenhum dos dois vale, mas é Hamiltoniano) e um exemplo de 5 vértices (Ore ok). |
| `GrafoNaoHamiltonianoMaximal.java`    | Confere se um grafo não-Hamiltoniano é "maximal" (toda aresta que falta, se adicionada, cria um grafo Hamiltoniano). |
| `FechamentoDeGrafo.java`              | Calcula `c(G)` e verifica o Teorema de Bondy. Testado com um grafo de 6 vértices cujo fechamento vira `K6` em 7 passos. |
| `CaixeiroViajante.java`               | TSP exato por força bruta num grafo completo ponderado pequeno. |

Compilar e rodar (dentro da pasta):

```bash
javac *.java
java CicloCaminhoHamiltoniano
java TeoremaDiracOre
java GrafoNaoHamiltonianoMaximal
java FechamentoDeGrafo
java CaixeiroViajante
```

---

## 8. Pegadinhas frequentes

- Hamiltoniano é sobre **vértices**, Euleriano é sobre **arestas** — não
  confunda os dois circuitos/ciclos.
- Um vértice de **grau 1** nunca pode estar num ciclo (precisa de 2
  arestas distintas para entrar e sair) — se algum vértice tem grau 1, o
  grafo **não** é Hamiltoniano (mas pode ter caminho Hamiltoniano).
- Dirac e Ore são **condições suficientes, não necessárias** — não passar
  no teste **não prova** que o grafo não é Hamiltoniano, só que esses
  teoremas não conseguem garantir. Pode ainda ser Hamiltoniano (aí é
  preciso achar o ciclo por outro método, ou usar o fechamento `c(G)`).
- Dirac exige a condição para **todo** vértice; Ore só para os **pares não
  adjacentes** (pares já adjacentes não entram na conta).
- `Kn` (n >= 3) é sempre Hamiltoniano (todo vértice tem grau `n-1 >= n/2`
  → satisfaz Dirac direto).
- Não existe algoritmo eficiente conhecido para o Caixeiro Viajante
  (problema NP-completo) — força bruta serve só para poucas cidades.
