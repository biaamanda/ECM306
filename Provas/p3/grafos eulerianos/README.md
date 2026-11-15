# Unidade 18 - Grafos Eulerianos

## 1. Passeio, Trilha e Caminho

Dado `G = (V,E)`, um **passeio** é uma sequência finita, alternando vértices
e arestas:

```
W = v0 e1 v1 e2 v2 ... vk-1 ek vk
```

onde cada aresta `ei` liga `vi-1` e `vi`. `k` (o nº de arestas) é o
**comprimento** do passeio. `v0` é a **origem**, `vk` o **término**.

- **Passeio**: pode repetir vértices e arestas à vontade.
- **Trilha**: passeio em que **nenhuma aresta se repete** (vértices podem repetir).
- **Caminho**: passeio (e portanto trilha) em que **nenhum vértice se
  repete** — exceto que `v0` e `vk` **podem** ser iguais.

Para `u ≠ v`: **aberto**. Para `u = v`: **fechado**.

| \                                | `u ≠ v` (aberto) | `u = v` (fechado)             |
| -------------------------------- | ---------------- | ----------------------------- |
| **Passeio** (repete à vontade)   | Passeio aberto   | Passeio fechado               |
| **Trilha** (aresta não repete)   | Trilha aberta    | Trilha fechada = **CIRCUITO** |
| **Caminho** (vértice não repete) | Caminho aberto   | Caminho fechado = **CICLO**   |

Relações :

- Todo **caminho** é uma **trilha**; toda **trilha** é um **passeio**.
- Nem toda trilha é caminho; nem todo passeio é trilha.
- **Teorema:** todo passeio `u-v` **contém** um caminho `u-v` (eliminando
  repetições sobra sempre um caminho por baixo).

> definição formal de "caminho fechado = ciclo" do slide não exige um
> tamanho mínimo, mas muitos livros só chamam de **ciclo** um caminho
> fechado com **3 ou mais vértices distintos** (duas arestas paralelas ou
> um laço às vezes são tratados como "ciclos degenerados" de tamanho 2 e 1).
> Preste atenção em qual convenção o enunciado está usando.

## 2. Trilha Euleriana e Grafo Euleriano

- **Trilha Euleriana**: trilha que percorre **todas** as arestas de `G`
  (cada uma exatamente uma vez — é trilha, não repete aresta).
- **Grafo Euleriano** (ou **Grafo de Euler**): tem uma trilha Euleriana
  **fechada**, ou seja, um **circuito** que usa todas as arestas.
- Se só existe uma trilha Euleriana **aberta** (não fechada), o grafo é
  chamado **semi-euleriano**

### O problema das pontes de Königsberg

Motivação histórica (1736): a cidade tinha 2 ilhas e 7 pontes ligando as
margens. Euler perguntou: dá para passear pela cidade cruzando **cada
ponte exatamente uma vez** e voltar ao ponto de partida? Modelando cada
região como vértice e cada ponte como aresta, o problema vira "esse
multigrafo é Euleriano?". Euler **provou que não** — ver `PontesDeKonigsberg.java`.

## 3. Teorema de Euler

```
Um grafo CONEXO G é um Grafo Euleriano
  se e somente se
o grau de TODO vértice de G for PAR.
```

Extensão (semi-euleriano): um grafo conexo tem uma trilha Euleriana
**aberta** se e somente se tiver **exatamente 0 ou 2** vértices de grau
ímpar (0 ímpares → é Euleriano de verdade, circuito fechado; 2 ímpares →
a trilha aberta obrigatoriamente começa em um deles e termina no outro).
Com mais de 2 vértices ímpares, **não existe** trilha Euleriana de
nenhum tipo.

### Por que funciona (intuição)

Numa trilha que passa por um vértice `v` (sem ser origem/término), cada
"passada" usa uma aresta para entrar e outra para sair — as arestas em
`v` se agrupam em pares, então `d(v)` tem que ser par. Só a origem e o
término (se forem diferentes) podem "sobrar" com uma aresta a mais,
ficando com grau ímpar.

## 4. Casos particulares úteis

- `Kn` é `(n-1)`-regular ⇒ `Kn` é Euleriano ⇔ `n-1` é par ⇔ **`n` é ímpar**
  (ex.: `K5` é Euleriano; `K4` e `K3,3` não são).
- `Km,n` bipartido completo: vértices de `X` têm grau `n`, vértices de `Y`
  têm grau `m` ⇒ Euleriano ⇔ **`m` e `n` são ambos pares**.

## 5. Arquivos desta pasta

| Arquivo | Assunto |
| `Grafo.java` | Multigrafo com **arestas rotuladas** (`e1`, `e2`, ...), igual à notação dos slides: `grau`, `eConexo` (BFS), `verticesGrauImpar`, `vizinhanca`. |
| `PasseioTrilhaCaminho.java` | Classifica uma sequência `v0 e1 v1 e2 v2 ...` como passeio/trilha/caminho, aberto/fechado, circuito/ciclo. |
| `GrafoEuleriano.java` | Implementa o Teorema de Euler (`eEuleriano`, `eSemiEuleriano`) + verificação de que uma trilha dada realmente usa cada aresta uma vez. Testado com os grafos G1-G4 dos slides (caminho semi-euleriano, grade euleriana, `K5`, `K3,3`). |
| `PontesDeKonigsberg.java` | modela as 7 pontes e mostra por que não há solução. |
| `VerificaEuleriano.java` | Roda o Teorema de Euler nos grafos das Questões 2 e 15 do `Aula18/Simulado_03_Resolucao.md`, conferindo os resultados (semi-euleriano e não-euleriano). |

Compilar e rodar (dentro da pasta):

```bash
javac *.java
java Grafo
java PasseioTrilhaCaminho
java GrafoEuleriano
java PontesDeKonigsberg
java VerificaEuleriano
```

---

## 6. Pegadinhas frequentes

- O Teorema de Euler exige o grafo **conexo** — um grafo com todos os graus
  pares mas **desconexo** (duas peças separadas, cada uma "fechada") **não**
  é Euleriano (não dá pra visitar as duas peças numa trilha só).
- "Todo grau par" garante um **circuito**; "exatamente 2 ímpares" garante só
  uma **trilha aberta** (começando e terminando nos dois vértices ímpares).
- Mais de 2 vértices de grau ímpar ⇒ **nenhuma** trilha Euleriana existe
  (nem aberta, nem fechada) — é exatamente o caso das pontes de Königsberg
  (4 vértices de grau ímpar).
- Vértice isolado (grau 0) **não impede** o grafo de ser Euleriano por
  causa da paridade (0 é par!) — mas impede a **conexidade** se houver mais
  de um vértice no grafo.
- Não confundir **circuito** (trilha fechada, pode repetir vértices) com
  **ciclo** (caminho fechado, não repete vértices) — um circuito Euleriano
  tipicamente passa mais de uma vez por vértices de grau alto, então **não**
  costuma ser um ciclo.
