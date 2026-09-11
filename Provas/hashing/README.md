# Unidade 16 - Hashing

Códigos-base para a prova (P3). Segue os slides do Prof. Calvetti
(`IMT-2026-ECM306-T16-Hashing`) e os exercícios da Aula 16.

---

## 1. O problema (por que hashing existe)

- Um **array associativo** (acesso direto) mapeia cada chave `k` direto no
  índice `k` do array: `A[k]`. Acesso é `O(1)`.
- Só funciona bem quando o **universo de chaves `U`** é pequeno (ex.: matrícula
  de 2 dígitos, `U = {0..99}`).
- Quando `U` é gigante (ex.: CPF, 11 dígitos) mas o número de chaves **reais**
  usadas `K` é pequeno (`|K| << |U|`), alocar um array do tamanho de `U` é
  inviável (memória demais, quase tudo vazio).
- **Hashing** resolve isso: usa uma **função hash** para mapear o universo
  grande `U` num array pequeno de `m` posições (slots).

## 2. Mapa / Dicionário

Um **mapa (map/dicionário)** é um conjunto de pares `(chave, valor)`, onde cada
chave tem um valor associado. Pode ser implementado por **array associativo**
ou por **tabela hash**.

## 3. Função hash (método da divisão)

```
h(k) = k mod m
```

- `h : U -> {0, 1, ..., m-1}` — mapeia qualquer chave `k` do universo para um
  slot da tabela `T[0..m-1]`.
- `m` é o tamanho da tabela.
- Com array puro, a chave `k` é mapeada para o slot `A[k]`.
  Com tabela hash, a chave `k` é mapeada para o slot `T[h(k)]`.
- **Dica geral (fora do slide, mas cai bastante):** evitar `m` potência de 2;
  `m` primo tende a espalhar melhor as chaves e reduzir padrões de colisão.

### Fator de carga

```
alpha = n / m
```
onde `n` é o número de chaves armazenadas e `m` o tamanho da tabela. Quanto
mais próximo de 1 (ou maior), mais colisões tendem a ocorrer.

## 4. Colisão

**Colisão** ocorre quando duas chaves diferentes produzem o mesmo valor de
hash: `h(k1) = h(k2)` com `k1 != k2`. É inevitável (mesmo com boa função hash)
sempre que `|K| > m`. Duas técnicas de tratamento:

### 4.1 Encadeamento (chaining)

- Cada slot da tabela guarda a **cabeça de uma lista ligada** com todas as
  chaves que colidiram naquele índice.
- **Inserir** na cabeça da lista: `O(1)` no pior caso.
  (Se inserir no fim, para preservar a ordem de chegada, o custo passa a ser
  proporcional ao tamanho da lista.)
- **Buscar / Remover**: no pior caso é preciso percorrer toda a lista do slot
  -> `O(tamanho da lista)`. Em média, com boa função hash, a lista é curta.

### 4.2 Endereçamento aberto / Rehashing

- Todos os elementos ficam **na própria tabela** (sem listas externas).
- Quando há colisão em `h(k)`, procura-se sistematicamente outro slot livre.
- **Sondagem linear (linear probing):** tenta `h(k)`, depois `h(k)+1`,
  `h(k)+2`, ... voltando ao início (`mod m`) até achar um slot livre.
- **Remoção é delicada:** não dá para simplesmente esvaziar o slot, porque
  isso quebraria a cadeia de sondagem de outras chaves que passaram por ali.
  Usa-se um marcador especial de **"removido" (tombstone)**, distinto de
  "nunca usado", e a busca continua através de slots marcados como removidos.

## 5. Complexidades (resumo)

| Estrutura                    | Inserir      | Buscar / Remover        |
|-------------------------------|--------------|--------------------------|
| Array associativo (direto)    | O(1)         | O(1)                     |
| Tabela hash com encadeamento  | O(1)*        | pior caso O(n), média O(1+alpha) |
| Tabela hash c/ endereç. aberto| depende de alpha (pior caso O(n) se a tabela estiver quase cheia) | idem |

`*` O(1) se inserir na cabeça da lista.

## 6. Arquivos desta pasta

| Arquivo                             | Assunto                                                                 |
|--------------------------------------|--------------------------------------------------------------------------|
| `ArrayAssociativo.java`              | Acesso direto (sem hash): `A[chave]`. Mostra quando é viável e quando não é (CPF). |
| `FuncaoHash.java`                    | Função hash `h(k) = k mod m`, detecção de colisão **sem** tratamento (dado se sobrescreve), fator de carga. |
| `TabelaHashEncadeamento.java`        | Tabela hash com **encadeamento**: inserir/buscar/remover/mostrar + contagem de acessos (nº de comparações para achar cada chave). |
| `TabelaHashAberto.java`              | Tabela hash com **endereçamento aberto** (sondagem linear): inserir/buscar/remover (com tombstone) + relatório de colisões. |
| `SimulaEnderecamentoAberto.java`     | Passo a passo da sondagem linear, mostrando o array a cada inserção (estilo "trace" de prova). |
| `VerificaTabelaHash.java`            | Dado um conjunto de chaves e `m`, calcula `h(k)` para cada uma e aponta quais colidem — útil para responder rápido "calcule o índice hash de...". |

Compilar e rodar (dentro da pasta):

```bash
javac *.java
java ArrayAssociativo
java FuncaoHash
java TabelaHashEncadeamento
java TabelaHashAberto
java SimulaEnderecamentoAberto
java VerificaTabelaHash
```

---

## 7. Pegadinhas frequentes

- Hashing **não** mantém os dados ordenados (diferente de árvore de busca).
- Duas chaves com o **mesmo hash não são iguais** — sempre confira a chave
  armazenada no slot (ou na lista) antes de dizer "achei".
- `O(1)` do hashing é **esperado/médio**, não garantido — no pior caso
  (muitas colisões) pode virar `O(n)`.
- Em **endereçamento aberto**, remover um elemento **não pode** deixar o slot
  simplesmente vazio — quebra a busca de outras chaves que sondaram por ali.
  Precisa de marcador de "removido".
- Em **encadeamento**, a tabela nunca "enche" de verdade (as listas crescem);
  em **endereçamento aberto**, a tabela tem um limite rígido de `m` elementos.
- Array associativo só compensa quando `|U|` é pequeno; quando `|U| >> |K|`
  (ex.: CPF), a solução é hashing.
