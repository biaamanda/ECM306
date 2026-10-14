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

## Questão 9 ✓

- Grafo **cúbico**: grafo 3-regular (todo vértice tem grau 3).

**G1 = K4** (4 vértices, 6 arestas, todos os vértices com grau 3):

```mermaid
graph LR
  v1 --- v2
  v1 --- v3
  v1 --- v4
  v2 --- v3
  v2 --- v4
  v3 --- v4
```

**G2 = K3,3** (6 vértices, 9 arestas, todos os vértices com grau 3):

```mermaid
graph LR
  u1 --- w1
  u1 --- w2
  u1 --- w3
  u2 --- w1
  u2 --- w2
  u2 --- w3
  u3 --- w1
  u3 --- w2
  u3 --- w3
```

## Questão 10 ✓

- **Lema do aperto de mãos**: `Σ deg(v) = 2·|E|`. Logo a soma dos graus é
  sempre **par** (e o número de vértices de grau ímpar também é par).
- Em um grafo simples com `n` vértices, o grau máximo é `n − 1`.

**Não.** Σ deg(v) = 15 · 5 = 75, que é **ímpar**. Mas Σ deg(v) = 2·|E| é sempre
par, então esse grafo não pode existir (teria 75/2 = 37,5 arestas).

## Questão 11 ✓

- Mesmo raciocínio da questão 10: a soma dos graus precisa ser par
  (`Σ deg(v) = 2·|E|`) e `deg(v) ≤ n − 1`.

**Sim.** Σ deg(v) = 10 · 3 = 30 (par) → |E| = 30 / 2 = 15 arestas, e
3 ≤ n − 1 = 9.

Exemplo: **prisma pentagonal**. São dois ciclos C5 (v1..v5 e u1..u5) com as
arestas vi–ui ligando os dois (5 + 5 + 5 = 15 arestas). Todo vértice tem grau 3.

```mermaid
graph LR
  v1 --- v2
  v2 --- v3
  v3 --- v4
  v4 --- v5
  v5 --- v1
  u1 --- u2
  u2 --- u3
  u3 --- u4
  u4 --- u5
  u5 --- u1
  v1 --- u1
  v2 --- u2
  v3 --- u3
  v4 --- u4
  v5 --- u5
```

## Questão 12 ✓

- **Grafo de intersecção**: um vértice para cada conjunto `Ai`; existe aresta
  entre `Ai` e `Aj` (i ≠ j) se e somente se `Ai ∩ Aj ≠ ∅`.

| Par | Intersecção | Aresta? |
|---|---|---|
| A1, A2 | {0, 2, 4} | sim |
| A1, A3 | ∅ | não |
| A1, A4 | {6, 8} | sim |
| A1, A5 | {0, 8} | sim |
| A2, A3 | {1, 3} | sim |
| A2, A4 | ∅ | não |
| A2, A5 | {0, 1} | sim |
| A3, A4 | {5, 7, 9} | sim |
| A3, A5 | {1, 9} | sim |
| A4, A5 | {8, 9} | sim |

V = {A1, A2, A3, A4, A5}
E = {A1A2, A1A4, A1A5, A2A3, A2A5, A3A4, A3A5, A4A5} (8 arestas)

```mermaid
graph LR
  A1 --- A2
  A1 --- A4
  A1 --- A5
  A2 --- A3
  A2 --- A5
  A3 --- A4
  A3 --- A5
  A4 --- A5
```

## Questão 13 ✓

- Ver definição de isomorfismo na questão 15. Se dois grafos são isomorfos,
  eles têm obrigatoriamente o **mesmo número de vértices**.

**Não.** Um isomorfismo exige uma bijeção f: V1 → V2, e só existe bijeção
entre conjuntos de mesmo tamanho. Como |V1| = 10 ≠ 11 = |V2|, G1 e G2 não podem
ser isomorfos.

## Questão 14 ✓

- Ver definição de isomorfismo na questão 15. Se dois grafos são isomorfos,
  eles têm obrigatoriamente o **mesmo número de arestas**.

**Não.** Um isomorfismo exige uma bijeção g: E1 → E2, e só existe bijeção
entre conjuntos de mesmo tamanho. Como |E1| = 5 ≠ 6 = |E2|, G1 e G2 não podem
ser isomorfos.

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

