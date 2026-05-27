package Aula12;

/*Dada a árvore abaixo esquematizada:
a. Executar o método que imprime os filhos do nó 5;
b. Executar o método que imprime o valor armazenado no pai do nó 8;
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

    public void implimeFilhos() {
        //imprime os filhos de 5 -> 4 3 7
    } 

    public void imprimePai() {
        //imprime o valor armazenado no pai do nó 8 -> 3
    }

    public void dobrarFilhos() {

    }

    public void dobrarPai() {

    }


    public static void main(String[] args) {

        Ex02 arvore = new Ex02();

        arvore.raiz = new No(5);

        //nivel 1
        arvore.raiz.esq = new No(4);
        arvore.raiz     = new No(3)
        arvore.raiz.dir = new No(7);

        //nivel 2
        //filhos de 4
        arvore.raiz.esq.esq = new No(1);
        arvore.raiz.esq.dir = new No(2;

        //filhos de 3

        //filhos de 7
        arvore.raiz.dir.esq = new No(0);
        arvore.raiz.dir.dir = new No(6);
        

        //nivel 3: filhos de 8
        
    }
}
