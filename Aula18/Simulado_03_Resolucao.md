# Simulado 03 — Resolução

> Unidades 16 (hashing), 17 (teoria dos grafos) e 18 (grafos eulerianos).
> Nas questões que dependem de figura, a leitura adotada do desenho está
> explicitada; o método de resolução vale mesmo que a figura do enunciado
> seja lida de forma ligeiramente diferente.

---

## Questão 1 — Tabela hash, `h(x) = x mod 23`, 23 endereços (0..22)

Sequência de inserção: `44, 46, 49, 70, 27, 71, 90, 97, 95`

Endereços-base calculados com `h(x) = x mod 23`:

| chave x | 44 | 46 | 49 | 70 | 27 | 71 | 90 | 97 | 95 |
|--------:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| h(x)    | 21 |  0 |  3 |  1 |  4 |  2 | 21 |  5 |  3 |

Contas:
- 44 mod 23 = 21
- 46 mod 23 = 0
- 49 mod 23 = 3
- 70 mod 23 = 1
- 27 mod 23 = 4
- 71 mod 23 = 2
- 90 mod 23 = 21  → colide com 44
- 97 mod 23 = 5
- 95 mod 23 = 3   → colide com 49

### A) Conjunto das chaves envolvidas em colisões

- Endereço 21: 44 (ocupa) e 90 (colide)
- Endereço 3: 49 (ocupa) e 95 (colide)

**C = { 44, 90, 49, 95 }**

(90 e 95 são as chaves que sofreram colisão; 44 e 49 são as que já ocupavam o
endereço-base e, portanto, também estão "envolvidas" na colisão.)

### B) Tratamento por encadeamento exterior (listas encadeadas)

Cada endereço-base aponta para uma lista com as chaves ali mapeadas, na ordem
de inserção:

```
0  -> 46
1  -> 70
2  -> 71
3  -> 49 -> 95
4  -> 27
5  -> 97
6  -> (vazio)
...
20 -> (vazio)
21 -> 44 -> 90
22 -> (vazio)
```

### C) Tratamento por rehashing (endereçamento aberto)

Rehashing = ao colidir, procura-se a **próxima posição livre** varrendo a
tabela a partir de `índice + 1`, com retorno ao início (0) se necessário.

Passo a passo:

| # | chave | h(x) | posição de gravação | observação |
|--:|------:|-----:|--------------------:|------------|
| 1 | 44 | 21 | 21 | livre |
| 2 | 46 |  0 |  0 | livre |
| 3 | 49 |  3 |  3 | livre |
| 4 | 70 |  1 |  1 | livre |
| 5 | 27 |  4 |  4 | livre |
| 6 | 71 |  2 |  2 | livre |
| 7 | 90 | 21 | **22** | 21 ocupado (44) → tenta 22 (livre) |
| 8 | 97 |  5 |  5 | livre |
| 9 | 95 |  3 | **6** | 3 ocupado (49) → 4 ocupado (27) → 5 ocupado (97) → 6 livre |

Tabela final:

```
índice:  0   1   2   3   4   5   6   7..20  21  22
chave : 46  70  71  49  27  97  95   ---    44  90
```

---

## Questão 2 — Grafo G (figura)

**Leitura adotada da figura** (V com 11 vértices, E com 15 arestas):

| aresta | extremos |
|--------|----------|
| e1  | {v1, v3} |
| e2  | {v1, v2} |
| e3  | {v2, v3} |
| e4  | {v3, v4} |
| e5  | {v4, v5} |
| e6  | {v5, v6} |
| e7  | {v5, v8} |
| e8  | {v6, v8} |
| e9  | {v7, v8} |
| e10 | {v7, v10} |
| e11 | {v6, v9} |
| e12 | {v9, v10} |
| e13 | {v9, v11} |
| e14 | {v6, v11} |
| e15 | {v8, v9} |

**Conjuntos que constituem G:**

