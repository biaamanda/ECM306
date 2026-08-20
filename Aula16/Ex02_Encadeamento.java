/*
 * EXERCÍCIO 2 - Hashing Interno com Tratamento de Colisão - Encadeamento
 *
 * ENUNCIADO:
 * Parte B:
 * Considere 20 chaves, com valores de 0 a 19, e uma tabela Hash
 * com 10 elementos. Usar o método da divisão e encadeamento.
 *
 * 1. Criar TestHash e SList no mesmo pacote.
 * 2. Criar tabKeys com capacidade 20, desconsiderando a posição 0;
 *    chaves válidas: 1 a 19.
 * 3. Criar tabHash com capacidade 10, contendo listas ligadas.
 * 4. Escrever hash().
 * 5. Carregar tabHash usando insereInicio().
 * 6. Imprimir as listas em cada posição.
 * 7. Modificar para 100.000 chaves (1 a 99.999) e tabela com 1000 posições.
 */

import java.util.LinkedList;

public class Ex02_Encadeamento {

    static int hash(int chave, int n) {
        return chave % n;
    }

    public static void main(String[] args) {
        int[] tabKeys = new int[20];

        for (int i = 1; i < tabKeys.length; i++) {
            tabKeys[i] = i;
        }

        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] tabHash = new LinkedList[10];

        for (int i = 0; i < tabHash.length; i++) {
            tabHash[i] = new LinkedList<>();
        }

        for (int chave = 1; chave <= 19; chave++) {
            int indice = hash(tabKeys[chave], tabHash.length);
            tabHash[indice].addFirst(tabKeys[chave]);
        }

        System.out.println("Tabela Hash com encadeamento:");

        for (int i = 0; i < tabHash.length; i++) {
            System.out.print(i + " -> ");

            for (int chave : tabHash[i]) {
                System.out.print(chave + " ");
            }

            System.out.println();
        }
    }
}
