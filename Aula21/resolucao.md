# Simulado Grafos — Resolução

## Questão 1 ✓

- Um grafo `G = (V, E)` é formado por um conjunto de **vértices** `V` e um
  conjunto de **arestas** `E`, onde cada aresta liga dois vértices
  (suas extremidades), ex.: `e1 = {v1, v3}`.

V = {v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11} e
E = {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15}

## Questão 2 ✓

- Arestas paralelas: duas (ou mais) arestas com os mesmos vértices-extremidades.
  Considerando o grafo G, não há arestas paralelas.

## Questão 3 ✓

- Vértice isolado: não é extremidade de nenhuma aresta (grau 0).
  Considerando o grafo G, não há vértices isolados, pois todos têm grau ≥ 2.

## Questão 4 ✓

- Conjunto vizinhança `N(v)`: todos os vizinhos de `v`.
  N(v6) = {v5, v8, v9, v11}
  N(v9) = {v6, v8, v10, v11}

## Questão 5 ✓

- "Grafo simples" é sobre a ausência de laços/paralelas.
  Como G não possui laços nem arestas paralelas, G é simples.

## Questão 6 ✓

- Grau `deg(v)`: número de arestas incidentes em `v` (um laço conta 2).

deg(v1) = 2
deg(v2) = 2
deg(v3) = 3
deg(v4) = 2
deg(v5) = 3
deg(v6) = 4
deg(v7) = 2
deg(v8) = 4
deg(v9) = 4
deg(v10) = 2
deg(v11) = 2

## Questão 7 ✓

- Sequência de graus: lista com os graus (números) de todos os vértices,
  geralmente em ordem não crescente, ex.: `(4, 3, 3, 2)`.

  Sequência de graus de G = (4, 4, 4, 3, 3, 2, 2, 2, 2, 2, 2)

## Questão 8 ✓

`G` é k-regular se todo vértice tem grau `k`.
G não é regular, pois possui vértices com graus diferentes
(ex.: deg(v1) = 2 ≠ deg(v6) = 4).

## Questão 9

- Grafo **cúbico**: grafo 3-regular (todo vértice tem grau 3).

## Questão 10

- **Lema do aperto de mãos**: `Σ deg(v) = 2·|E|`. Logo a soma dos graus é
  sempre **par** (e o número de vértices de grau ímpar também é par).
- Em um grafo simples com `n` vértices, o grau máximo é `n − 1`.

## Questão 11

- Mesmo raciocínio da questão 10: a soma dos graus precisa ser par
  (`Σ deg(v) = 2·|E|`) e `deg(v) ≤ n − 1`.

## Questão 12

- **Grafo de intersecção**: um vértice para cada conjunto `Ai`; existe aresta
  entre `Ai` e `Aj` (i ≠ j) se e somente se `Ai ∩ Aj ≠ ∅`.

## Questão 13

- Ver definição de isomorfismo na questão 15. Se dois grafos são isomorfos,
  eles têm obrigatoriamente o **mesmo número de vértices**.

## Questão 14

- Ver definição de isomorfismo na questão 15. Se dois grafos são isomorfos,
  eles têm obrigatoriamente o **mesmo número de arestas**.

## Questão 15 ✓

`G1=(V1,E1)` e `G2=(V2,E2)` são **isomorfos** se existem bijeções
`f: V1→V2` e `g: E1→E2` que preservam a incidência (a aresta `g(e)` liga
`f(u)` e `f(v)` sempre que `e` ligava `u` e `v`).

**Invariantes** (se G1≅G2, então G1 e G2 têm que ter): o mesmo número de
vértices, o mesmo número de arestas, e a mesma sequência de graus (mesmo
número de vértices de cada grau).

> Para **provar que NÃO são isomorfos** basta achar 1 invariante diferente.
> Para provar que **são** isomorfos, é preciso exibir as funções f e g.

V1 = {v1, v2, v3, v4, v5, v6}, |E1| = 10
deg(v1) = 3
deg(v2) = 4
deg(v3) = 4
deg(v4) = 3
deg(v5) = 2
deg(v6) = 4
Sequência de graus: (4, 4, 4, 3, 3, 2)

V2 = {v1, v2, v3, v4, v5, v6}, |E2| = 10
deg(v1) = 2
deg(v2) = 4
deg(v3) = 4
deg(v4) = 4
deg(v5) = 4
deg(v6) = 2
Sequência de graus: (4, 4, 4, 4, 2, 2)

G1 e G2 têm o mesmo número de vértices (6) e de arestas (10), mas sequências de graus diferentes. Como a sequência de graus é um invariante, G1 e G2 não são isomorfos.

## Questão 16

- **Grafo completo `Kn`**: grafo simples com `n` vértices em que todo par de
  vértices distintos é ligado por uma aresta.
  Número de arestas: `|E| = n(n − 1) / 2` (= C(n, 2), número de pares).

