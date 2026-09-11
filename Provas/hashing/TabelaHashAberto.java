/* Unidade 16 - Hashing
 *
 * TABELA HASH COM ENDERECAMENTO ABERTO / REHASHING (slides 66-68).
 *
 * Todos os elementos ficam na PROPRIA tabela (sem listas externas). Quando
 * ocorre colisao, faz-se SONDAGEM LINEAR: tenta h(k), depois h(k)+1,
 * h(k)+2, ... voltando ao inicio (mod m) ate achar um slot livre.
 *
 * Funcao hash (metodo da divisao): h(k) = k mod m
 *
 * Cuidado com a REMOCAO: nao da para simplesmente esvaziar o slot, senao a
 * sondagem de outras chaves que passaram por ali quebra. Por isso existe um
 * marcador de "removido" (tombstone), diferente de "nunca usado".
 */

import java.util.Arrays;

public class TabelaHashAberto {

    private static final int VAZIO = Integer.MIN_VALUE;     // slot nunca usado
    private static final int REMOVIDO = Integer.MIN_VALUE + 1; // slot usado e depois removido

    private int[] chaves;
    private String[] valores;
    private int m;
    private int totalElementos;

    public TabelaHashAberto(int m) {
        this.m = m;
        chaves = new int[m];
        valores = new String[m];
        Arrays.fill(chaves, VAZIO);
    }

    public int hash(int chave) {
        return chave % m;
    }

    // Sondagem linear: procura, a partir de h(chave), o primeiro slot livre
    // (vazio ou removido). Retorna -1 se a tabela estiver cheia.
    public boolean inserir(int chave, String valor) {
        if (totalElementos == m) {
            return false; // tabela cheia
        }
        int inicio = hash(chave);
        for (int passo = 0; passo < m; passo++) {
            int pos = (inicio + passo) % m;
            if (chaves[pos] == VAZIO || chaves[pos] == REMOVIDO) {
                chaves[pos] = chave;
                valores[pos] = valor;
                totalElementos++;
                return true;
            }
        }
        return false;
    }

    // Retorna o slot onde a chave esta armazenada, ou -1 se nao encontrar.
    // Precisa continuar sondando ao passar por slots REMOVIDOS.
    public int localizar(int chave) {
        int inicio = hash(chave);
        for (int passo = 0; passo < m; passo++) {
            int pos = (inicio + passo) % m;
            if (chaves[pos] == VAZIO) {
                return -1; // slot vazio "de verdade" -> a chave nunca passou por aqui
            }
            if (chaves[pos] == chave) {
                return pos;
            }
            // se REMOVIDO, continua sondando
        }
        return -1;
    }

    public String buscar(int chave) {
        int pos = localizar(chave);
        return pos == -1 ? null : valores[pos];
    }

    public boolean remover(int chave) {
        int pos = localizar(chave);
        if (pos == -1) {
            return false;
        }
        chaves[pos] = REMOVIDO;
        valores[pos] = null;
        totalElementos--;
        return true;
    }

    public double fatorDeCarga() {
        return (double) totalElementos / m;
    }

    public void mostrar() {
        for (int i = 0; i < m; i++) {
            String situacao;
            if (chaves[i] == VAZIO) situacao = "vazio";
            else if (chaves[i] == REMOVIDO) situacao = "removido";
            else situacao = String.valueOf(chaves[i]);
            System.out.println("Slot " + i + " -> " + situacao);
        }
    }

    // ---------------------------------------------------------------
    // Teste - Exercicio 3 (h(k)=k%10) e Exercicio 4 (h(k)=k%11) dos slides
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Exercicio 3: h(k) = k % 10, sondagem linear ===");
        TabelaHashAberto t1 = new TabelaHashAberto(10);
        int[] chaves1 = {23, 45, 77, 11, 33, 49, 10, 4, 89, 14};

        System.out.println("Chave | Endereco calculado | Colisao? | Endereco efetivo");
        for (int chave : chaves1) {
            int calculado = t1.hash(chave);
            boolean colisao = t1.chaves[calculado] != VAZIO && t1.chaves[calculado] != REMOVIDO;
            t1.inserir(chave, "chave" + chave);
            int efetivo = t1.localizar(chave);
            System.out.printf("%5d | %19d | %8s | %d%n",
                    chave, calculado, colisao ? "S" : "N", efetivo);
        }
        System.out.println("\nTabela final:");
        t1.mostrar();
        System.out.println("Esperado: 0:10 1:11 2:89 3:23 4:33 5:45 6:4 7:77 8:14 9:49");

        System.out.println("\n=== Exercicio 4: h(k) = k % 11, sondagem linear ===");
        TabelaHashAberto t2 = new TabelaHashAberto(11);
        int[] chaves2 = {73, 15, 44, 37, 30, 59, 49, 99};
        for (int chave : chaves2) {
            t2.inserir(chave, "chave" + chave);
        }
        t2.mostrar();
        System.out.println("Esperado: 0:44 1:99 2:vazio 3:vazio 4:15 5:37 6:59 7:73 8:30 9:49 10:vazio");

        System.out.println("\n=== Remocao com tombstone ===");
        t2.remover(15);         // remove a chave que ocupava o slot 4
        t2.mostrar();
        System.out.println("buscar(37) apos remover 15 = " + t2.buscar(37)
                + "  (tem que continuar achando, mesmo passando pelo slot removido)");
    }
}
