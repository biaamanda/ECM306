import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;

/**
 * Separa o objeto do fundo de uma imagem a partir de um clique.
 *
 * Método principal: corte mínimo (graph cut).
 *   - Cada pixel é um vértice; S = objeto, T = fundo.
 *   - Arestas entre vizinhos (n-links): peso alto se as cores são parecidas.
 *   - Arestas S->p e p->T (t-links): quanto a cor de p lembra o objeto ou o fundo.
 *   - Sementes: o pixel clicado fica preso a S; a borda da imagem fica presa a T.
 *   - O fluxo máximo (Edmonds-Karp) encontra o corte mínimo.
 *
 * Método de comparação: BFS (flood fill) a partir do clique.
 */
public final class Segmentador {

    /** A imagem é reduzida para no máximo este lado, porque Edmonds-Karp é O(V * E^2). */
    public static final int TAMANHO_MAX = 120;

    private static final int ESCALA = 100;           // custos reais -> capacidades inteiras
    private static final double GAMMA = 50;          // peso das arestas entre vizinhos
    private static final double LAMBDA = 1;          // peso do termo de cor (t-links)
    private static final int INFINITO = 1_000_000_000;
    private static final int ITERACOES = 3;          // reestima as cores e corta de novo
    private static final int BITS_POR_CANAL = 3;     // histograma 8 x 8 x 8 cores

    /** Resultado de uma segmentação. A máscara está no tamanho da imagem reduzida. */
    public record Resultado(boolean[][] mascara, long fluxo, int vertices, int arestas,
                            long milissegundos, String metodo) {
        public int pixelsObjeto() {
            int total = 0;
            for (boolean[] linha : mascara) {
                for (boolean b : linha) {
                    if (b) total++;
                }
            }
            return total;
        }
    }

    private Segmentador() {
    }

    // ------------------------------------------------------------------
    // Corte mínimo
    // ------------------------------------------------------------------