## Questão 16 ✓

- **Grafo completo `Kn`**: grafo simples com `n` vértices em que todo par de
  vértices distintos é ligado por uma aresta.
  Número de arestas: `|E| = n(n − 1) / 2` (= C(n, 2), número de pares).

|E(K7)| = 7 · 6 / 2 = **21 arestas**, pois cada um dos 7 vértices se liga aos
outros 6, e cada aresta é contada duas vezes (uma em cada extremidade).

## Questão 17 ✓

- Ver questão 16: `|E(Kn)| = n(n − 1) / 2`.

|E(K10)| = 10 · 9 / 2 = **45 arestas**, pois cada um dos 10 vértices se liga aos
outros 9, e cada aresta é contada duas vezes.

## Questão 18 ✓

- **Grafo bipartido completo `Km,n`**: vértices divididos em dois grupos
  `X` (com `m` vértices) e `Y` (com `n` vértices); **cada** vértice de `X` é
  ligado a **todos** os de `Y`, e não há arestas dentro do mesmo grupo.

X = {x1, x2, x3}, Y = {y1, y2, y3, y4, y5} → 3 + 5 = 8 vértices e 3 · 5 = 15 arestas.

```mermaid
graph LR
  x1 --- y1
  x1 --- y2
  x1 --- y3
  x1 --- y4
  x1 --- y5
  x2 --- y1
  x2 --- y2
  x2 --- y3
  x2 --- y4
  x2 --- y5
  x3 --- y1
  x3 --- y2
  x3 --- y3
  x3 --- y4
  x3 --- y5
```

## Questão 19 ✓

- Ver questão 18 (`Km,n`).

X = {x1, x2, x3}, Y = {y1, y2, y3, y4} → 3 + 4 = 7 vértices e 3 · 4 = 12 arestas.

```mermaid
graph LR
  x1 --- y1
  x1 --- y2
  x1 --- y3
  x1 --- y4
  x2 --- y1
  x2 --- y2
  x2 --- y3
  x2 --- y4
  x3 --- y1
  x3 --- y2
  x3 --- y3
  x3 --- y4
```

## Questão 20 ✓

- `V` pode ser particionado em dois conjuntos não vazios e disjuntos
  `X` e `Y` (`X ∪ Y = V`, `X ∩ Y = ∅`) tais que toda aresta tem uma
  extremidade em `X` e a outra em `Y` (nunca liga dois vértices do mesmo
  lado). Um grafo é bipartido se e somente se não tem ciclo de tamanho ímpar.

E = {v1v2, v1v4, v3v4, v4v5}

**Sim, G é bipartido.** Basta tomar X = {v1, v3, v5} e Y = {v2, v4}:

- v1v2: X–Y ✓
- v1v4: X–Y ✓
- v3v4: X–Y ✓
- v4v5: Y–X ✓

Toda aresta liga um vértice de X a um de Y. (Também se pode justificar assim:
G não tem nenhum ciclo, logo não tem ciclo ímpar.)

## Questão 21 ✓

> Obs.: o enunciado diz "questão 21", mas se refere ao grafo G da questão 20.

- **Supergrafo**: `G' = (V', E')` é supergrafo de `G = (V, E)` se `V ⊆ V'` e
  `E ⊆ E'` (ou seja, G está contido em G'; G' tem tudo de G e pode ter mais).

G = (V, E), com V = {v1, v2, v3, v4, v5} e E = {v1v2, v1v4, v3v4, v4v5}.

Supergrafo G' = (V', E'), adicionando o vértice v6 e as arestas v2v3 e v5v6:

- V' = {v1, v2, v3, v4, v5, v6}
- E' = {v1v2, v1v4, v3v4, v4v5, v2v3, v5v6}

Como V ⊆ V' e E ⊆ E', G' é supergrafo de G.

```mermaid
graph LR
  v1 --- v2
  v1 --- v4
  v3 --- v4
  v4 --- v5
  v2 --- v3
  v5 --- v6
```

## Questão 22 ✓

> Obs.: o grafo G é o da questão 20.

