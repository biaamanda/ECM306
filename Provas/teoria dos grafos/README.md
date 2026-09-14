# Unidade 17 - Introdução à Teoria dos Grafos

Códigos-base para a prova (P3). Segue os slides do Prof. Calvetti
(`IMT-2026-ECM306-T17-Introdução à Teoria dos Grafos`) — unidade de
**conceitos iniciais** (definições, grau, isomorfismo, grafos completos/
bipartidos, subgrafo). Não entra ainda caminhos/conectividade/euleriano
(isso é a Unidade 18) nem representação em matriz/lista de adjacência
formal — os códigos aqui usam uma representação simples (lista de arestas)
só para poder calcular tudo isso na mão/no código.

---

## 1. Definição

Um grafo é `G = (V, E)`:

- **V (ou V(G))**: conjunto de **vértices** — **não pode ser vazio**;
- **E (ou E(G))**: conjunto de **arestas** — **pode ser vazio** (nesse caso G
  é chamado **grafo nulo**);
- cada aresta `e ∈ E` tem um par **não ordenado** de vértices `(u,v)`
  associado, chamados **vértices-extremidade** de `e`.

Vocabulário:

- **Vértice isolado**: não é extremidade de nenhuma aresta (grau 0).
- **Aresta paralela**: duas (ou mais) arestas com os mesmos
  vértices-extremidade.
- **Laço (loop)**: aresta cujas duas extremidades são o **mesmo** vértice.
- **Grafo simples**: **não** tem laços **nem** arestas paralelas.
- **Vértices adjacentes / vizinhos**: unidos por uma aresta.
- **Arestas adjacentes**: têm um vértice em comum.
- **Conjunto vizinhança `N(v)`**: todos os vizinhos de `v`.

---

## 2. Grau de um vértice

`d(v)` = número de arestas incidentes em `v`, **contando o laço duas vezes**
(um laço conta como as duas extremidades sendo o próprio `v`).

- grau **0** → vértice **isolado**;
- grau **1** → vértice **final**;
- **sequência de graus**: os graus de todos os vértices, em ordem
  **crescente** (com repetição).

### Teorema 1 (do aperto de mãos / handshaking)

```
Σ d(vi) = 2m         (m = número de arestas)
```

A soma dos graus de todos os vértices é sempre o **dobro** do número de
arestas — cada aresta contribui 1 grau para cada uma das suas duas
extremidades (um laço contribui os 2 para o mesmo vértice).

### Teorema 2

Em qualquer grafo, **o número de vértices de grau ímpar é sempre par**.
(Consequência direta do Teorema 1 — se a soma total é par e os vértices
pares já somam um número par, os ímpares têm que fechar em quantidade par
para a soma continuar par.)

> Pegadinha: isso **não** diz nada sobre a quantidade de vértices **pares**
> — essa pode ser par ou ímpar.

---

## 3. Grafo regular

`G` é **k-regular** se **todo** vértice tem grau `k`. Um **grafo regular**
é k-regular para algum `k`. Caso particular importante: **grafo cúbico** =
3-regular. O **grafo nulo** é 0-regular.

## 4. Grafo completo `Kn`

Grafo **simples** com `n` vértices onde **todo par** de vértices distintos
é ligado por exatamente uma aresta.

- número de arestas: `C(n,2) = n(n-1)/2`;
- `Kn` é **(n-1)-regular** (todo vértice se liga a todos os outros n-1).

## 5. Grafo bipartido

`V` pode ser particionado em dois conjuntos **não vazios e disjuntos**
`X` e `Y` (`X ∪ Y = V`, `X ∩ Y = ∅`) tais que **toda** aresta tem uma
extremidade em `X` e a outra em `Y` (nunca liga dois vértices do mesmo
lado). Equivalente (fato conhecido, fora do slide): **um grafo é bipartido
se e somente se não tem ciclo de tamanho ímpar** — na prática, dá para
testar tentando **colorir com 2 cores por BFS/DFS** (ver `GrafoBipartido.java`).

### Grafo bipartido completo `Km,n`

Todo vértice de `X` (`|X|=m`) ligado a **todo** vértice de `Y` (`|Y|=n`).

- número de arestas: `m * n`;
- `Kn,n` é **n-regular**.

