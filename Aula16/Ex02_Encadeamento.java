/*
 * PARTE B - Hashing Interno com Tratamento de Colisão - Encadeamento
 *
 * Considere uma aplicação que utiliza 20 chaves, com valores de 0 até 19. Para
 * essa aplicação será construída uma tabela Hash com 10 elementos. Será
 * empregada uma função hash definida pelo método da divisão e o
 * tratamento de colisões será feito pelo método do encadeamento (listas
 * ligadas irão absorver os elementos de colisão)
 *
 * 1. Armazenando num mesmo pacote, escreva uma classe chamada
 * TestHash e a classe SList referente à implementação de Listas
 * Simplesmente Encadeadas. A classe TestHash deve conter o método
 * main() da aplicação a ser executada;
 * 
 * 2. No método main(), definir um array chamado tabKeys com capacidade
 * para armazenar 20 chaves. Cada chave corresponde a um valor inteiro.
 * Desconsiderar a primeira posição do array, visto que a aplicação irá
 * considerar chaves válidas entre 1 e 19 no intervalo de chaves da
 * aplicação. Em cada posição do array, deve estar armazenado o valor
 * correspondente da chave (array associativo).
 * 
 * 3. No método main(), definir um array chamado tabHash com capacidade
 * para armazenar 10 chaves. Em cada posição do array, deve estar
 * armazenado o endereço de uma lista ligada que conterá a chave
 * retornada pelo método hash() com as suas respectivas colisões.
 * Inicializar essa tabela com listas ligadas inicialmente vazias (referências
 * às listas devem ter valores null);
 * 
 * 4. Escrever o código do método hash():
 *   public static int hash(int key) {
 *     return key % 10;
 *   }
 * 
 * 5. No método main(), escrever o código para a carga da Tabela Hash. Em
 * cada posição de tabHash, deverá ser inserida a chave retornada pelo
 * método hash(). Para inserção da chave em tabHash, chamar o método
 * insereInicio() existente na classe SList correspondente às listas ligadas;
 * 
 * 6. No método main(), escrever o código para imprimir em cada posição de
 * TabHash, a lista com as chaves armazenadas (colisões);
 * 
 * 7. Modificar o exercício, para um total de 100.000 chaves, com valores de
 * 1 a 99.999. Para esse universo de chaves, considerar a tabela hash com
 * capacidade para armazenar 1000 chaves.
 *
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