- **Subgrafo**: `H = (V', E')` é subgrafo de `G = (V, E)` se `V' ⊆ V` e
  `E' ⊆ E`, e toda aresta de `E'` tem as duas extremidades em `V'`.

Subgrafo H = (V', E'), mantendo só v1, v2 e v4:

- V' = {v1, v2, v4}
- E' = {v1v2, v1v4}

Como V' ⊆ V, E' ⊆ E e as arestas de E' têm as extremidades em V', H é subgrafo de G.

```mermaid
graph LR
  v1 --- v2
  v1 --- v4
```

## Questão 23 ✓

- **Passeio**: sequência alternada vértice–aresta–vértice… (pode repetir
  vértices e arestas). **Aberto**: começa e termina em vértices diferentes;
  **fechado**: começa e termina no mesmo vértice.
- **Trilha**: passeio **sem repetir arestas** (vértices podem repetir).
- **Circuito**: trilha fechada (sem repetir arestas, volta ao início).
- **Caminho**: passeio **sem repetir vértices** (logo, nem arestas).
- **Ciclo**: caminho fechado (só o vértice inicial = final se repete).
  Um laço (aresta de um vértice nele mesmo) já forma um ciclo.

Arestas de G:

- e1 = V1V2
- e2 = V2V3
- e3 = V2V5
- e4 = V2V3 (paralela a e2)
- e5 = V5V4
- e6 = V3V3 (laço)

A) **Passeio aberto**: V1 e1 V2 e3 V5 e3 V2 e2 V3. A aresta e3 se repete
   (o passeio permite), e o início (V1) é diferente do fim (V3).

B) **Passeio fechado**: V1 e1 V2 e1 V1. Começa e termina em V1.

C) **Trilha aberta**: V1 e1 V2 e2 V3 e4 V2 e3 V5. Nenhuma aresta se repete
   (V2 se repete, o que a trilha permite), e V1 ≠ V5.

D) **Circuito**: V2 e2 V3 e6 V3 e4 V2. É uma trilha fechada, sem arestas
   repetidas (V3 se repete por causa do laço).

E) **Caminho aberto**: V1 e1 V2 e3 V5 e5 V4. Nenhum vértice se repete.

F) **Ciclo**: V2 e2 V3 e4 V2. É um caminho fechado de tamanho 2, formado pelas
   arestas paralelas. O laço V3 e6 V3 também é um ciclo, de tamanho 1.

## Questão 24 ✓

- **Grafo Euleriano**: possui um **circuito Euleriano** (circuito que passa
  por **todas as arestas** exatamente uma vez).
- **Teorema de Euler**: um grafo conexo é Euleriano ⇔ **todos** os vértices
  têm **grau par**.
- (Se exatamente 2 vértices têm grau ímpar, há uma **trilha** Euleriana
  entre eles, mas o grafo não é Euleriano.)

Graus: deg(v1) = 2, deg(v2) = 3, deg(v3) = 3, deg(v4) = 3, deg(v5) = 3, deg(v6) = 2.

**Não é Euleriano.** G é conexo, mas v2, v3, v4 e v5 têm grau ímpar. Pelo
Teorema de Euler, todos os vértices precisariam ter grau par.

## Questão 25 ✓

- Ver questão 24 (Teorema de Euler: conexo + todos os graus pares).

Nomeando os vértices pela posição na figura (linha / coluna):

```
a   b
c   d   e
f   g   h
```

E = {ab, ad, bd, be, cd, cf, dg, eg, eh, fg, gh} (11 arestas)

Graus: a = 2, **b = 3**, c = 2, d = 4, **e = 3**, f = 2, g = 4, h = 2.

**Não é Euleriano.** Os vértices b e e têm grau ímpar. Como são exatamente
2 vértices ímpares, G tem uma **trilha** Euleriana (de b até e), mas não um
circuito Euleriano.

## Questão 26 ✓

- Ver questão 24 (Teorema de Euler: conexo + todos os graus pares).

O grafo é o **K3,3**: cada um dos 3 vértices da esquerda está ligado aos 3 da
direita (9 arestas).

**Não é Euleriano.** Todos os 6 vértices têm grau 3 (ímpar), e o Teorema de
Euler exige que todos os graus sejam pares.

## Questão 27 ✓

