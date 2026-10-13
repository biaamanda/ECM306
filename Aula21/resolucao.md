# Simulado Grafos — Resolução

## Questão 1

V = {v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11} e
E = {e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15}

## Questão 2

- Arestas paralela: duas (ou mais) arestas com os mesmos vértices-extremidades.
  Considerando o grafo G, não há arestas paralelas.

## Questão 3

- Vértice isolado: não é extremidade de nenhuma aresta (grau 0).
  Considerando o grafo G, não há vértices isolados

## Questão 4

- Conjunto vizinhança `N(v)`: todos os vizinhos de `v`.
  v6: v5, v8, v9, v11
  v9:v6, v8, v10, v11

## Questão 5

- "Grafo simples" é sobre a ausência de laços/paralelas.
  Como não há laços ou paralelismo, grafo G é dito simples.

## Questão 6

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

## Questão 7

Sequência de graus: v6, v8, v9, v3, v5, v1, v2, v4, v7, v10, v11

## Questão 8

`G` é k-regular se todo vértice tem grau `k`.
Grafo G não é regular pois possui grais diferentes

## Questão 9

## Questão 10

## Questão 11

## Questão 12

## Questão 13

## Questão 14

## Questão 15

`G1=(V1,E1)` e `G2=(V2,E2)` são **isomorfos** se existem bijeções
`f: V1→V2` e `g: E1→E2` que preservam a incidência (a aresta `g(e)` liga
`f(u)` e `f(v)` sempre que `e` ligava `u` e `v`).

**Invariantes** (se G1≅G2, então G1 e G2 têm que ter): o mesmo número de
vértices, o mesmo número de arestas, e a mesma sequência de graus (mesmo
número de vértices de cada grau).

> Para **provar que NÃO são isomorfos** basta achar 1 invariante diferente.
> Para provar que **são** isomorfos, é preciso exibir as funções f e g.

G1 = V {v1, v2, v3, v4, v5, v6} e A = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10}
deg(v1) = 3
deg(v2) = 4
deg(v3) = 4
deg(v4) = 3
deg(v5) = 2
deg(v6) = 4
(2, 3, 3, 4, 4, 4)

G2 = V {v1, v2, v3, v4, v5, v6} e A = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10}
deg(v1) = 2
deg(v2) = 4
deg(v3) = 4
deg(v4) = 4
deg(v5) = 4
deg(v6) = 2
(2, 2, 4, 4, 4, 4)

G1 e G2 possuem o mesmo número de vértices e arestas, porém diferem nos graus, portanto são invariantes e não são isomorfos.

## Questão 16

## Questão 17

## Questão 18

## Questão 19

## Questão 20

- `V` pode ser particionado em dois conjuntos não vazios e disjuntos
  `X` e `Y` (`X ∪ Y = V`, `X ∩ Y = ∅`) tais que toda aresta tem uma
  extremidade em `X` e a outra em `Y` (nunca liga dois vértices do mesmo
  lado). Um grafo é bipartido se e somente se não tem ciclo de tamanho ímpar.

## Questão 21

## Questão 22

## Questão 23

## Questão 24

## Questão 25

## Questão 26

## Questão 27

## Questão 28

## Questão 29

## Questão 30

## Questão 31

## Questão 32

## Questão 33

## Questão 34

## Questão 35

## Questão 36

## Questão 37

## Questão 38

## Questão 39

## Questão 40

## Questão 41

## Questão 42

## Questão 43

## Questão 44

## Questão 45

## Questão 46

## Questão 47

## Questão 48

## Questão 49

## Questão 50