## Questão 17

- Ver questão 16: `|E(Kn)| = n(n − 1) / 2`.

## Questão 18

- **Grafo bipartido completo `Km,n`**: vértices divididos em dois grupos
  `X` (com `m` vértices) e `Y` (com `n` vértices); **cada** vértice de `X` é
  ligado a **todos** os de `Y`, e não há arestas dentro do mesmo grupo.

## Questão 19

- Ver questão 18 (`Km,n`).

## Questão 20

- `V` pode ser particionado em dois conjuntos não vazios e disjuntos
  `X` e `Y` (`X ∪ Y = V`, `X ∩ Y = ∅`) tais que toda aresta tem uma
  extremidade em `X` e a outra em `Y` (nunca liga dois vértices do mesmo
  lado). Um grafo é bipartido se e somente se não tem ciclo de tamanho ímpar.

## Questão 21

> Obs.: o enunciado diz "questão 21", mas se refere ao grafo G da questão 20.

- **Supergrafo**: `G' = (V', E')` é supergrafo de `G = (V, E)` se `V ⊆ V'` e
  `E ⊆ E'` (ou seja, G está contido em G'; G' tem tudo de G e pode ter mais).

## Questão 22

> Obs.: o grafo G é o da questão 20.

- **Subgrafo**: `H = (V', E')` é subgrafo de `G = (V, E)` se `V' ⊆ V` e
  `E' ⊆ E`, e toda aresta de `E'` tem as duas extremidades em `V'`.

## Questão 23

- **Passeio**: sequência alternada vértice–aresta–vértice… (pode repetir
  vértices e arestas). **Aberto**: começa e termina em vértices diferentes;
  **fechado**: começa e termina no mesmo vértice.
- **Trilha**: passeio **sem repetir arestas** (vértices podem repetir).
- **Circuito**: trilha fechada (sem repetir arestas, volta ao início).
- **Caminho**: passeio **sem repetir vértices** (logo, nem arestas).
- **Ciclo**: caminho fechado (só o vértice inicial = final se repete).
  Um laço (aresta de um vértice nele mesmo) já forma um ciclo.

## Questão 24

- **Grafo Euleriano**: possui um **circuito Euleriano** (circuito que passa
  por **todas as arestas** exatamente uma vez).
- **Teorema de Euler**: um grafo conexo é Euleriano ⇔ **todos** os vértices
  têm **grau par**.
- (Se exatamente 2 vértices têm grau ímpar, há uma **trilha** Euleriana
  entre eles, mas o grafo não é Euleriano.)

## Questão 25

- Ver questão 24 (Teorema de Euler: conexo + todos os graus pares).

## Questão 26

- Ver questão 24 (Teorema de Euler: conexo + todos os graus pares).

## Questão 27

- Ver questão 24 (Teorema de Euler: conexo + todos os graus pares).

## Questão 28

- **Grafo Hamiltoniano**: possui um **ciclo Hamiltoniano** (ciclo que passa
  por **todos os vértices** exatamente uma vez e volta ao início).
- Não há um critério simples como o de Euler; normalmente se **exibe** o
  ciclo, ou se usa Dirac/Ore (questões 45 e 46).

## Questão 29

- Ver questão 16: `Kn` tem `n` vértices e `n(n − 1) / 2` arestas.

## Questão 30

- `Km,n` (questão 18): tem `m + n` vértices e `m · n` arestas
  (cada um dos `m` vértices liga-se aos `n` do outro lado).

## Questão 31

- **Ciclo `Cn`** (n ≥ 3): `n` vértices `v1, v2, …, vn` ligados em anel
  (`v1–v2, v2–v3, …, vn–v1`). Tem `n` vértices e `n` arestas.

## Questão 32

- **Cubo `Qn`**: vértices são as sequências binárias de `n` bits; dois
  vértices são adjacentes se diferem em **exatamente 1 bit**.
  Tem `2ⁿ` vértices; cada vértice tem grau `n`, logo
  `|E| = n · 2ⁿ / 2 = n · 2ⁿ⁻¹` arestas.

## Questão 33

- **Roda `Wn`**: um ciclo `Cn` + **1 vértice central** ligado a todos os `n`
  vértices do ciclo. Tem `n + 1` vértices e `2n` arestas
  (`n` do ciclo + `n` "raios").

## Questão 34

- **Lema do aperto de mãos**: `Σ deg(v) = 2·|E|` → `|E| = Σ deg(v) / 2`.
- Se o grafo não precisa ser simples, pode usar laços/arestas paralelas para
  desenhar.

## Questão 35

- Condições necessárias para um grafo **simples** com `n` vértices:
  1. A soma dos graus é **par** (aperto de mãos).
  2. Nenhum grau passa de `n − 1`.
- Para confirmar, **tente desenhar** (ou use Havel-Hakimi: remove o vértice
  de maior grau `d`, subtrai 1 dos `d` próximos maiores graus, repete;
  se chegar a tudo zero, existe).

