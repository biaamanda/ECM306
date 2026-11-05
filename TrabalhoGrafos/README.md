# Trabalho de Grafos — Sticker por corte mínimo

ECM306 · Prof. Calvetti · entrega e apresentação em 12/11/2026.

A aplicação abre uma imagem, o usuário clica no objeto e o programa gera um
**sticker** (PNG com fundo transparente), como o celular faz quando você
pressiona uma foto.

## Como funciona

1. A imagem é reduzida para no máximo 120 px de lado.
2. Cada pixel vira um **vértice**; cada pixel se liga aos 4 vizinhos por
   **arestas** cujo peso é alto quando as cores são parecidas:
   `w = 50 · e^(−β·|Ip − Iq|²)`.
3. Dois vértices extras: **S** (objeto) e **T** (fundo). O clique fica preso a S
   e a borda da imagem fica presa a T. Os outros pixels ligam-se a S e T
   conforme sua cor lembra o objeto ou o fundo (histogramas de cor).
4. O **fluxo máximo** (Edmonds-Karp, com BFS) encontra o **corte mínimo**.
   Os pixels alcançáveis a partir de S no grafo residual são o objeto.
5. Repete 3 vezes, reestimando as cores do objeto e do fundo com o
   resultado anterior (ideia do GrabCut).
6. A máscara é ampliada para o tamanho original (bordas suavizadas) e vira o
   canal alfa do PNG.

Para comparação há também um recorte por **BFS simples** (flood fill a partir
do clique, com limiar de cor).

## Arquivos

| Arquivo | O que faz |
|---|---|
| `src/Aresta.java` | Aresta com capacidade, fluxo e aresta reversa |
| `src/Grafo.java` | Grafo em lista de adjacência |
| `src/EdmondsKarp.java` | Fluxo máximo e BFS final do lado de S |
| `src/Segmentador.java` | Monta o grafo da imagem, corte mínimo, BFS, gera o sticker |
| `src/PainelImagem.java` | Painel Swing que mostra a imagem e recebe o clique |
| `src/App.java` | Janela principal (e modo terminal) |
| `exemplos/` | Imagens de teste e a comparação corte × BFS |

## Como rodar

Precisa de JDK 17 ou mais novo. Na pasta `TrabalhoGrafos`:

```bash
javac -encoding UTF-8 -d out src/*.java
```

```bash
java -cp out App
```

Na janela: **Abrir imagem** → clique no objeto → **Salvar sticker**.
O menu "Método" alterna entre corte mínimo e BFS.

Modo terminal (útil para gerar prints):

```bash
java -cp out App exemplos/teste_listras.png 300 225 sticker.png
```

```bash
java -cp out App exemplos/teste_listras.png 300 225 sticker_bfs.png bfs
```

## Resultados de teste

Em `exemplos/teste_listras.png` (objeto listrado de duas cores, com ruído):

- Corte mínimo: recorta o objeto inteiro (28% dos pixels), cerca de 0,3 s.
- BFS: pega só a listra clicada (2% dos pixels).

Veja `exemplos/comparacao_listras.png` (original, corte mínimo, BFS).

## Limitações

- O objeto precisa ter cores diferentes do fundo; uma parte do objeto com a
  mesma cor do fundo é cortada (veja `exemplos/teste_elipse.png`, cujo canto
  inferior direito tem cor de fundo de propósito).
- Objetos encostados na borda da imagem perdem a parte da borda, porque a
  borda é tratada como fundo.
- Os celulares de hoje usam redes neurais; este é o método clássico de grafos.