    public static Resultado corteMinimo(BufferedImage original, int cliqueX, int cliqueY) {
        long inicio = System.nanoTime();
        BufferedImage img = reduzir(original);
        int w = img.getWidth();
        int h = img.getHeight();
        int n = w * h;
        int fonte = n;          // S
        int sorvedouro = n + 1; // T
        int[] rgb = img.getRGB(0, 0, w, h, null, 0, w);
        int semente = indiceSemente(original, img, cliqueX, cliqueY);

        // Pesos entre vizinhos: w(p,q) = GAMMA * e^(-beta * |Ip - Iq|^2)
        // beta = 1 / (2 * média das diferenças^2), ajusta-se ao contraste da imagem.
        double somaD2 = 0;
        int pares = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int p = y * w + x;
                if (x + 1 < w) { somaD2 += dist2(rgb[p], rgb[p + 1]); pares++; }
                if (y + 1 < h) { somaD2 += dist2(rgb[p], rgb[p + w]); pares++; }
            }
        }
        double beta = somaD2 > 0 ? pares / (2 * somaD2) : 0;
        int[] pesoDireita = new int[n];
        int[] pesoBaixo = new int[n];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int p = y * w + x;
                if (x + 1 < w) pesoDireita[p] = pesoVizinho(rgb[p], rgb[p + 1], beta);
                if (y + 1 < h) pesoBaixo[p] = pesoVizinho(rgb[p], rgb[p + w], beta);
            }
        }

        // Sementes "duras": perto do clique = objeto; borda da imagem = fundo.
        int sx = semente % w;
        int sy = semente / w;
        boolean[] sementeObjeto = new boolean[n];
        boolean[] sementeFundo = new boolean[n];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int p = y * w + x;
                if (quadrado(x - sx) + quadrado(y - sy) <= 4) sementeObjeto[p] = true;
                else if (x == 0 || y == 0 || x == w - 1 || y == h - 1) sementeFundo[p] = true;
            }
        }

        // Palpite inicial para os modelos de cor:
        // objeto = disco em volta do clique; fundo = faixa na borda da imagem.
        int raio = Math.max(3, Math.min(w, h) / 8);
        int faixa = Math.max(2, Math.min(w, h) / 20);
        boolean[] objeto = new boolean[n];
        boolean[] fundo = new boolean[n];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int p = y * w + x;
                objeto[p] = quadrado(x - sx) + quadrado(y - sy) <= raio * raio;
                fundo[p] = !objeto[p] && (x < faixa || y < faixa || x >= w - faixa || y >= h - faixa);
            }
        }

        long fluxo = 0;
        Grafo g = null;
        for (int it = 0; it < ITERACOES; it++) {
            double[] probObjeto = histograma(rgb, objeto);
            double[] probFundo = histograma(rgb, fundo);

            g = new Grafo(n + 2);
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int p = y * w + x;

                    // n-links (arestas entre pixels vizinhos)
                    if (x + 1 < w) g.addArestaDupla(p, p + 1, pesoDireita[p]);
                    if (y + 1 < h) g.addArestaDupla(p, p + w, pesoBaixo[p]);

                    // t-links (arestas com S e T)
                    int capS;
                    int capT;
                    if (sementeObjeto[p]) {
                        capS = INFINITO;
                        capT = 0;
                    } else if (sementeFundo[p]) {
                        capS = 0;
                        capT = INFINITO;
                    } else {
                        int bin = binCor(rgb[p]);
                        // cortar S->p põe p no fundo: custa o quanto p NÃO parece fundo
                        capS = (int) Math.round(ESCALA * LAMBDA * -Math.log(probFundo[bin]));
                        // cortar p->T põe p no objeto: custa o quanto p NÃO parece objeto
                        capT = (int) Math.round(ESCALA * LAMBDA * -Math.log(probObjeto[bin]));
                        // Subtrair o mesmo valor dos dois não muda qual corte é mínimo,
                        // e deixa cada pixel com só um t-link (menos caminhos para a BFS).
                        int menor = Math.min(capS, capT);
                        capS -= menor;
                        capT -= menor;
                    }
                    if (capS > 0) g.addAresta(fonte, p, capS);
                    if (capT > 0) g.addAresta(p, sorvedouro, capT);
                }
            }

            fluxo = EdmondsKarp.fluxoMaximo(g, fonte, sorvedouro);
            boolean[] ladoS = EdmondsKarp.ladoDaFonte(g, fonte);

            // Fica só a parte do objeto ligada ao clique (remove ilhas soltas).
            objeto = componenteConectado(ladoS, w, h, semente);
            for (int p = 0; p < n; p++) fundo[p] = !objeto[p];
        }

        long ms = (System.nanoTime() - inicio) / 1_000_000;
        return new Resultado(paraMatriz(objeto, w, h), fluxo, g.numVertices(), g.numArestas(), ms,
                "Corte mínimo (Edmonds-Karp)");
    }

    // ------------------------------------------------------------------
    // BFS simples (flood fill), para comparação
    // ------------------------------------------------------------------

    public static Resultado bfs(BufferedImage original, int cliqueX, int cliqueY, double limiar) {
        long inicio = System.nanoTime();
        BufferedImage img = reduzir(original);
        int w = img.getWidth();
        int h = img.getHeight();
        int n = w * h;
        int[] rgb = img.getRGB(0, 0, w, h, null, 0, w);
        int semente = indiceSemente(original, img, cliqueX, cliqueY);
        int corSemente = rgb[semente];
        double limiar2 = limiar * limiar;

        boolean[] objeto = new boolean[n];
        ArrayDeque<Integer> fila = new ArrayDeque<>();
        fila.add(semente);
        objeto[semente] = true;
        while (!fila.isEmpty()) {
            int p = fila.poll();
            int x = p % w;
            int y = p / w;
            int[] vizinhos = {x > 0 ? p - 1 : -1, x < w - 1 ? p + 1 : -1, y > 0 ? p - w : -1, y < h - 1 ? p + w : -1};
            for (int q : vizinhos) {
                if (q >= 0 && !objeto[q] && dist2(rgb[q], corSemente) <= limiar2) {
                    objeto[q] = true;
                    fila.add(q);
                }
            }
        }
        int arestas = 2 * ((w - 1) * h + w * (h - 1));
        long ms = (System.nanoTime() - inicio) / 1_000_000;
        return new Resultado(paraMatriz(objeto, w, h), 0, n, arestas, ms, "BFS (flood fill)");
    }

    // ------------------------------------------------------------------
    // Sticker: aplica a máscara na imagem original (fundo transparente)
    // ------------------------------------------------------------------

    public static BufferedImage gerarSticker(BufferedImage original, boolean[][] mascara) {
        int W = original.getWidth();
        int H = original.getHeight();
        int h = mascara.length;
        int w = mascara[0].length;
        BufferedImage saida = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < H; y++) {
            // posição correspondente na máscara reduzida (interpolação bilinear = borda suave)
            double my = (y + 0.5) * h / H - 0.5;
            for (int x = 0; x < W; x++) {
                double mx = (x + 0.5) * w / W - 0.5;
                double a = amostrar(mascara, mx, my);
                a = Math.max(0, Math.min(1, (a - 0.5) * 2 + 0.5));
                int alfa = (int) Math.round(a * 255);
                int cor = original.getRGB(x, y) & 0xFFFFFF;
                saida.setRGB(x, y, (alfa << 24) | cor);
            }
        }
        return saida;
    }

    // ------------------------------------------------------------------
    // Auxiliares
    // ------------------------------------------------------------------

    static BufferedImage reduzir(BufferedImage img) {
        double f = Math.min(1.0, (double) TAMANHO_MAX / Math.max(img.getWidth(), img.getHeight()));
        int w = Math.max(1, (int) Math.round(img.getWidth() * f));
        int h = Math.max(1, (int) Math.round(img.getHeight() * f));
        BufferedImage r = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = r.createGraphics();
        g.drawImage(img.getScaledInstance(w, h, Image.SCALE_AREA_AVERAGING), 0, 0, null);
        g.dispose();
        return r;
    }

    /** Converte o clique (coordenadas da imagem original) para o índice do pixel reduzido. */
    private static int indiceSemente(BufferedImage original, BufferedImage reduzida, int cx, int cy) {
        int w = reduzida.getWidth();
        int h = reduzida.getHeight();
        int x = Math.min(w - 1, Math.max(0, cx * w / original.getWidth()));
        int y = Math.min(h - 1, Math.max(0, cy * h / original.getHeight()));
        return y * w + x;
    }

    private static int pesoVizinho(int c1, int c2, double beta) {
        return (int) Math.round(ESCALA * GAMMA * Math.exp(-beta * dist2(c1, c2)));
    }

    private static int binCor(int c) {
        int desloc = 8 - BITS_POR_CANAL;
        int r = ((c >> 16) & 0xFF) >> desloc;
        int g = ((c >> 8) & 0xFF) >> desloc;
        int b = (c & 0xFF) >> desloc;
        return (r << (2 * BITS_POR_CANAL)) | (g << BITS_POR_CANAL) | b;
    }

    /** Probabilidade de cada faixa de cor dentro do conjunto marcado (com suavização de Laplace). */
    private static double[] histograma(int[] rgb, boolean[] conjunto) {
        int bins = 1 << (3 * BITS_POR_CANAL);
        double[] cont = new double[bins];
        int total = 0;
        for (int p = 0; p < rgb.length; p++) {
            if (conjunto[p]) {
                cont[binCor(rgb[p])]++;
                total++;
            }
        }
        for (int i = 0; i < bins; i++) cont[i] = (cont[i] + 1) / (total + bins);
        return cont;
    }

    /** BFS sobre os pixels do objeto, a partir da semente: só o pedaço conectado ao clique. */
    private static boolean[] componenteConectado(boolean[] marcado, int w, int h, int semente) {
        boolean[] resultado = new boolean[w * h];
        if (!marcado[semente]) return resultado;
        ArrayDeque<Integer> fila = new ArrayDeque<>();
        fila.add(semente);
        resultado[semente] = true;
        while (!fila.isEmpty()) {
            int p = fila.poll();
            int x = p % w;
            int y = p / w;
            int[] vizinhos = {x > 0 ? p - 1 : -1, x < w - 1 ? p + 1 : -1, y > 0 ? p - w : -1, y < h - 1 ? p + w : -1};
            for (int q : vizinhos) {
                if (q >= 0 && marcado[q] && !resultado[q]) {
                    resultado[q] = true;
                    fila.add(q);
                }
            }
        }
        return resultado;
    }

    private static boolean[][] paraMatriz(boolean[] v, int w, int h) {
        boolean[][] m = new boolean[h][w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) m[y][x] = v[y * w + x];
        }
        return m;
    }

    private static double amostrar(boolean[][] m, double x, double y) {
        int h = m.length;
        int w = m[0].length;
        int x0 = (int) Math.floor(x);
        int y0 = (int) Math.floor(y);
        double fx = x - x0;
        double fy = y - y0;
        double a = valor(m, x0, y0, w, h) * (1 - fx) + valor(m, x0 + 1, y0, w, h) * fx;
        double b = valor(m, x0, y0 + 1, w, h) * (1 - fx) + valor(m, x0 + 1, y0 + 1, w, h) * fx;
        return a * (1 - fy) + b * fy;
    }

    private static double valor(boolean[][] m, int x, int y, int w, int h) {
        x = Math.max(0, Math.min(w - 1, x));
        y = Math.max(0, Math.min(h - 1, y));
        return m[y][x] ? 1 : 0;
    }

    private static int dist2(int c1, int c2) {
        int dr = ((c1 >> 16) & 0xFF) - ((c2 >> 16) & 0xFF);
        int dg = ((c1 >> 8) & 0xFF) - ((c2 >> 8) & 0xFF);
        int db = (c1 & 0xFF) - (c2 & 0xFF);
        return dr * dr + dg * dg + db * db;
    }

    private static int quadrado(int v) {
        return v * v;
    }
}
