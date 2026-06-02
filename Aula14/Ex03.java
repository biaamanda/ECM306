package Aula14;

/*Imprimir os elementos de uma árvore binária de busca, por meio da
travessia PreOrder; */

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
public class Ex03 {
    No raiz;
    
    // raiz -> esquerda -> direita
    public void preOrder(No no) {

        if (no != null) {

            System.out.print(
                no.valor + " "
            );

            preOrder(no.esq);

            preOrder(no.dir);
        }
    }


    public static void main(String[] args) {
        
        Ex03 arvore = new Ex03();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        System.out.print("PreOrder: ");
        arvore.preOrder(arvore.raiz);

    }
}