- **V** = { v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11 }   (|V| = 11)
- **E** = { e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15 }   (|E| = 15)

com E = { {v1,v3}, {v1,v2}, {v2,v3}, {v3,v4}, {v4,v5}, {v5,v6}, {v5,v8},
{v6,v8}, {v7,v8}, {v7,v10}, {v6,v9}, {v9,v10}, {v9,v11}, {v6,v11}, {v8,v9} }.

### A) Há arestas paralelas?

**Não.** Arestas paralelas (ou múltiplas) são duas ou mais arestas distintas
com o **mesmo par de extremos**. Em G cada par de vértices é ligado por, no
máximo, uma aresta; logo não há arestas paralelas.

### B) Há vértices isolados?

**Não.** Um vértice isolado tem grau 0 (nenhuma aresta incidente). Em G todos
os vértices têm grau ≥ 2 (ver item E), portanto nenhum é isolado.

### C) Vizinhança de v6 e de v9

- **N(v6) = { v5, v8, v9, v11 }**  (arestas e6, e8, e11, e14)
- **N(v9) = { v6, v8, v10, v11 }** (arestas e11, e15, e12, e13)

### D) G é simples?

**Sim.** Um grafo é simples quando **não possui laços** (aresta de um vértice
para ele mesmo) **nem arestas paralelas**. G não tem laços e, pelo item A, não
tem arestas paralelas.

### E) Grau de todos os vértices

| v | v1 | v2 | v3 | v4 | v5 | v6 | v7 | v8 | v9 | v10 | v11 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|----:|----:|
| d(v) | 2 | 2 | 3 | 2 | 3 | 4 | 2 | 4 | 4 | 2 | 2 |

Verificação (lema do aperto de mãos): Σ d(v) = 2+2+3+2+3+4+2+4+4+2+2 = **30 = 2·|E| = 2·15**. ✔

### F) Sequência de graus

Em ordem não-crescente:

**(4, 4, 4, 3, 3, 2, 2, 2, 2, 2, 2)**

### G) G é regular?

**Não.** Um grafo é regular quando todos os vértices têm o mesmo grau. Em G há
vértices de grau 2, 3 e 4, portanto G não é regular.

---

## Questão 3 — Dois grafos cúbicos G1 e G2

Grafo **cúbico** = 3-regular (todo vértice tem grau exatamente 3). Como Σd = 3n
deve ser par, n precisa ser par.

**G1 = K4** (grafo completo com 4 vértices):

```
      a
     /|\
    b-+-c        arestas: ab, ac, ad, bc, bd, cd
     \|/         cada vértice tem grau 3
      d
```
V = {a,b,c,d}, E = {ab, ac, ad, bc, bd, cd}. 4 vértices, 6 arestas, 3-regular.

**G2 = prisma triangular** (dois triângulos ligados por 3 arestas):

```
    a-------d
   / \     / \
  b---c   e---f      arestas dos triângulos: abc  (ab, bc, ca)
   \   \ /   /                                 def  (de, ef, fd)
    \   X   /        arestas de ligação:       ad, be, cf
     b---c
```
V = {a,b,c,d,e,f}, E = {ab, bc, ca, de, ef, fd, ad, be, cf}. 6 vértices,
9 arestas, 3-regular.

(Outras opções válidas: K3,3 e o grafo do cubo Q3.)

---

## Questão 4 — Grafo simples com 15 vértices, todos de grau 5?

**Não pode existir.**

Pelo **lema do aperto de mãos**, Σ d(v) = 2·|E|, ou seja, a soma dos graus é
sempre **par**. Aqui Σ d(v) = 15 × 5 = **75**, que é ímpar. Contradição.
(Equivalente: o número de vértices de grau ímpar deve ser par, e teríamos 15
vértices de grau ímpar.)

---

## Questão 5 — Grafo simples com 10 vértices, todos de grau 3?

**Pode existir.**