## Questão 36

- Ver questão 35 (soma par, grau ≤ n − 1, tentar desenhar).

## Questão 37

- Ver questão 35 (soma par, grau ≤ n − 1, tentar desenhar).
- Dica: se um vértice tem grau `n − 1`, ele é vizinho de **todos** os outros,
  então nenhum outro vértice pode ter grau 0.

## Questão 38

- Ver questão 35 (soma par, grau ≤ n − 1, tentar desenhar).

## Questão 39

- **Subgrafo** (questão 22): escolha um subconjunto **não vazio** de vértices
  e, depois, um subconjunto das arestas que têm **as duas extremidades**
  entre os vértices escolhidos. Conte todas as combinações possíveis.

## Questão 40

- Ver questões 22 e 39. Organize por número de vértices (1, 2, 3, 4) e,
  para cada escolha de vértices, liste todas as escolhas de arestas válidas.

## Questão 41

- Grafo **k-regular**: todo vértice tem grau `k` (questão 8).
- Em `Kn`, cada vértice é ligado a todos os outros `n − 1` vértices.

## Questão 42

- Grafo **k-regular**: todo vértice tem grau `k`.
- Em `Cn` (n ≥ 3), cada vértice tem exatamente 2 vizinhos no anel.

## Questão 43

- Grafo **k-regular**: todo vértice tem grau `k`.
- Em `Wn` (n ≥ 3), compare o grau do vértice **central** (`n`) com o grau dos
  vértices do **ciclo** (2 do ciclo + 1 do raio).

## Questão 44

- Grafo **k-regular**: todo vértice tem grau `k`.
- Em `Qn`, cada vértice tem como vizinhos as sequências que diferem em 1 bit
  (há `n` bits para trocar).

## Questão 45

- **Teorema de Dirac**: se `G` é simples com `n ≥ 3` vértices e
  `deg(v) ≥ n/2` para **todo** vértice `v`, então `G` é Hamiltoniano.
- **Condição suficiente**: se vale, garante o resultado (mas o resultado
  pode ocorrer sem ela).
- **Condição necessária**: se o resultado ocorre, ela tem que valer.
- Dica: pense em `Cn` com n grande (é Hamiltoniano? satisfaz Dirac?).

## Questão 46

- **Teorema de Ore**: se `G` é simples com `n ≥ 3` vértices e
  `deg(u) + deg(v) ≥ n` para **todo par de vértices não adjacentes** `u, v`,
  então `G` é Hamiltoniano.
- Ver na questão 45 a diferença entre suficiente e necessária.

## Questão 47 ✓

- **Hamiltoniano**: ciclo passando por todos os vértices uma vez (questão 28).
  Dica: um vértice de **grau 1** não pode estar em nenhum ciclo.
- **Euleriano**: conexo e todos os graus pares (questão 24).

Graus: deg(v1) = 3, deg(v2) = 3, deg(v3) = 3, deg(v4) = 2, deg(v5) = 1.

- **Não é Hamiltoniano**: v5 tem grau 1 (só é ligado a v2), então não pode
  pertencer a nenhum ciclo — um ciclo exige que cada vértice tenha 2 arestas
  no ciclo (uma para entrar e outra para sair).
- **Não é Euleriano**: pelo Teorema de Euler, todos os vértices precisariam
  ter grau par, mas v1, v2, v3 e v5 têm grau ímpar.

## Questão 48

- **Classe P**: problemas de decisão que podem ser **resolvidos** em tempo
  polinomial (`O(nᵏ)`) por um algoritmo determinístico.
- **Classe NP**: problemas cuja solução pode ser **verificada** em tempo
  polinomial (dado um "certificado").
- **NP-Completo**: problema que está em NP **e** é NP-difícil (todo problema
  de NP pode ser reduzido a ele em tempo polinomial). Ex.: ciclo
  Hamiltoniano, caixeiro viajante (decisão), SAT.

## Questão 49

- **Emparelhamento (matching)** `M`: conjunto de arestas sem vértices em
  comum. **Máximo**: com o maior número possível de arestas.
- **Caminho M-alternante**: caminho cujas arestas alternam entre fora de `M`
  e dentro de `M`. **M-aumentante**: caminho alternante que começa e termina
  em vértices **não cobertos** por `M`.
- **Teorema de Berge (1957)**: `M` é máximo ⇔ não existe caminho
  M-aumentante em `G`.

## Questão 50

- **Teorema de Hall (casamento)**: seja `G` bipartido com partes `X` e `Y`.
  Existe um emparelhamento que **cobre todo `X`** ⇔ para todo subconjunto
  `S ⊆ X`, `|N(S)| ≥ |S|` (condição de Hall), onde `N(S)` é o conjunto dos
  vizinhos dos vértices de `S`.
