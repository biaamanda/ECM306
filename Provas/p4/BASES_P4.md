# Bases para a prova — o que usar e o que alterar

Cada base é um arquivo `.java` **completo, que compila e roda sozinho**, dentro de [bases/](bases/). Todas foram testadas com os casos das provas antigas.
Nos arquivos, procure **`ALTERE`**: são os únicos pontos que normalmente mudam de uma questão para outra.

---

## 1. Qual base usar?

| Se a questão pedir… | Base | Classe com `main` |
|---|---|---|
| alocar / atribuir / distribuir (professores × dias, casais × quartos, ônibus × vagas, equipes × chamados) | [bases/emparelhamento/BaseEmparelhamento.java](bases/emparelhamento/BaseEmparelhamento.java) | `BaseEmparelhamento` |
| fila de prioridade, senhas, "o mais urgente primeiro", botões | [bases/heap/BaseHeap.java](bases/heap/BaseHeap.java) | `BaseHeap` |
| tabela hash, colisões, "buscar em N tentativas" | [bases/hash/BaseHash.java](bases/hash/BaseHash.java) | `BaseHash` |
| graus, conexo, Euler, bipartido, BFS, DFS, Dijkstra, ordem de tarefas, Hamilton, isomorfismo | [bases/grafo/BaseGrafo.java](bases/grafo/BaseGrafo.java) | `BaseGrafo` |
| força bruta, divisão e conquista, MergeSort, Fibonacci com PD, guloso | [bases/tecnicas/BaseTecnicas.java](bases/tecnicas/BaseTecnicas.java) | `BaseTecnicas` |

### Roteiro na prova
1. **Copie a pasta da base** para a pasta da questão (ex.: `Questao6/`).
2. **Renomeie o arquivo e a classe `public`** se quiser (ex.: `BaseEmparelhamento` → `Horario`). O nome do arquivo tem que ser **igual** ao da classe `public`.
3. **Ajuste os pontos `ALTERE`** (tabelas abaixo).
4. **Apague o que a questão não pede.** Exemplo: em `BaseGrafo`, deixe só o algoritmo pedido.
5. **Compile, gere o .jar e rode todos os casos de teste** (passo a passo no início do [TEORIA_P4.md](TEORIA_P4.md)):
   ```powershell
   javac --release 8 -Xlint:-options *.java
   ```
   ```powershell
   jar cfe NomeDoJar.jar ClasseComMain *.class
   ```
   ```powershell
   java -jar NomeDoJar.jar
   ```

---

## 2. Emparelhamento

### Pseudocódigo
```
para cada vertice e da ESQUERDA:
    visitado = tudo falso
    se tenta(e): total++

tenta(e):
    para cada recurso d que e aceita:
        se d ja foi visitado: pula
        marca d como visitado
        se d esta livre  OU  tenta(dono atual de d) conseguir outro recurso:
            d passa a ser de e
            retorna verdadeiro
    retorna falso

se total == tamanho da esquerda: mostra os pares
senao: "sem solucao"   (Hall: algum grupo tem menos opcoes que pessoas)
```

### O que alterar
| Onde (`ALTERE`) | Exemplo: professores × dias (P4 2024) | Exemplo: casais × quartos 1–6 |
|---|---|---|
| `ESQUERDA` | `{"P1","P2","P3","P4","P5"}` | `{"A","B","C","D","E","F"}` |
| `DIREITA` | `{"2a","3a","4a","5a","6a"}` | `{"Q1","Q2","Q3","Q4","Q5","Q6"}` |
| `converteEntrada` | `digitado - 2` (usuário digita 2 a 6) | `digitado - 1` (usuário digita 1 a 6) |
| `AJUDA` | legenda dos dias | "Digite os quartos aceitos, ex.: 1 2 4" |
| `if (total < ...)` | precisa alocar todos → mantém | se pedir só o **máximo** → mostre os pares sempre, com o total |

Se a questão quiser os pares **por pessoa** (ex.: "Casal A → Quarto 1"), troque o `for` final para percorrer `par` e imprimir `ESQUERDA[par[d]] + " -> " + DIREITA[d]`.

---

## 3. Heap / fila de prioridade

### Pseudocódigo
```
inserir(x):
    coloca x no fim do vetor (posicao n)
    enquanto x for "melhor" que o pai (posicao i/2): troca e sobe

remover():
    guarda a raiz (posicao 1)
    move o ultimo elemento para a raiz
    enquanto tiver filho "melhor" (2i ou 2i+1): troca com o melhor filho e desce
    devolve a raiz guardada

"melhor" = menor (min-heap)  ou  maior (max-heap)
```

### O que alterar
| Onde (`ALTERE`) | O que fazer |
|---|---|
| `new Heap(true)` | `true` = **menor sai primeiro** (senhas 1001 < 2001). `false` = **maior sai primeiro** (ex.: nota mais alta, urgência maior). |
| `contador = new int[5]` | número de prioridades + 1 (4 prioridades → 5) |
| `prioridade * 1000 + contador` | formato da senha pedido (ex.: `* 100` se a senha tiver 3 dígitos) |
| `botoes` | os nomes dos botões da questão; ajuste o `if/else` aos índices |
| `proximo()` | texto mostrado quando a fila está vazia |

**HeapSort**, se pedirem: insira todos e depois remova todos. Sai ordenado (crescente com min-heap, decrescente com max-heap).

---

## 4. Hash