- Σ d(v) = 10 × 3 = 30 (par) → |E| = 15. Não viola o aperto de mãos.
- Grau máximo 3 ≤ n − 1 = 9. Sem impedimento.
- Existe construção concreta: o **grafo de Petersen** é 3-regular com 10
  vértices. Outro exemplo simples: dispor 10 vértices em ciclo (C10) e
  acrescentar as 5 "cordas" ligando vértices diametralmente opostos
  (i com i+5) — grafo de Möbius–Kantor / escada circular, 3-regular.

---

## Questão 6 — Grafo de intersecção dos conjuntos

```
A1 = {0,2,4,6,8}
A2 = {0,1,2,3,4}
A3 = {1,3,5,7,9}
A4 = {5,6,7,8,9}
A5 = {0,1,8,9}
```

Testando todas as intersecções (aresta ⇔ intersecção não vazia):

| par | intersecção | aresta? |
|-----|-------------|:------:|
| A1,A2 | {0,2,4} | sim |
| A1,A3 | ∅ (par × ímpar) | **não** |
| A1,A4 | {6,8} | sim |
| A1,A5 | {0,8} | sim |
| A2,A3 | {1,3} | sim |
| A2,A4 | ∅ | **não** |
| A2,A5 | {0,1} | sim |
| A3,A4 | {5,7,9} | sim |
| A3,A5 | {1,9} | sim |
| A4,A5 | {8,9} | sim |

**V** = {A1, A2, A3, A4, A5}
**E** = { {A1,A2}, {A1,A4}, {A1,A5}, {A2,A3}, {A2,A5}, {A3,A4}, {A3,A5}, {A4,A5} }   (8 arestas)

Desenho (só faltam as diagonais A1–A3 e A2–A4):

```
        A1
       /  \  \
     A2    \   \
    / |     \    A4
  A3  |      \  /  \
    \ |       \/    |
     \|       /\    |
      A5-----/  ----+
      (A5 liga-se a A1, A2, A3, A4)
```

Graus: d(A1)=3, d(A2)=3, d(A3)=3, d(A4)=3, d(A5)=4. Σ = 16 = 2·8. ✔
(É o K5 menos as arestas A1A3 e A2A4.)

---

## Questão 7 — G1 com 10 vértices e G2 com 11 vértices podem ser isomorfos?

**Não.** Um isomorfismo é uma **bijeção** f: V(G1) → V(G2) que preserva
adjacências. Uma bijeção só existe entre conjuntos de mesma cardinalidade.
Como |V(G1)| = 10 ≠ 11 = |V(G2)|, não há bijeção e os grafos não podem ser
isomorfos. (Número de vértices é invariante de isomorfismo.)

---

## Questão 8 — G1 com 5 arestas e G2 com 6 arestas podem ser isomorfos?

**Não.** O número de arestas é **invariante** por isomorfismo: se f preserva
adjacência nos dois sentidos, ela induz uma bijeção entre E(G1) e E(G2). Como
|E(G1)| = 5 ≠ 6 = |E(G2)|, os grafos não são isomorfos.

---

## Questão 9 — G1 e G2 da figura são isomorfos?

**Método:** comparar invariantes — nº de vértices, nº de arestas e sequência
de graus; se todos coincidirem, tentar exibir a bijeção.

**Leitura adotada da figura:**

- **G1**: V = {v1,v2,v3,v4,v5,v6}. O quadrado v1–v3–v4–v2 com as duas diagonais
  encontrando-se no vértice central v6, o vértice v5 subdividindo o lado
  superior (v1–v5–v3) e a aresta "curva" externa v2–v3.
  E(G1) = { v1v5, v5v3, v3v4, v4v2, v2v1, v1v6, v6v4, v2v6, v6v3, v2v3 } → 10 arestas.
  Graus: d(v1)=3, d(v2)=4, d(v3)=4, d(v4)=3, d(v5)=2, d(v6)=4.
  **Sequência de graus de G1: (4, 4, 4, 3, 3, 2)**

