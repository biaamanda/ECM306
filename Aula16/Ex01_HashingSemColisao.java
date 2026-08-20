/*
 * EXERCÍCIO 1 - Hashing Interno sem Tratamento de Colisão
 *
 * ENUNCIADO:
 * 1. Completar o método hash() no programa, utilizando a função
 *    hash h = chave mod n.
 * 2. Executar o código e avaliar sua execução.
 * 3. Responder: houve colisões? Em caso afirmativo, quantas e quais?
 * 4. Como foi feito o tratamento de colisões?
 * 5. Que sugestões apresentar para o tratamento das colisões?
 *
 * Observação: a classe Aluno e os dados originais aparecem nas páginas
 * 2 a 5 do material. Este arquivo deixa uma implementação-base para
 * testar a função de hashing.
 */

public class Ex01_HashingSemColisao {

    static int hash(int chave, int n) {
        return chave % n;
    }

    public static void main(String[] args) {
        int[] tabAluno = {10, 21, 32, 43, 54, 65, 76, 87, 98, 19};
        int n = 10;
        int[] tabela = new int[n];

        for (int i = 0; i < n; i++) {
            tabela[i] = -1;
        }

        for (int chave : tabAluno) {
            int indice = hash(chave, n);

            if (tabela[indice] != -1) {
                System.out.println("Colisão: chave " + chave
                        + " com chave " + tabela[indice]
                        + " no índice " + indice);
            } else {
                tabela[indice] = chave;
            }
        }

        System.out.println("\nTabela Hash:");
        for (int i = 0; i < tabela.length; i++) {
            System.out.println(i + " -> " + tabela[i]);
        }
    }
}
