# Unidade 17 — Introdução à Teoria dos Grafos — Exercícios resolvidos

> Slides "Grafos - Exemplo" (Exercícios 1 a 6) do material
> *IMT-2026-ECM306-T17-Introdução à Teoria dos Grafos - Prof. Calvetti*.
> Os slides já trazem a resposta numérica de cada item; abaixo está o
> **porquê** de cada resultado — a leitura do grafo (vértices, arestas,
> laços e arestas paralelas) e o cálculo completo dos graus.

Dois resultados usados em todos os exercícios:

- **Teorema 1 (aperto de mãos):** Σ d(v) = 2·|E| — a soma dos graus de
  todos os vértices é sempre o dobro do número de arestas (cada aresta
  contribui com 1 grau para cada uma das suas duas extremidades; um
  **laço** contribui com **2** para o grau do seu único vértice).
- **Teorema 2:** em qualquer grafo, o número de vértices de grau ímpar é
  sempre **par** (consequência direta do Teorema 1).

---

## Exercício 1 (e Exercício 4, mesmo grafo)

Grafo: V = {v1, v2, v3, v4, v5}, com as arestas rotuladas e1..e8:

| aresta | extremos | observação |
|--------|----------|------------|
| e1 | {v1, v5} | |
| e2 | {v1, v2} | paralela com e3 |
| e3 | {v1, v2} | paralela com e2 |
| e4 | {v5, v4} | |
| e5 | {v2, v4} | |
| e6 | {v2, v3} | |
| e7 | {v4, v3} | |
| e8 | {v3, v3} | **laço** |

### Exercício 1

**a) Quantidade de arestas:** conta-se e1 a e8 → **8 arestas**.

**b) Soma dos graus:**

| v | incidências | grau |
|---|-------------|-----:|
| v1 | e1, e2, e3 | 3 |
| v2 | e2, e3, e5, e6 | 4 |
| v3 | e6, e7, e8 (laço conta 2) | 4 |
| v4 | e4, e5, e7 | 3 |
| v5 | e1, e4 | 2 |

Soma = 3 + 4 + 4 + 3 + 2 = **16** = 2 × 8 arestas ✔ (Teorema 1)

### Exercício 4 — vértices ímpares (mesmo grafo)

Graus: v1=3 (ímpar), v2=4 (par), v3=4 (par), v4=3 (ímpar), v5=2 (par).

**Ímpares = { v1, v4 } → 2 vértices ímpares** (e 3 pares: v2, v3, v5).
Confere com o Teorema 2: 2 é par.

---

## Exercício 2 (e Exercício 5, mesmo grafo)

Grafo: V = {V1, ..., V9}. Arestas:

- V1–V2
- V1–V4 (duas arestas paralelas — o "fuso" ao lado de V1V4)
- V2–V3
- V2–V7
- V5–V7
- V5–V6
- V6–V7
- V3–V8
- V8–V9 (duas arestas paralelas)

### Exercício 2

**a) Quantidade de arestas:** V1V2(1) + V1V4×2(2) + V2V3(1) + V2V7(1) +
V5V7(1) + V5V6(1) + V6V7(1) + V3V8(1) + V8V9×2(2) = **11 arestas**.

**b) Soma dos graus:**

| v | incidências | grau |
|---|-------------|-----:|
| V1 | V2, V4, V4 | 3 |
| V2 | V1, V3, V7 | 3 |
| V3 | V2, V8 | 2 |
| V4 | V1, V1 | 2 |
| V5 | V7, V6 | 2 |
| V6 | V5, V7 | 2 |
| V7 | V2, V5, V6 | 3 |
| V8 | V3, V9, V9 | 3 |
| V9 | V8, V8 | 2 |

Soma = 3+3+2+2+2+2+3+3+2 = **22** = 2 × 11 arestas ✔

### Exercício 5 — vértices ímpares (mesmo grafo)

Graus ímpares: V1(3), V2(3), V7(3), V8(3).

**Ímpares = { V1, V2, V7, V8 } → 4 vértices ímpares** (e 5 pares: V3, V4,
V5, V6, V9). 4 é par ✔.

---

## Exercício 3 (e Exercício 6, mesmo grafo)

Grafo: V = {V1, ..., V9}. É o grafo do Exercício 2 acrescido de dois
laços e arestas paralelas extras:

- **laço em V1**
- **laço em V2**
- V1–V2
- V1–V4 (duas arestas paralelas)
- V2–V7 (**duas** arestas paralelas — diferença em relação ao Ex. 2)
- V5–V7
- V5–V6 (**duas** arestas paralelas — diferença em relação ao Ex. 2)
- V6–V7
- V2–V3
- V3–V8
- V8–V9 (duas arestas paralelas)
- **laço em V9**

### Exercício 3

**a) Quantidade de arestas:** 2 laços + V1V2(1) + V1V4×2(2) + V2V7×2(2) +
V5V7(1) + V5V6×2(2) + V6V7(1) + V2V3(1) + V3V8(1) + V8V9×2(2) + 1 laço
= 2+1+2+2+1+2+1+1+1+2+1 = **16 arestas**.

**b) Soma dos graus** (laço conta 2 para o grau do seu vértice):

| v | incidências | grau |
|---|-------------|-----:|
| V1 | laço(2) + V2(1) + V4×2(2) | 5 |
| V2 | laço(2) + V1(1) + V7×2(2) + V3(1) | 6 |
| V3 | V2, V8 | 2 |
| V4 | V1, V1 | 2 |
| V5 | V7, V6×2 | 3 |
| V6 | V5×2, V7 | 3 |
| V7 | V2×2, V5, V6 | 4 |
| V8 | V3, V9×2 | 3 |
| V9 | V8×2, laço(2) | 4 |

Soma = 5+6+2+2+3+3+4+3+4 = **32** = 2 × 16 arestas ✔

### Exercício 6 — vértices ímpares (mesmo grafo)

Graus ímpares: V1(5), V5(3), V6(3), V8(3).

**Ímpares = { V1, V5, V6, V8 } → 4 vértices ímpares** (e 5 pares: V2, V3,
V4, V7, V9). 4 é par ✔.

---

## Resumo

| Exercício | Arestas | Σ graus | Vértices ímpares |
|:---------:|:-------:|:-------:|-------------------|
| 1 | 8  | 16 | — |
| 2 | 11 | 22 | — |
| 3 | 16 | 32 | — |
| 4 (grafo do Ex.1) | — | — | {v1, v4} → 2 |
| 5 (grafo do Ex.2) | — | — | {V1, V2, V7, V8} → 4 |
| 6 (grafo do Ex.3) | — | — | {V1, V5, V6, V8} → 4 |

Em todos os casos, Σ graus = 2·|E| (Teorema 1) e a quantidade de vértices
de grau ímpar é par (Teorema 2), como esperado para qualquer grafo.