- **G2**: V = {v1,v2,v3,v4,v5,v6}. Triângulo superior v1–v2–v4; laterais
  v2–v3 e v4–v5; as diagonais cruzadas v2–v5 e v3–v4; a base v3–v5; e o
  triângulo inferior v3–v6–v5.
  E(G2) = { v1v2, v1v4, v2v4, v2v3, v4v5, v2v5, v3v4, v3v5, v3v6, v5v6 } → 10 arestas.
  Graus: d(v1)=2, d(v2)=4, d(v3)=4, d(v4)=4, d(v5)=4, d(v6)=2.
  **Sequência de graus de G2: (4, 4, 4, 4, 2, 2)**

**Conclusão: G1 e G2 NÃO são isomorfos.** Ambos têm 6 vértices e 10 arestas,
mas as sequências de graus diferem: G1 tem dois vértices de grau 3 e um de
grau 2, enquanto G2 tem quatro vértices de grau 4 e dois de grau 2. Como a
sequência de graus é invariante de isomorfismo, não existe bijeção que preserve
adjacências.

> Observação: se, na sua cópia, o vértice v5 de G1 estiver **isolado** (sem
> arestas), a conclusão é a mesma e ainda mais direta — G1 teria um vértice de
> grau 0 e G2 não.

---

## Questão 10 — Arestas de K7 e de K10

Em Kn (grafo completo) cada par de vértices distintos é ligado por **exatamente
uma** aresta, logo:

$$|E(K_n)| = \binom{n}{2} = \frac{n(n-1)}{2}$$

- **K7**:  7·6/2 = **21 arestas**
- **K10**: 10·9/2 = **45 arestas**

(Também: em Kn todo vértice tem grau n−1; Σd = n(n−1) = 2|E| ⇒ |E| = n(n−1)/2.)

---

## Questão 11 — Grafo K3,5

Bipartido completo: parte X = {x1, x2, x3}, parte Y = {y1, y2, y3, y4, y5}.
**Toda** aresta liga um vértice de X a um de Y; nenhuma aresta dentro de X ou
dentro de Y. Total: 3 × 5 = **15 arestas**.

```
      x1        x2        x3
     /|\ \     /|X|\     / /|\
    / | \  \  / X X \  / /  | \
  y1  y2  y3  y4  y5   (cada xi ligado a y1..y5)
```

Lista de adjacência:
- x1 — y1, y2, y3, y4, y5
- x2 — y1, y2, y3, y4, y5
- x3 — y1, y2, y3, y4, y5

Graus: cada xi tem grau 5; cada yj tem grau 3.

---

## Questão 12 — Grafo K3,4

Bipartido completo: X = {x1, x2, x3}, Y = {y1, y2, y3, y4}.
Total: 3 × 4 = **12 arestas**.

```
      x1        x2        x3
     /||\\     ...       //||\
  y1  y2  y3  y4   (cada xi ligado a y1..y4)
```

Lista de adjacência:
- x1 — y1, y2, y3, y4
- x2 — y1, y2, y3, y4
- x3 — y1, y2, y3, y4

Graus: cada xi tem grau 4; cada yj tem grau 3.

---

## Questão 13 — Grafo G da figura: é bipartido?

**Leitura adotada:** V = {v1, v2, v3, v4, v5},
E = { {v1,v2}, {v1,v4}, {v3,v4}, {v4,v5} }  (4 arestas).

Graus: d(v1)=2, d(v2)=1, d(v3)=1, d(v4)=3, d(v5)=1.

**Sim, G é bipartido.** G é acíclico (5 vértices, 4 arestas, conexo → é uma
árvore) e **todo grafo sem ciclos ímpares é bipartido** (árvores não têm ciclo
algum). Uma bipartição:

- **X = {v1, v3, v5}**
- **Y = {v2, v4}**

Conferindo — toda aresta tem uma ponta em X e outra em Y:
v1v2 (X–Y), v1v4 (X–Y), v3v4 (X–Y), v4v5 (Y–X). ✔

