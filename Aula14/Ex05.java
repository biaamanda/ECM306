package Aula14;

/*Imprimir os elementos de uma árvore binária de busca, por meio da
travessia InOrder; */

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

public class Ex05 {

    No raiz;
    
    public void inOrder(No no) {

        if (no != null) {

            inOrder(no.esq);

            System.out.print(
                no.valor + " "
            );

            inOrder(no.dir);
        }
    }

    public static void main(String[] args) {

        Ex05 arvore = new Ex05();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);


        System.out.print("InOrder: ");
        arvore.inOrder(arvore.raiz);
    }
}