- Ver questão 24 (Teorema de Euler: conexo + todos os graus pares).

Nomeando os vértices pela posição na figura:

```
a   b   c
d   e   f
g   h   i
```

E = {ab, bc, ad, bd, be, ce, de, ef, eg, dh, eh, fi, gh, hi} (14 arestas)

Graus: a = 2, b = 4, c = 2, d = 4, e = 6, f = 2, g = 2, h = 4, i = 2
(Σ = 28 = 2 · 14 ✓).

**É Euleriano.** G é conexo e todos os vértices têm grau par (Teorema de Euler).
Um circuito Euleriano possível:

a → b → c → e → b → d → e → f → i → h → e → g → h → d → a

Ele usa as 14 arestas, cada uma exatamente uma vez.

## Questão 28 ✓

- **Grafo Hamiltoniano**: possui um **ciclo Hamiltoniano** (ciclo que passa
  por **todos os vértices** exatamente uma vez e volta ao início).
- Não há um critério simples como o de Euler; normalmente se **exibe** o
  ciclo, ou se usa Dirac/Ore (questões 45 e 46).

E = {v1v2, v1v4, v2v3, v2v5, v3v4, v3v5, v4v5}

**É Hamiltoniano.** Basta exibir um ciclo que passa por todos os vértices
exatamente uma vez:

v1 → v2 → v5 → v3 → v4 → v1

(Obs.: Dirac não se aplica aqui, pois deg(v1) = 2 < 5/2. Mesmo assim, G é
Hamiltoniano, já que o ciclo foi exibido.)

## Questão 29 ✓

- Ver questão 16: `Kn` tem `n` vértices e `n(n − 1) / 2` arestas.

K8 tem **8 vértices** e 8 · 7 / 2 = **28 arestas**, pois todo par de vértices
distintos é ligado por uma aresta.

## Questão 30 ✓

- `Km,n` (questão 18): tem `m + n` vértices e `m · n` arestas
  (cada um dos `m` vértices liga-se aos `n` do outro lado).

K6,3 tem 6 + 3 = **9 vértices** e 6 · 3 = **18 arestas**, pois cada um dos 6
vértices de um lado se liga a cada um dos 3 do outro.

## Questão 31 ✓

- **Ciclo `Cn`** (n ≥ 3): `n` vértices `v1, v2, …, vn` ligados em anel
  (`v1–v2, v2–v3, …, vn–v1`). Tem `n` vértices e `n` arestas.

C5 tem **5 vértices** e **5 arestas** (v1v2, v2v3, v3v4, v4v5, v5v1). Cada
vértice tem grau 2, e Σ deg = 5 · 2 = 10 = 2 · 5 arestas.

## Questão 32 ✓

- **Cubo `Qn`**: vértices são as sequências binárias de `n` bits; dois
  vértices são adjacentes se diferem em **exatamente 1 bit**.
  Tem `2ⁿ` vértices; cada vértice tem grau `n`, logo
  `|E| = n · 2ⁿ / 2 = n · 2ⁿ⁻¹` arestas.

Q5 tem 2⁵ = **32 vértices** (sequências de 5 bits). Cada vértice tem grau 5,
logo |E| = 32 · 5 / 2 = **80 arestas**.

## Questão 33 ✓

- **Roda `Wn`**: um ciclo `Cn` + **1 vértice central** ligado a todos os `n`
  vértices do ciclo. Tem `n + 1` vértices e `2n` arestas
  (`n` do ciclo + `n` "raios").

W4 tem 4 + 1 = **5 vértices** e 4 + 4 = **8 arestas** (4 do ciclo C4 + 4 raios
ligando o centro aos vértices do ciclo).
Conferindo: o centro tem grau 4 e os 4 vértices do ciclo têm grau 3, então
Σ deg = 4 + 4 · 3 = 16 = 2 · 8.

## Questão 34 ✓

- **Lema do aperto de mãos**: `Σ deg(v) = 2·|E|` → `|E| = Σ deg(v) / 2`.
- Se o grafo não precisa ser simples, pode usar laços/arestas paralelas para
  desenhar.

|E| = (5 + 2 + 2 + 2 + 2 + 1) / 2 = 14 / 2 = **7 arestas**.

