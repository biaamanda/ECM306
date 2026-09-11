/* Unidade 16 - Hashing
 *
 * FUNCAO HASH pelo metodo da divisao: h(k) = k mod m  (slides 42-49).
 *
 * Este arquivo reproduz o exemplo dos slides SEM nenhum tratamento de
 * colisao, so para deixar claro o problema: quando duas chaves diferentes
 * caem no mesmo slot, uma sobrescreve a outra (dado se perde!).
 *
 * Veja TabelaHashEncadeamento.java e TabelaHashAberto.java para as duas
 * formas de CONSERTAR isso.
 */

public class FuncaoHash {

    // Metodo da divisao (slide 43): h(k) = k mod m
    public static int hash(int chave, int m) {
        return chave % m;
    }

    public static void main(String[] args) {
        // ---------------------------------------------------------------
        // Exemplo do slide 45: tabela com 80 slots, chaves entre 0 e 1000.
        // ---------------------------------------------------------------
        int m = 80;
        String[] tabHash = new String[m];

        Integer[] tabKeys  = {100, 180, 260};
        String[]  tabNomes = {"Ana", "Ivo", "Ari"};

        System.out.println("--- Exemplo do slide 49/52 (h(k) = k % 80) ---");
        for (int i = 0; i < tabKeys.length; i++) {
            int indice = hash(tabKeys[i], m);
            boolean colisao = tabHash[indice] != null;
            System.out.println("Chave: " + tabKeys[i] + "   HashCode = " + indice
                    + (colisao ? "   COLISAO! sobrescreve \"" + tabHash[indice] + "\"" : ""));
            tabHash[indice] = tabNomes[i]; // SEM tratamento: o ultimo que chega vence
        }

        System.out.println("\nResultado (so sobrou UMA chave no slot 20):");
        for (int i = 0; i < tabHash.length; i++) {
            if (tabHash[i] != null) {
                System.out.println("Indice " + i + "  ==>  Valor armazenado: \"" + tabHash[i] + "\"");
            }
        }

        // ---------------------------------------------------------------
        // Exemplo do slide 46: varias chaves, m = 80 -> ver quais colidem.
        // ---------------------------------------------------------------
        System.out.println("\n--- Simulacao da funcao hash (slide 46), m = 80 ---");
        int[] chaves = {383, 487, 235, 527, 510, 564, 103, 66, 14};
        for (int chave : chaves) {
            System.out.println("h(" + chave + ") = " + hash(chave, m));
        }
        // 66 e 14 sao os proprios indices (chave < m): h(k) = k.
    }
}