### A) Um supergrafo de G

H ⊇ G acrescentando arestas (e/ou vértices), mantendo V(G) ⊆ V(H) e E(G) ⊆ E(H):

- V(H) = {v1, v2, v3, v4, v5}
- E(H) = { {v1,v2}, {v1,v4}, {v3,v4}, {v4,v5}, **{v2,v3}**, **{v1,v3}**, **{v2,v5}** }

### B) Um subgrafo de G

H' ⊆ G removendo vértices e/ou arestas:

- V(H') = {v1, v2, v4}
- E(H') = { {v1,v2}, {v1,v4} }

(É inclusive um subgrafo induzido por {v1, v2, v4}.)

---

## Questão 14 — Grafo G da figura (multigrafo)

**Leitura adotada:** V = {V1, V2, V3, V4, V5}, com

| aresta | extremos | tipo |
|--------|----------|------|
| e1 | {V1, V2} | comum |
| e2 | {V2, V3} | paralela com e4 |
| e3 | {V2, V5} | comum |
| e4 | {V2, V3} | paralela com e2 |
| e5 | {V5, V4} | comum |
| e6 | {V3, V3} | **laço** |

Graus: d(V1)=1, d(V2)=4, d(V3)=4 (o laço e6 conta 2), d(V4)=1, d(V5)=2.

### A) Passeio aberto (vértices/arestas podem repetir, início ≠ fim)

**V1 – e1 – V2 – e2 – V3 – e4 – V2 – e3 – V5**   (começa em V1, termina em V5)

### B) Passeio fechado (início = fim)

**V2 – e2 – V3 – e4 – V2**   (sai de V2 e volta a V2)

### C) Trilha aberta (não repete arestas, início ≠ fim)

**V1 – e1 – V2 – e2 – V3 – e4 – V2 – e3 – V5 – e5 – V4**
Arestas usadas: e1, e2, e4, e3, e5 (todas distintas); começa em V1, termina em V4.

### D) Circuito (trilha fechada — não repete arestas, início = fim)

**V2 – e2 – V3 – e4 – V2**   (arestas e2 e e4 distintas, retorna a V2)
Também vale o laço: **V3 – e6 – V3**.

### E) Caminho aberto (não repete vértices, início ≠ fim)

**V4 – e5 – V5 – e3 – V2 – e1 – V1**   (vértices V4, V5, V2, V1 todos distintos)

### F) Ciclo

**Não existe ciclo** no sentido estrito (caminho fechado com ≥ 3 vértices
distintos). As únicas estruturas fechadas de G são o par de arestas paralelas
e2/e4 entre V2 e V3 (que alguns livros aceitam como "ciclo de comprimento 2"
em multigrafos) e o laço e6 em V3 ("ciclo de comprimento 1"). Ciclo simples,
com três ou mais vértices, G não possui.

---

## Questão 15 — O grafo G da figura é Euleriano?

**Leitura adotada:** V = {v1, v2, v3, v4, v5, v6},
E = { {v1,v2}, {v1,v4}, {v2,v4}, {v2,v3}, {v4,v5}, {v3,v5}, {v3,v6}, {v5,v6} }  (8 arestas).

Graus:

| v | v1 | v2 | v3 | v4 | v5 | v6 |
|---|---:|---:|---:|---:|---:|---:|
| d(v) | 2 | 3 | 3 | 3 | 3 | 2 |

**G NÃO é Euleriano.**

Um grafo conexo é Euleriano (possui **circuito** de Euler — percurso fechado
que usa cada aresta exatamente uma vez) **se, e somente se, todos os vértices
têm grau par**. Em G os vértices v2, v3, v4 e v5 têm grau 3 (ímpar).

Mais ainda: G não é nem **semi-euleriano** (não tem trilha euleriana aberta),
pois isso exigiria **exatamente 0 ou 2** vértices de grau ímpar, e aqui há
**4** vértices de grau ímpar.