É possível até como grafo simples (6 vértices, grau máximo 5 = n − 1):
v1 ligado a todos os outros (grau 5), mais as arestas v2v3 e v4v5.

deg(v1) = 5, deg(v2) = deg(v3) = deg(v4) = deg(v5) = 2, deg(v6) = 1.

```mermaid
graph LR
  v1 --- v2
  v1 --- v3
  v1 --- v4
  v1 --- v5
  v1 --- v6
  v2 --- v3
  v4 --- v5
```

## Questão 35 ✓

- Condições necessárias para um grafo **simples** com `n` vértices:
  1. A soma dos graus é **par** (aperto de mãos).
  2. Nenhum grau passa de `n − 1`.
- Para confirmar, **tente desenhar** (ou use Havel-Hakimi: remove o vértice
  de maior grau `d`, subtrai 1 dos `d` próximos maiores graus, repete;
  se chegar a tudo zero, existe).

**Existe.** Soma = 3 + 3 + 3 + 3 + 2 = 14 (par) → 7 arestas; grau máximo 3 ≤ 4.

Construção: parte-se de K4 (v1..v4, todos com grau 3), remove-se a aresta v1v2
e liga-se v5 a v1 e a v2.

E = {v1v3, v1v4, v2v3, v2v4, v3v4, v1v5, v2v5}
deg(v1) = deg(v2) = deg(v3) = deg(v4) = 3, deg(v5) = 2.

```mermaid
graph LR
  v1 --- v3
  v1 --- v4
  v2 --- v3
  v2 --- v4
  v3 --- v4
  v1 --- v5
  v2 --- v5
```

## Questão 36 ✓

- Ver questão 35 (soma par, grau ≤ n − 1, tentar desenhar).

**Não existe.**

- A soma dos graus é 1 + 2 + 3 + 4 + 5 = 15, que é **ímpar**, contrariando
  `Σ deg(v) = 2·|E|`.
- Além disso, em um grafo simples com 5 vértices o grau máximo é 4, e há um
  vértice com grau 5.

## Questão 37 ✓

- Ver questão 35 (soma par, grau ≤ n − 1, tentar desenhar).
- Dica: se um vértice tem grau `n − 1`, ele é vizinho de **todos** os outros,
  então nenhum outro vértice pode ter grau 0.

**Não existe.** A soma é 14 (par) e o grau máximo é 4 ≤ n − 1, mas há **dois**
vértices de grau 4 = n − 1. Cada um deles é vizinho de todos os outros, então
todo vértice restante tem grau ≥ 2. Isso contradiz a existência de um vértice
de grau 1.

## Questão 38 ✓

- Ver questão 35 (soma par, grau ≤ n − 1, tentar desenhar).

**Não existe.** A soma dos graus é 3 + 4 + 3 + 4 + 3 = 17, que é **ímpar**,
contrariando `Σ deg(v) = 2·|E|`.

## Questão 39 ✓

- **Subgrafo** (questão 22): escolha um subconjunto **não vazio** de vértices
  e, depois, um subconjunto das arestas que têm **as duas extremidades**
  entre os vértices escolhidos. Conte todas as combinações possíveis.

K3: V = {a, b, c}, E = {ab, ac, bc}.

| Vértices escolhidos | Qtd. de escolhas | Arestas disponíveis | Subgrafos |
|---|---|---|---|
| 1 vértice | C(3,1) = 3 | 0 → 2⁰ = 1 | 3 · 1 = 3 |
| 2 vértices | C(3,2) = 3 | 1 → 2¹ = 2 | 3 · 2 = 6 |
| 3 vértices | C(3,3) = 1 | 3 → 2³ = 8 | 1 · 8 = 8 |

Total: 3 + 6 + 8 = **17 subgrafos**.

## Questão 40 ✓

- Ver questões 22 e 39. Organize por número de vértices (1, 2, 3, 4) e,
  para cada escolha de vértices, liste todas as escolhas de arestas válidas.

G: V = {a, b, c, d}, E = {ab, ac, ad}. Todas as arestas usam o vértice `a`,
então só há arestas no subgrafo se `a` estiver em V'.