### Pseudocódigo
```
ENCADEAMENTO ("hashing aberto" para o professor):
  inserir(chave): i = hash(chave); coloca a chave no FIM da lista tabela[i]
  buscar(chave):  percorre a lista tabela[hash(chave)] contando tentativas

SONDAGEM LINEAR ("enderecamento aberto"):
  inserir(chave): h = hash(chave)
                  tenta h, h+1, h+2... (volta ao inicio com % tamanho) ate achar vazio
  buscar(chave):  mesmo caminho; parar ao achar a chave (ou uma posicao vazia = nao existe)
```

### O que alterar
| Onde (`ALTERE`) | O que fazer |
|---|---|
| tamanho da tabela | letra inicial → 26; números → o tamanho pedido (de preferência primo) |
| `hash()` | letra inicial: `charAt(0) - 'A'`; número: `numero % tamanho`; texto: soma dos caracteres `% tamanho` |
| `mostrar()` | formato de saída pedido (ex.: `15 PAULO, PEDRO;`) |
| classe usada no `main` | `HashEncadeado` (encadeamento) ou `HashSondagemLinear` (endereçamento aberto) |
| sondagem | linear `(h + i) % tam`; quadrática `(h + i*i) % tam` |

Se a questão proibir estruturas prontas do Java, esta base já obedece: usa só vetor e nó próprio (`No`).

---

## 5. Grafo (vários algoritmos num arquivo só)

### Pseudocódigos
```
BFS (menor numero de saltos)                 DIJKSTRA (menor custo, pesos >= 0)
  dist[] = -1; dist[origem] = 0                dist[] = infinito; dist[origem] = 0
  fila <- origem                               fila de prioridade <- (origem, 0)
  enquanto fila nao vazia:                     enquanto fila nao vazia:
    u = tira da fila                             u = tira o de MENOR distancia
    para cada vizinho v de u:                    para cada vizinho v de u com peso w:
      se dist[v] == -1:                            se dist[u] + w < dist[v]:     (relaxamento)
        dist[v] = dist[u] + 1                        dist[v] = dist[u] + w
        anterior[v] = u; poe v na fila               anterior[v] = u; poe v na fila

KAHN (ordem topologica)                      EULER
  conta setas que CHEGAM em cada vertice       conta os vertices de grau impar
  fila <- vertices com 0                       0 impares -> euleriano
  enquanto fila nao vazia:                     2 impares -> semi (comeca num, termina no outro)
    u = tira; ordem += u                       mais    -> nao e
    para cada v com seta u->v:               (precisa ser conexo)
      entrada[v]--; se chegou a 0: fila <- v
  se ordem < total de vertices: CICLO        BIPARTIDO
                                               pinta a origem com cor 0 (BFS)
HAMILTON (backtracking)                        vizinho sem cor -> cor oposta
  caminho = [inicio]                           vizinho com a MESMA cor -> nao e bipartido
  tenta(u):
    se usou todos: existe aresta u->inicio?
    para cada vizinho v nao usado:
      usa v; se tenta(v): sucesso
      desusa v   <- desfaz (backtracking)
```

### O que alterar
| Onde (`ALTERE`) | O que fazer |
|---|---|
| `nomes` | os vértices da questão (`{"A","B",...}`, `{"SP","RJ",...}`, `{"v1","v2",...}`) |
| `dirigido` | `true` para setas (dependências, mão única); `false` caso contrário |
| leitura das arestas | formato `A B 4` (sem peso: `A B`). Se as arestas forem fixas no enunciado, troque o `while` por chamadas `g.addAresta(g.indice("A"), g.indice("B"), 4);` |
| `origem` / `destino` | de onde sai e aonde chega (ex.: `g.indice("SP")`) |
| bloco "2. RESPONDER" | **apague** o que não foi pedido |

Para **isomorfismo**, monte dois grafos e use `Grafo.mesmaSequenciaGraus(g1, g2)`. Se der `false`, **não são isomorfos**.

---

## 6. Técnicas de projeto

| Técnica | Molde | Método na base |
|---|---|---|
| Força bruta | `para cada possibilidade: testa` | `maximoForcaBruta`, `potenciaForcaBruta` |
| Divisão e conquista | `se pequeno: resolve direto` → `divide ao meio` → `resolve as metades` → `junta` | `maximoDC`, `somaDC`, `potenciaDC`, `mergeSort` |
| Programação dinâmica | `tabela[i] = combina(tabela[i-1], tabela[i-2]...)`, cada valor **calculado uma vez** | `fibPD` (compare com `fibRecursivo`) |
| Guloso | `ordena as opções` → `pega sempre a melhor agora` → `nunca volta atrás` | `troco` |
| Backtracking | `tenta` → `se falhar, desfaz e tenta outra` | `cicloHamiltoniano` (BaseGrafo) |

**Para adaptar o D&C** a outro problema (mínimo, contar elementos, procurar um valor), mude só **o caso base** e **a forma de juntar** (`Math.max` → `Math.min`, `+`…).

---

## 7. Erros comuns ao adaptar

| Sintoma | Causa provável |
|---|---|
| `class X is public, should be declared in a file named X.java` | renomeou a classe e não o arquivo (ou o contrário) |
| `missing return statement` | método que não é `void` sem `return` |
| `cannot find symbol` | nome escrito diferente, falta `import`, ou atributo apagado ainda usado |
| `ArrayIndexOutOfBoundsException` | índice fora do vetor: confira o `- 1` / `- 2` da entrada e o tamanho dos vetores |
| `NumberFormatException: For input string: ""` | `split(" ")` com espaço duplo; use `split("\\s+")` |
| o .jar roda a versão antiga | esqueceu de recompilar e gerar o .jar depois de editar |