## 6. Isomorfismo de grafos

`G1=(V1,E1)` e `G2=(V2,E2)` são **isomorfos** se existem bijeções
`f: V1→V2` e `g: E1→E2` que preservam a incidência (a aresta `g(e)` liga
`f(u)` e `f(v)` sempre que `e` ligava `u` e `v`).

**Invariantes** (se G1≅G2, então G1 e G2 têm que ter): o mesmo número de
vértices, o mesmo número de arestas, e a mesma sequência de graus (mesmo
número de vértices de cada grau).

> Pegadinha grande: essas condições são **necessárias, mas NÃO
> suficientes**. Dois grafos podem ter os três invariantes iguais e ainda
> **não** serem isomorfos (contraexemplo clássico do slide: dois grafos
> com sequência `(1,1,1,2,2,3)`, mas em um deles o vértice de grau 1
> adjacente ao de grau 3 tem 2 "vizinhos gêmeos" de grau 1, e no outro só
> 1 — ver `VerificaIsomorfismo.java`).
> Para **provar que NÃO são isomorfos** basta achar 1 invariante diferente.
> Para provar que **são** isomorfos, é preciso exibir as funções f e g.

## 7. Subgrafo

`G2=(V2,E2)` é **subgrafo** de `G1=(V1,E1)` se `V2 ⊆ V1`, `E2 ⊆ E1`, e toda
aresta de `E2` tem as duas extremidades em `V2` (G1 é dito **supergrafo**
de G2). Casos particulares: todo grafo é subgrafo de si mesmo; um único
vértice (sem arestas) já é um subgrafo; uma única aresta com seus dois
vértices também é um subgrafo.

---

## 8. Arquivos desta pasta

| Arquivo                        | Assunto                                                                 |
|---------------------------------|--------------------------------------------------------------------------|
| `Grafo.java`                    | Classe base (lista de arestas, aceita laço/paralelas): grau, soma de graus, sequência de graus, Teorema 1, Teorema 2, `N(v)`, adjacência de vértices/arestas, "é simples?", "é k-regular?". |
| `VerificaGrafo.java`            | Roda os Exercícios 1 a 6 do slide (conta arestas, soma de graus, vértices ímpares) — mesmos grafos do `Aula17/Grafos_Exercicios_Resolucao.md`, conferindo os resultados automaticamente. |
| `GrafoCompletoRegular.java`     | Gera `Kn`, confere `n(n-1)/2` arestas e regularidade `(n-1)`; testa se um grafo qualquer é k-regular. |
| `GrafoBipartido.java`           | Testa se um grafo é bipartido (coloração com 2 cores via BFS) e gera `Km,n` (conferindo `m*n` arestas). |
| `VerificaIsomorfismo.java`      | Confere os invariantes necessários (nº vértices, nº arestas, sequência de graus) entre dois grafos — e mostra o contraexemplo clássico onde os invariantes batem mas os grafos não são isomorfos. |
| `Subgrafo.java`                 | Verifica se `G2` é subgrafo de `G1` (`V2 ⊆ V1` e `E2 ⊆ E1`). |

Compilar e rodar (dentro da pasta):

```bash
javac *.java
java Grafo
java VerificaGrafo
java GrafoCompletoRegular
java GrafoBipartido
java VerificaIsomorfismo
java Subgrafo
```

---

## 9. Pegadinhas frequentes

- Grau conta o **laço duas vezes** — esquecer isso é o erro mais comum.
- Grafo **nulo** (E vazio) é válido; grafo com **V vazio** não existe.
- "Grafo simples" é sobre a **ausência** de laços/paralelas — não confundir
  com "grafo pequeno" ou qualquer outra noção informal de simplicidade.
- Teorema 2 fala dos **ímpares**; nada garantido sobre os pares.
- Mesma sequência de graus **não implica** isomorfismo (é necessário, não
  suficiente) — sempre cheque a estrutura de adjacência antes de afirmar
  isomorfismo.
- `Kn` tem `n(n-1)/2` arestas — não confundir com `n²` ou `n(n-1)`.
- Bipartido não é o mesmo que "grafo com 2 vértices" — é sobre conseguir
  particionar **todos** os vértices em dois lados sem aresta dentro do
  mesmo lado.