**1 vértice** (4 subgrafos, sem arestas):
{a}, {b}, {c}, {d}

**2 vértices** (6 + 3 = 9 subgrafos):
- Sem arestas: {a,b}, {a,c}, {a,d}, {b,c}, {b,d}, {c,d}
- Com aresta: {a,b} + ab, {a,c} + ac, {a,d} + ad

**3 vértices** (4 + 4 + 4 + 1 = 13 subgrafos):
- {b,c,d}: só sem arestas (1)
- {a,b,c}: ∅, {ab}, {ac}, {ab,ac} (4)
- {a,b,d}: ∅, {ab}, {ad}, {ab,ad} (4)
- {a,c,d}: ∅, {ac}, {ad}, {ac,ad} (4)

**4 vértices** {a,b,c,d} (8 subgrafos):
∅, {ab}, {ac}, {ad}, {ab,ac}, {ab,ad}, {ac,ad}, {ab,ac,ad}

Total: 4 + 9 + 13 + 8 = **34 subgrafos**.

Exemplos desenhados (4 vértices):

```mermaid
graph LR
  a --- b
  c
  d
```

```mermaid
graph LR
  a --- b
  a --- c
  d
```

```mermaid
graph LR
  a --- b
  a --- c
  a --- d
```

## Questão 41 ✓

- Grafo **k-regular**: todo vértice tem grau `k` (questão 8).
- Em `Kn`, cada vértice é ligado a todos os outros `n − 1` vértices.

**Para todo n ≥ 1.** Em Kn, cada vértice é adjacente aos outros n − 1
vértices, logo todos têm grau n − 1: Kn é **(n − 1)-regular**.

## Questão 42 ✓

- Grafo **k-regular**: todo vértice tem grau `k`.
- Em `Cn` (n ≥ 3), cada vértice tem exatamente 2 vizinhos no anel.

**Para todo n ≥ 3** (valores em que Cn está definido). Cada vértice do ciclo
é ligado ao anterior e ao seguinte, logo todos têm grau 2: Cn é **2-regular**.

## Questão 43 ✓

- Grafo **k-regular**: todo vértice tem grau `k`.
- Em `Wn` (n ≥ 3), compare o grau do vértice **central** (`n`) com o grau dos
  vértices do **ciclo** (2 do ciclo + 1 do raio).

**Só para n = 3.** Em Wn, o centro tem grau n e cada vértice do ciclo tem
grau 3 (2 do ciclo + 1 raio). Para ser regular, é preciso n = 3. Nesse caso,
W3 = K4, que é 3-regular. Para n ≥ 4, o centro tem grau n > 3, e Wn não é
regular.

## Questão 44 ✓

- Grafo **k-regular**: todo vértice tem grau `k`.
- Em `Qn`, cada vértice tem como vizinhos as sequências que diferem em 1 bit
  (há `n` bits para trocar).

**Para todo n ≥ 1.** Cada vértice de Qn é uma sequência de n bits, e seus
vizinhos são as sequências obtidas trocando exatamente 1 dos n bits. Logo
todo vértice tem grau n: Qn é **n-regular**.

## Questão 45 ✓

- **Teorema de Dirac**: se `G` é simples com `n ≥ 3` vértices e
  `deg(v) ≥ n/2` para **todo** vértice `v`, então `G` é Hamiltoniano.
- **Condição suficiente**: se vale, garante o resultado (mas o resultado
  pode ocorrer sem ela).
- **Condição necessária**: se o resultado ocorre, ela tem que valer.
- Dica: pense em `Cn` com n grande (é Hamiltoniano? satisfaz Dirac?).

**Suficiente, mas não necessária.** Se todo vértice tem grau ≥ n/2, G é
Hamiltoniano. A recíproca, porém, não vale.

Contraexemplo: **C6** é Hamiltoniano (o próprio ciclo passa por todos os
vértices), mas cada vértice tem grau 2 < 6/2 = 3. Ou seja, G é Hamiltoniano
sem satisfazer a condição de Dirac.

## Questão 46 ✓

- **Teorema de Ore**: se `G` é simples com `n ≥ 3` vértices e
  `deg(u) + deg(v) ≥ n` para **todo par de vértices não adjacentes** `u, v`,
  então `G` é Hamiltoniano.
