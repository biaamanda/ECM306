public class SList {

    // Nó da lista
    static class No {
        int chave;
        No prox;

        public No(int chave) {
            this.chave = chave;
            this.prox = null;
        }
    }

    private No inicio;

    // Construtor
    public SList() {
        inicio = null;
    }

    // Insere uma chave no início da lista
    public void insereInicio(int chave) {

        No novo = new No(chave);

        novo.prox = inicio;

        inicio = novo;
    }

    // Imprime os elementos da lista
    public void imprime() {

        No atual = inicio;

        while (atual != null) {

            System.out.print(atual.chave + " ");

            atual = atual.prox;
        }
    }

    // Verifica se a lista está vazia
    public boolean vazia() {

        return inicio == null;
    }
}