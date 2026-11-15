/* Unidade 16 - Hashing
 *
 * TABELA HASH COM TRATAMENTO DE COLISAO POR ENCADEAMENTO (slides 55-64).
 *
 * Cada slot da tabela guarda a lista ligada de todas as chaves que
 * colidiram naquele indice (mesmo h(k)).
 *
 * Funcao hash (metodo da divisao): h(k) = k mod m
 *
 * Este arquivo insere no FIM da lista (preserva a ordem de chegada), que e
 * como o professor desenha nos slides 61-64. Ligar no INICIO da lista
 * tambem e valido (e da O(1) garantido na insercao) - so muda a ordem em
 * que os elementos aparecem dentro do slot.
 */

public class TabelaHashEncadeamento {

    // No de uma lista ligada: guarda a chave e o valor associado.
    private static class No {
        int chave;
        String valor;
        No prox;

        No(int chave, String valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    private No[] tabela;
    private int m;               // tamanho da tabela (numero de slots)
    private int totalElementos;  // quantidade de chaves armazenadas

    public TabelaHashEncadeamento(int m) {
        this.m = m;
        tabela = new No[m];
    }

    // Funcao hash - metodo da divisao (slide 43)
    public int hash(int chave) {
        return chave % m;
    }

    // Insere (chave, valor) no FIM da lista do slot h(chave).
    public void inserir(int chave, String valor) {
        int i = hash(chave);
        No novo = new No(chave, valor);

        if (tabela[i] == null) {
            tabela[i] = novo;
        } else {
            No atual = tabela[i];
            while (atual.prox != null) {
                atual = atual.prox;
            }
            atual.prox = novo;
        }
        totalElementos++;
    }

    // Busca o valor associado a uma chave, percorrendo a lista do slot h(chave).
    public String buscar(int chave) {
        No atual = tabela[hash(chave)];
        while (atual != null) {
            if (atual.chave == chave) {
                return atual.valor;
            }
            atual = atual.prox;
        }
        return null;
    }

    // Numero de comparacoes feitas ate encontrar a chave (>=1), ou -1 se nao existir.
    public int acessosParaBuscar(int chave) {
        No atual = tabela[hash(chave)];
        int acessos = 0;
        while (atual != null) {
            acessos++;
            if (atual.chave == chave) {
                return acessos;
            }
            atual = atual.prox;
        }
        return -1;
    }

    // Remove a chave da lista do seu slot. Custo proporcional ao tamanho da lista.
    public boolean remover(int chave) {
        int i = hash(chave);
        No atual = tabela[i];
        No anterior = null;

        while (atual != null) {
            if (atual.chave == chave) {
                if (anterior == null) {
                    tabela[i] = atual.prox;
                } else {
                    anterior.prox = atual.prox;
                }
                totalElementos--;
                return true;
            }
            anterior = atual;
            atual = atual.prox;
        }
        return false;
    }

    public double fatorDeCarga() {
        return (double) totalElementos / m;
    }

    public void mostrar() {
        for (int i = 0; i < m; i++) {
            System.out.print("Slot " + i + " -> ");
            No atual = tabela[i];
            while (atual != null) {
                System.out.print(atual.chave + " ");
                atual = atual.prox;
            }
            System.out.println();
        }
    }

    // ---------------------------------------------------------------
    // Teste - Exercicios 1 e 2 do simulado (slides 61-64)
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        TabelaHashEncadeamento tabela = new TabelaHashEncadeamento(10);
        int[] chaves = {23, 45, 77, 11, 33, 49, 10, 4, 89, 14};

        System.out.println("--- Exercicio 1: insercao com h(k) = k % 10 ---");
        System.out.println("Chave | Endereco calculado | Colisao?");
        for (int chave : chaves) {
            int indice = tabela.hash(chave);
            boolean colisao = tabela.tabela[indice] != null;
            System.out.printf("%5d | %19d | %s%n", chave, indice, colisao ? "S" : "N");
            tabela.inserir(chave, "chave" + chave);
        }

        System.out.println("\nTabela final (cada slot com sua lista encadeada):");
        tabela.mostrar();
        System.out.println("Fator de carga = " + tabela.fatorDeCarga());

        System.out.println("\n--- Exercicio 2: numero de acessos para buscar cada chave ---");
        int total = 0;
        for (int chave : chaves) {
            int acessos = tabela.acessosParaBuscar(chave);
            total += acessos;
            System.out.println("  chave " + chave + " -> " + acessos + " acesso(s)");
        }
        System.out.printf("Total = %d, media = %.1f%n", total, (double) total / chaves.length);

        System.out.println("\n--- Remover 33 e buscar de novo ---");
        tabela.remover(33);
        tabela.mostrar();
        System.out.println("buscar(33) apos remover = " + tabela.buscar(33));
    }
}
