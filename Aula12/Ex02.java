package Aula12;

/*Dada a árvore abaixo esquematizada:
a. Executar o método que imprime os filhos do nó 5;
b. Executar o método que imprime o valor armazenado no pai do
nó 8;
c. Escrever o método chamado dobraFilhos() que multiplica
por 2 todos os nós de um determinado filho e executar essa
função para o nó 8;
d. Escrever um método chamado dobraPai() que multiplica por 2
o valor armazenado no pai de um determinado nó e executar
essa função para o nó 2. */

class No {
    int valor;
    No esq;
    No dir;

    public No(int valor) {
        this.valor = valor;
        this.esq = null;
        this.dir = null;
    }
}

public class Ex02 {
    public static void main(String[] args) {

        Ex02 arvore = new Ex02();

        arvore.raiz = new No(5);
    }
}
