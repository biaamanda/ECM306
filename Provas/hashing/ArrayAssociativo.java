/* Unidade 16 - Hashing
 *
 * ARRAY ASSOCIATIVO (acesso direto), estilo dos slides 5-25.
 *
 * Ideia: quando o universo de chaves U e pequeno, aloca-se um array do
 * tamanho de U e a propria chave vira o indice do array: A[chave].
 * Custo de busca/insercao: O(1) - acesso DIRETO, sem nenhum calculo.
 *
 * Problema (slides 27-33): se o universo de chaves for gigante (ex.: CPF,
 * 11 digitos) mas so um punhado de chaves for realmente usado, alocar um
 * array do tamanho do universo e inviavel (memoria demais).
 */

public class ArrayAssociativo {

    private String[] tabela;   // tabela[chave] = nome do estudante
    private int capacidade;    // tamanho do universo de chaves (0..capacidade-1)

    public ArrayAssociativo(int capacidade) {
        this.capacidade = capacidade;
        tabela = new String[capacidade];
    }

    // Acesso DIRETO: nao ha calculo nenhum, a chave E o indice.
    public void inserir(int chave, String valor) {
        validarChave(chave);
        tabela[chave] = valor;
    }

    public String buscar(int chave) {
        validarChave(chave);
        return tabela[chave];
    }

    public void remover(int chave) {
        validarChave(chave);
        tabela[chave] = null;
    }

    private void validarChave(int chave) {
        if (chave < 0 || chave >= capacidade) {
            throw new RuntimeException("Chave fora do universo [0.." + (capacidade - 1) + "]");
        }
    }

    public void mostrar() {
        for (int i = 0; i < capacidade; i++) {
            if (tabela[i] != null) {
                System.out.println("Indice " + i + " -> " + tabela[i]);
            }
        }
    }

    public static void main(String[] args) {
        // Exemplo dos slides: escola com codigo de matricula de 2 digitos (0..99).
        ArrayAssociativo escola = new ArrayAssociativo(100);

        escola.inserir(75, "Estudante 75");
        escola.inserir(2, "Estudante 2");
        escola.inserir(55, "Paulo de Souza Alves"); // slide 5
        escola.inserir(13, "Estudante 13");

        System.out.println("--- Array associativo (universo pequeno: 0..99) ---");
        escola.mostrar();

        System.out.println("\nbuscar(55) = " + escola.buscar(55));
        System.out.println("buscar(10) = " + escola.buscar(10)); // null, ninguem cadastrado

        // Por que isso NAO funciona bem com CPF (slides 27-32):
        System.out.println("\n--- Por que array associativo falha com CPF ---");
        long universoCPF = 100_000_000_000L; // 11 digitos -> 10^11 posicoes
        long chavesReais = 40;               // poucas chaves de fato usadas
        long bytesPorRegistro = 132;
        System.out.println("Universo de chaves (CPF): " + universoCPF);
        System.out.println("Chaves realmente usadas : " + chavesReais);
        System.out.println("Memoria necessaria       : "
                + (universoCPF * bytesPorRegistro) + " bytes (~13 TB, quase tudo vazio)");
        System.out.println("=> Nesse caso o certo e usar HASHING (ver TabelaHashEncadeamento / TabelaHashAberto).");
    }
}
