/* Unidade 16 - Hashing
 *
 * Simula a insercao passo a passo numa tabela hash com ENDERECAMENTO
 * ABERTO (sondagem linear), imprimindo a tabela inteira apos cada chave.
 * Bom para responder questoes do tipo "mostre o estado da tabela apos
 * inserir as chaves X, Y, Z" (estilo slides 66-68).
 *
 * h(k) = k mod m ; em colisao, tenta h(k)+1, h(k)+2, ... (mod m).
 */

public class SimulaEnderecamentoAberto {

    public static int hash(int chave, int m) {
        return chave % m;
    }

    // Retorna o slot livre encontrado por sondagem linear a partir de h(chave),
    // ou -1 se a tabela estiver cheia. tabHash[i] == null significa slot livre.
    public static int sondagemLinear(Integer[] tabHash, int chave) {
        int m = tabHash.length;
        int inicio = hash(chave, m);

        for (int passo = 0; passo < m; passo++) {
            int pos = (inicio + passo) % m;
            if (tabHash[pos] == null) {
                return pos;
            }
        }
        return -1;
    }

    public static void inserir(Integer[] tabHash, int chave) {
        int calculado = hash(chave, tabHash.length);
        boolean colisao = tabHash[calculado] != null;

        int pos = sondagemLinear(tabHash, chave);
        if (pos == -1) {
            System.out.println("Chave " + chave + " -> tabela CHEIA, nao foi possivel inserir.");
            return;
        }

        tabHash[pos] = chave;

        System.out.println("Inserindo " + chave + ":  h(" + chave + ") = " + calculado
                + (colisao ? "  (colisao! sonda ate achar livre)" : "")
                + "  ->  posicao final = " + pos);
        imprimirTabela(tabHash);
        System.out.println();
    }

    public static void imprimirTabela(Integer[] tabHash) {
        StringBuilder indices = new StringBuilder("   idx: ");
        StringBuilder valores = new StringBuilder("  valor: ");
        for (int i = 0; i < tabHash.length; i++) {
            indices.append(String.format("%3d ", i));
            valores.append(tabHash[i] == null ? "  . " : String.format("%3d ", tabHash[i]));
        }
        System.out.println(indices);
        System.out.println(valores);
    }

    public static void main(String[] args) {
        // Exercicio 4 dos slides: h(k) = k mod 11
        int m = 11;
        Integer[] tabHash = new Integer[m];
        int[] chaves = {73, 15, 44, 37, 30, 59, 49, 99};

        System.out.println("Tabela hash com " + m + " slots, funcao h(k) = k mod " + m);
        System.out.println();

        for (int chave : chaves) {
            inserir(tabHash, chave);
        }

        System.out.println("Estado final:");
        imprimirTabela(tabHash);
        System.out.println("Esperado: idx 0..10 -> 44 99 . . 15 37 59 73 30 49 .");
    }
}
