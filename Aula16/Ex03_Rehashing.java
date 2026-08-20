/*
 * EXERCÍCIO 3 - Hashing Interno com Tratamento de Colisão - Rehashing
 *
 * ENUNCIADO:
 * Considere uma tabela Hash para empregados com capacidade para 10
 * empregados. Armazenar apenas o código do empregado.
 *
 * 1. Criar TestHash com main().
 * 2. Carregar inicialmente as chaves:
 *    23, 45, 77, 11, 33, 49, 10, 4, 89 e 14.
 * 3. Criar a tabela tabHash.
 * 4. Escrever hash() pelo método da divisão.
 * 5. Escrever rehashing(), que recebe a tabela e a chave de colisão
 *    e retorna a primeira posição livre; se não houver, retornar -1.
 * 6. Inserir as chaves usando hash() e, em caso de colisão, rehashing().
 * 7. Imprimir todas as chaves.
 */

public class Ex03_Rehashing {

    static int hash(int chave, int n) {
        return chave % n;
    }

    static int rehashing(int[] tabHash, int chave) {

        int indiceInicial = hash(chave, tabHash.length);

        for (int i = 0; i < tabHash.length; i++) {
            int indice = (indiceInicial + i) % tabHash.length;

            if (tabHash[indice] == -1) {
                return indice;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] chaves = {23, 45, 77, 11, 33, 49, 10, 4, 89, 14};

        int[] tabHash = new int[10];

        for (int i = 0; i < tabHash.length; i++) {
            tabHash[i] = -1;
        }

        for (int chave : chaves) {

            int indice = hash(chave, tabHash.length);

            if (tabHash[indice] == -1) {
                tabHash[indice] = chave;
            } else {
                int novoIndice = rehashing(tabHash, chave);

                if (novoIndice != -1) {
                    tabHash[novoIndice] = chave;
                } else {
                    System.out.println(
                            "Tabela cheia. Não foi possível inserir: " + chave);
                }
            }
        }

        System.out.println("Tabela Hash com Rehashing:");

        for (int i = 0; i < tabHash.length; i++) {
            System.out.println(i + " -> " + tabHash[i]);
        }
    }
}
