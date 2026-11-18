# Teoria — P4 ECM306

Explicação simples, tópico por tópico. Os códigos estão em [CODIGOS_P4.md](CODIGOS_P4.md).

---

## ⚙️ Passo a passo: compilar e gerar o .jar (computador só com o Java instalado)

Na prova, o .jar **precisa rodar**, senão a questão vale zero. Siga a ordem abaixo. Os exemplos usam a classe `Horario`; troque pelo nome da **sua classe que tem o `main`**.

### Passo 1 — Abrir o terminal na pasta dos arquivos `.java`
- No Explorador de Arquivos, abra a pasta onde estão os seus `.java`.
- Clique na barra de endereço, digite `powershell` e aperte **Enter**. O terminal abre já naquela pasta.
- Confira se os arquivos aparecem:

```powershell
dir *.java
```

Se aparecer `Invalid filename: *.java` mais adiante, é porque você **não está na pasta certa**.

### Passo 2 — Ver se o Java responde

```powershell
java -version
```
```powershell
javac -version
```
```powershell
jar --version
```

- Os 3 responderam? Pule para o **Passo 4**.
- Algum deu "**não é reconhecido**"? Vá para o **Passo 3**.

### Passo 3 — Ensinar ao terminal onde está o Java
O Java costuma estar em `C:\Program Files\Java\`, mas a versão muda de computador para computador. Descubra o caminho:

```powershell
Get-ChildItem "C:\Program Files\Java" -Recurse -Filter jar.exe
```

A resposta mostra uma pasta como `C:\Program Files\Java\jdk-25.0.2\bin`. Coloque **essa pasta** no comando abaixo (vale só para essa janela do terminal):

```powershell
$env:Path += ";C:\Program Files\Java\jdk-25.0.2\bin"
```

Repita o Passo 2 para confirmar que os 3 comandos respondem.

- Se a busca não achou nada, procure também em `C:\Program Files\Eclipse Adoptium\`.
- Se só existir `jre` (sem `jdk`), não há compilador. Gere o .jar pela IDE: IntelliJ em *Build → Build Artifacts*; Eclipse em *File → Export → Runnable JAR file*.

### Passo 4 — Compilar

```powershell
javac --release 8 -Xlint:-options *.java
```

- Transforma os `.java` em `.class`.
- `--release 8` faz o programa rodar até em computador com Java antigo.
- `-Xlint:-options` só esconde os avisos de "obsolete".
- Se aparecer **erro**, é erro no código: leia o nome do arquivo e o número da linha.

### Passo 5 — Gerar o .jar

```powershell
jar cfe Horario.jar Horario *.class
```

- `c` = **c**riar; `f` = nome do arquivo (`Horario.jar`); `e` = classe que tem o `main` (`Horario`).
- Coloque **todos** os `.class` (`*.class`): as outras classes também precisam estar no .jar.

### Passo 6 — Testar o .jar

```powershell
java -jar Horario.jar
```

Rode **todos os casos de teste** do enunciado antes de entregar.

### Passo 7 — Escrever no .docx da prova como executar
Exemplo: *"Abrir o terminal na pasta Questao6 e executar: `java -jar Horario.jar`."*
O professor segue **exatamente** o que você escrever.

### Se usar `package` no código (ex.: `package br.maua;`)

```powershell
javac --release 8 -Xlint:-options -d out *.java
```
```powershell
jar cfe Horario.jar br.maua.Horario -C out .
```

### Se estiver no Prompt de Comando (cmd) em vez do PowerShell
Os comandos dos Passos 4 a 6 são iguais. Mudam só o Passo 3:

```bat
where /r "C:\Program Files\Java" jar.exe
```
```bat
set PATH=%PATH%;C:\Program Files\Java\jdk-25.0.2\bin
```

### Erros comuns
| Mensagem | Causa | Solução |
|---|---|---|
| `Invalid filename: *.java` | terminal fora da pasta dos `.java` | Passo 1 |
| `'jar' is not recognized` | o terminal não sabe onde está o JDK | Passo 3 |
| `no main manifest attribute` | faltou o `e` ou o nome da classe no `jar cfe` | refaça o Passo 5 |
| `NoClassDefFoundError` | o .jar ficou sem alguma classe, ou o nome do `main` está errado | use `*.class` e confira o nome (com pacote, se tiver) |
| `UnsupportedClassVersionError` | compilou sem `--release 8` e o Java do PC é antigo | refaça o Passo 4 com `--release 8` |
| `Unable to access jarfile` | o .jar não existe nessa pasta (o Passo 5 falhou) | refaça o Passo 5 |

---

## 1. O básico de grafos

Um grafo **G = (V, E)** tem:
- **V**: os vértices (pontos). **Nunca pode ser vazio.**
- **E**: as arestas (linhas). **Pode ser vazio.**

| Palavra | Significado |
|---|---|
| Arestas paralelas | duas arestas ligando os mesmos dois vértices |
| Laço | aresta que sai e volta para o mesmo vértice |
| Grafo simples | não tem laço nem aresta paralela |
| Vértices adjacentes | estão ligados por uma aresta |
| Arestas adjacentes | duas arestas que dividem um vértice |
| Grafo nulo | não tem arestas (mas tem vértices!) |
| Grau de um vértice | quantas arestas tocam nele |
| Vértice isolado | grau 0 |
| Grafo regular | todos os vértices têm o mesmo grau |

**Regra de ouro dos graus:** somando o grau de todos os vértices, dá **2 × número de arestas** (cada aresta tem duas pontas).
Consequências:
- A soma dos graus é sempre **par**.
- A quantidade de vértices com grau ímpar é sempre **par**.
- Num grafo simples com n vértices, ninguém tem grau maior que **n − 1**.

> Exemplo: "Existe grafo simples com 5 vértices de graus 1, 2, 3, 4, 5?" → **Não.** Grau 5 é impossível com 5 vértices (máximo 4), e a soma 15 é ímpar.

### Tipos famosos
| Grafo | O que é | Arestas |
|---|---|---|
| **Kn** (completo) | todo vértice ligado a todos | n(n−1)/2 |
| **Km,n** (bipartido completo) | dois grupos, todos de um lado ligados a todos do outro | m × n |
| **Cn** (ciclo) | um anel com n vértices | n |
| **Bipartido** | dá para dividir os vértices em 2 grupos, e as arestas só vão de um grupo para o outro | — |

**Como saber se é bipartido:** tente pintar os vértices com 2 cores sem dois vizinhos da mesma cor. Se achar um **triângulo** (ou qualquer ciclo de tamanho ímpar), **não é bipartido**.

---

## 2. Passeio, trilha e caminho

Andar pelo grafo de vértice em vértice usando arestas.

| Nome | Pode repetir vértice? | Pode repetir aresta? |
|---|---|---|
| **Passeio** | sim | sim |
| **Trilha** | sim | **não** |
| **Caminho** | **não** | **não** |

- **Fechado** = termina onde começou. **Aberto** = termina em outro lugar.
- Trilha fechada = **circuito**. Caminho fechado = **ciclo**.
- **Comprimento** = número de arestas percorridas.
- Primeiro vértice = **origem**; último = **término**; os do meio = **internos**.

---

## 3. Euler e Hamilton

**Euler = passar por todas as ARESTAS uma vez só.**
- **Euleriano**: dá para fazer isso e voltar ao início.
- **Semi-euleriano**: dá para fazer, mas termina em outro lugar.

> **Regra (só olhar os graus, com o grafo conexo):**
> - Todos os graus **pares** → euleriano.
> - Exatamente **2** graus ímpares → semi-euleriano (começa num ímpar e termina no outro).
> - Mais de 2 ímpares → não dá.

**Hamilton = passar por todos os VÉRTICES uma vez só** (e voltar, no hamiltoniano).
- **Não existe regra simples.** Para mostrar que é, desenhe o ciclo.
- Dicas que **garantem** (mas, se falharem, o grafo ainda pode ser hamiltoniano):
  - **Dirac:** todo vértice tem grau ≥ n/2.
  - **Ore:** para todo par de vértices **não** ligados, a soma dos graus é ≥ n.

Truque para lembrar: **E**uler = **E**dges (arestas). Hamilton = vértices.

---

## 4. Isomorfismo

Dois grafos são **isomorfos** quando são o **mesmo grafo desenhado de jeitos diferentes**.

Como verificar (em ordem):
1. Mesmo número de vértices?
2. Mesmo número de arestas?
3. Mesma lista de graus (ordenada)?

Se **qualquer um for diferente → NÃO são isomorfos.**
Se tudo bater, ainda pode não ser isomorfo; aí é preciso mostrar qual vértice corresponde a qual.

---

## 5. Emparelhamento ⭐ (muito provável)

**Situação típica:** pessoas que precisam ser alocadas em recursos (casais → quartos, professores → dias, ônibus → vagas).

**Como modelar:**
- Um grupo de vértices à esquerda (quem precisa ser alocado).
- Outro grupo à direita (os recursos).
- Uma aresta quando a pessoa **aceita** aquele recurso.
- Isso é um **grafo bipartido**.

**Emparelhamento** = escolher arestas de modo que **ninguém aparece em duas**. (Cada casal num quarto só, cada quarto com um casal só.)

| Nome | Significado |
|---|---|
| Vértice **livre** | ainda não foi alocado |
| Emparelhamento **máximo** | o maior número de pares possível |
| Emparelhamento **perfeito** | **todos** os vértices foram alocados (os dois lados) |
| Emparelhamento **completo** | todos do lado esquerdo foram alocados (ex.: 5 ônibus em 6 vagas) |

### Teorema de Berge — como melhorar uma alocação
**Caminho aumentante:** começa num vértice **livre**, alterna aresta **não usada** → aresta **usada** → não usada…, e termina em outro vértice **livre**.

Se você achar um, **inverta**: o que era usado sai, o que não era entra. A alocação ganha **+1 par**.

> **Berge:** a alocação é a melhor possível **quando não existe mais caminho aumentante**.

**Exemplo:** Casal D está sem quarto, e o quarto 2 está livre.
D quer o quarto 5, que está com F. F também aceita o 2.
Caminho: D → 5 → F → 2. Inverte: D fica com 5 e F vai para o 2. Um casal a mais hospedado.

### Teorema de Hall — como provar que NÃO dá
Pegue um grupo **S** de pessoas e veja **N(S)**, o conjunto de recursos que elas aceitam juntas.

> **Hall:** dá para alocar todo mundo **se e só se** nenhum grupo tem mais pessoas do que opções: **|N(S)| ≥ |S|** sempre.

**Exemplo:** P1 só pode quinta, P2 só segunda, P3 segunda ou quinta.
São 3 professores disputando só 2 dias → **impossível**.

**Berge** diz **como** aumentar a alocação. **Hall** diz **se** dá para alocar todos.

---

## 6. Como guardar um grafo no computador

| | Lista de adjacência | Matriz de adjacência |
|---|---|---|
| O que é | cada vértice tem uma lista dos vizinhos | tabela n × n com 1 se há aresta |
| Memória | V + E (econômica) | V² (gasta mais) |
| "Existe aresta u–v?" | precisa procurar na lista | **instantâneo, O(1)** |
| Melhor para | grafo com **poucas** arestas (o normal) | grafo pequeno ou cheio de arestas |

- **Matriz de incidência**: linhas = vértices, colunas = arestas (n × m).
- Matriz de adjacência com 10 vértices tem 10 × 10 = **100 posições**, não importa quantas arestas existam.

---

## 7. Buscas e algoritmos (T22 e T25)

| Problema | Algoritmo | Usa | Custo |
|---|---|---|---|
| Visitar tudo indo fundo primeiro | **DFS** (profundidade) | pilha/recursão | O(V+E) |
| Menor número de saltos | **BFS** (largura) | fila | O(V+E) |
| Menor custo com pesos ≥ 0 | **Dijkstra** | fila de prioridade | O((V+E) log V) |
| Ordem de tarefas com dependência | **Ordenação topológica (Kahn)** | fila + grau de entrada | O(V+E) |
| Alocar recursos | **Emparelhamento** | caminho aumentante | O(V·E) |

**Qual escolher?**
- Todas as arestas valem igual → **BFS**.
- Pesos diferentes e positivos → **Dijkstra**.
- Tem peso **negativo** → **nem BFS nem Dijkstra**.

### BFS
Começa na origem e visita **todos os vizinhos primeiro**, depois os vizinhos dos vizinhos, como ondas. A distância é contada em **saltos**.

### Dijkstra
1. Origem = 0; todos os outros = ∞.
2. Pegue o vértice **ainda não fechado com menor distância**.
3. Para cada vizinho: se **ir por ele ficar mais barato**, atualize (isso se chama **relaxamento**).
4. Repita até acabar.

Monte sempre uma **tabela** (uma linha por vértice retirado). Guarde também **de onde veio** cada vértice, para reconstruir a rota de trás para frente.

### Ordenação topológica (Kahn)
Exemplo: "B depende de A" vira uma seta A → B.
1. Conte quantas setas **chegam** em cada vértice.
2. Quem tem **0** pode ser feito agora.
3. Faça, apague as setas que saem dele e repita.
4. Se sobrarem vértices que nunca chegam a 0 → existe uma **dependência circular** (ciclo).

A ordem **não é única**.

---

## 8. Técnicas de projeto de algoritmos (T23)

| Técnica | Ideia em uma frase | Exemplo |
|---|---|---|
| **Força bruta** | testa **todas** as possibilidades | achar o máximo olhando um por um |
| **Divisão e conquista** | **divide** em pedaços, **resolve** cada um, **junta** | MergeSort — O(n log n) |
| **Programação dinâmica** | os pedaços se **repetem**, então resolve uma vez e **guarda** | Fibonacci |
| **Guloso** | escolhe o que parece **melhor agora** e **nunca volta atrás** | troco com as maiores notas; Dijkstra |
| **Backtracking** | tenta um caminho; se der errado, **volta** e tenta outro | labirinto |

- **Divisão e conquista × programação dinâmica:** na primeira os pedaços são independentes; na segunda eles se repetem.
- **Fibonacci recursivo** é lento porque recalcula as mesmas coisas várias vezes. Com programação dinâmica, guardando os dois últimos valores, fica O(n).
- **Guloso nem sempre acerta:** com moedas de 1, 3 e 4, troco de 6 → o guloso dá 4+1+1 (3 moedas), mas 3+3 (2 moedas) é melhor.
- **P** = resolve rápido. **NP** = conferir a resposta é rápido. **NP-completo** = os mais difíceis de NP (ex.: Hamilton, caixeiro-viajante).

---

## 9. Planaridade (T24)

- **Planar** = dá para **redesenhar** o grafo sem nenhuma aresta cruzando.
- **Plano** = já está desenhado sem cruzamentos.
- **K5 e K3,3 não são planares.**
- **Faces** = regiões que o desenho divide (conte também a região de fora, a **face exterior**).
- **Fórmula de Euler** (grafo plano e conexo): **vértices − arestas + faces = 2**.
- **Kuratowski:** um grafo é planar se e só se não "contém" K5 nem K3,3 escondido (subgrafo homeomorfo).
- **Redução de série:** vértice de grau 2 no meio de uma linha pode ser apagado, juntando as duas arestas numa só.
- **Teorema das 4 cores:** todo grafo planar pode ser pintado com 4 cores sem vizinhos iguais.

---

## 10. Heap e Hash (caíram em 2023)

### Heap
- Árvore em que o **pai é sempre maior** (max-heap) ou **menor** (min-heap) que os filhos.
- É preenchida da esquerda para a direita, por isso cabe num **vetor**.
- Com índices começando em 1: filho esquerdo = **2i**, filho direito = **2i + 1**, pai = **i/2**.
- Inserir e remover custam **O(log n)**.
- Usado para fazer **fila de prioridade**.

### Hash
- Uma função transforma a chave num **índice** do vetor. Ex.: letra inicial do nome → A=0, B=1, …, Z=25.
- Busca em **O(1)**, pagando com mais memória.
- **Colisão** = duas chaves caem no mesmo índice.
- **Encadeamento separado** (o professor chama de **"hashing aberto"**): cada posição guarda uma **lista**, e quem colide entra no fim dela.
- **Endereçamento aberto**: quem colide procura outra posição livre no próprio vetor (tenta a próxima, por exemplo).

---

## Dica para questões "I PORQUE II"
1. A frase I é verdadeira?
2. A frase II é verdadeira?
3. Só se as duas forem: II **explica** I? (Às vezes a alternativa certa é que I explica II.)
