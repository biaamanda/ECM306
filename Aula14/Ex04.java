package Aula14;

/*Imprimir os elementos de uma árvore binária de busca, por meio da
travessia PostOrder; */

class No {

    int valor;
    No esq;
    No dir;

    public No(int valor) {

        this.valor = valor;

        esq = null;
        dir = null;
    }
}

public class Ex04 {
    No raiz;

    // esquerda -> direita -> raiz
    public void postOrder(No no) {

        if (no != null) {

            postOrder(no.esq);

            postOrder(no.dir);

            System.out.print(
                no.valor + " "
            );
        }
    }

    public static void main(String[] args) {

        Ex04 arvore = new Ex04();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        System.out.print("PostOrder: ");
        arvore.postOrder(arvore.raiz);
    }
}