- Ver na questão 45 a diferença entre suficiente e necessária.

**Suficiente, mas não necessária.** Se a condição de Ore vale, G é Hamiltoniano.
A recíproca, porém, não vale.

Contraexemplo: **C5** é Hamiltoniano, mas dois vértices não adjacentes têm
deg(u) + deg(v) = 2 + 2 = 4 < 5 = n. Ou seja, G é Hamiltoniano sem satisfazer
a condição de Ore.

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

## Questão 48 ✓

- **Classe P**: problemas de decisão que podem ser **resolvidos** em tempo
  polinomial (`O(nᵏ)`) por um algoritmo determinístico.
- **Classe NP**: problemas cuja solução pode ser **verificada** em tempo
  polinomial (dado um "certificado").
- **NP-Completo**: problema que está em NP **e** é NP-difícil (todo problema
  de NP pode ser reduzido a ele em tempo polinomial). Ex.: ciclo
  Hamiltoniano, caixeiro viajante (decisão), SAT.

**P**: o problema pode ser *resolvido* por um algoritmo em tempo polinomial no
tamanho da entrada. Na prática, é considerado "tratável". Ex.: decidir se um
grafo é Euleriano (basta checar conexidade e graus) e caminho mínimo (Dijkstra).

**NP-Completo**: o problema está em NP (uma solução proposta pode ser
*verificada* em tempo polinomial) **e** todo problema de NP se reduz a ele em
tempo polinomial. Ou seja, está entre os problemas mais difíceis de NP. Não se
conhece algoritmo polinomial para nenhum deles. Se algum NP-Completo fosse
resolvido em tempo polinomial, então P = NP. Ex.: decidir se um grafo é
Hamiltoniano. É por isso que não existe um teste simples, como o de Euler, para
ciclos Hamiltonianos.

## Questão 49 ✓

- **Emparelhamento (matching)** `M`: conjunto de arestas sem vértices em
  comum. **Máximo**: com o maior número possível de arestas.
- **Caminho M-alternante**: caminho cujas arestas alternam entre fora de `M`
  e dentro de `M`. **M-aumentante**: caminho alternante que começa e termina
  em vértices **não cobertos** por `M`.
- **Teorema de Berge (1957)**: `M` é máximo ⇔ não existe caminho
  M-aumentante em `G`.

**Teorema de Berge**: um emparelhamento M de G é **máximo** se e somente se
**não existe** caminho M-aumentante em G.

**Importância**: dá um critério de parada e, ao mesmo tempo, um método para
encontrar o emparelhamento máximo:

1. Comece com um emparelhamento qualquer (até vazio).
2. Procure um caminho M-aumentante P.
3. Se existir, troque as arestas de P (as que estão em M saem, as que não
   estão entram). O novo emparelhamento tem 1 aresta a mais.
4. Repita até não haver caminho aumentante. Pelo teorema, M é máximo.

É a base de algoritmos como o Húngaro, o de Hopcroft-Karp e o de Edmonds.

## Questão 50 ✓

- **Teorema de Hall (casamento)**: seja `G` bipartido com partes `X` e `Y`.
  Existe um emparelhamento que **cobre todo `X`** ⇔ para todo subconjunto
  `S ⊆ X`, `|N(S)| ≥ |S|` (condição de Hall), onde `N(S)` é o conjunto dos
  vizinhos dos vértices de `S`.

**Teorema de Hall**: em um grafo bipartido G com partes X e Y, existe um
emparelhamento que cobre todos os vértices de X se e somente se, para todo
S ⊆ X, |N(S)| ≥ |S|. Isto é, qualquer grupo de vértices de X tem, juntos,
pelo menos tantos vizinhos quanto o tamanho do grupo.

**Importância**: dá uma condição **necessária e suficiente** para saber se é
possível atribuir cada elemento de X a um elemento distinto de Y (ex.: pessoas
a tarefas, candidatos a vagas), sem precisar testar todos os emparelhamentos.
Para provar que **não** existe tal emparelhamento, basta exibir um S ⊆ X com
|N(S)| < |S|. Uma consequência: todo grafo bipartido k-regular (k ≥ 1) tem
emparelhamento perfeito.
