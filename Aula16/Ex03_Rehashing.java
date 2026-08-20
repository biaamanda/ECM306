/*
 * PARTE B- Hashing Interno com Tratamento de Colisão - Rehashing
 *
 * Considere uma aplicação que utiliza uma tabela hash para armazenar
 * empregados de uma grande rede de empresas, com milhares de
 * Empregados. Considere que a tabela hash, a ser criada em memória, terá
 * capacidade para 10 empregados e irá armazenar apenas o código do
 * empregado, ou seja, sua chave. Será empregada uma função hash definida
 * pelo método da divisão e o tratamento de colisões será feito pelo método do
 * endereçamento aberto, ou rehashing.
 * 
 * 1. Escrever uma classe TestHash, com o método main() para execução do
 * código. O método deve, inicialmente, criar a tabela hash, representada
 * por um array chamado tabHash, que irá conter as chaves dos empregados;
 * 
 * 2. Considere inicialmente as chaves 23, 45, 77, 11, 33, 49, 10, 4, 89 e 14,
 * que deverão ser carregadas na tabela hash;
 * 
 * 3. O método main() terá o seguinte código inicial:
 *      public static void main(String[] args) {
 *        int[] tabChaves = new int{23, 45, 77, 11, 33, 49, 10, 4, 89, 14};
 *        int[] tabHash = new int[10];
 *      }
 * 
 * 4. Escrever o método hash(), que receberá uma chave como parâmetro e
 * retornará o índice correspondente a essa chave na tabela hash.
 * Considerar o método da divisão para a escrita do código da função hash:
 *   int indiceHash = hash(codigoEmpregado);
 * 
 * 5. Escrever o método rehashing(), que recebe como parâmetro o endereço
 * da tabela hash e a chave de colisão. O método rehashing() deverá
 * percorrer a tabela hash passada como parâmetro e retornar a primeira
 * posição da tabela hash que esteja livre para armazenar a chave. Caso a
 * tabela não tenha índices livres, retornar null;
 * 
 * 6. Uma vez conhecido o índice da tabela hash correspondente ao
 * empregado passado como parâmetro, no método main() proceder à
 * gravação da chave na tabela hash no índice retornado pela função hash.
 * Caso a posição da tabela hash já estiver preenchida com outra chave,
 * recalcular o índice por meio da chamada do método rehashing().

 * 7. Uma vez conhecido o índice da tabela hash correspondente ao
 * empregado passado como parâmetro, no método main() proceder à
 * gravação da chave na tabela hash no índice retornado pela função hash.
 * Caso a posição da tabela hash já estiver preenchida com outra chave,
 * recalcular o índice por meio da chamada do método rehashing().
 * Proceder à gravação de todas as chaves e imprimi-las:
 *  public static int rehashing(int[] tabHash, int indice) {
 *      for(int i = indice + 1; i < tabHash.length; i++) {
 *         if(tabHash[i] == null) {
 *            return i;
 *        }
 *      for(int i = 0; i < indice; i++) {
 *        if(tabHash[i] == null) {
 *           return i;
 *      }
 *      return null;
 * }
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
