package Aula13;

/*Percorrer a árvore binária, imprimindo os valores dos nós segundo as
estratégias de busca: preOrder, postOrder e inOrder; */

/*
          0
         / \
        1   2
           / \
          3   4
         / \
        5   6
*/

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

public class Ex02 {

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

    // inorder
    // esquerda -> raiz -> direita
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

        Ex02 arvore = new Ex02();

        // raiz
        arvore.raiz = new No(0);

        // nível 1
        arvore.raiz.esq = new No(1);
        arvore.raiz.dir = new No(2);

        // nível 2
        arvore.raiz.dir.esq = new No(3);
        arvore.raiz.dir.dir = new No(4);

        // nível 3
        arvore.raiz.dir.esq.esq = new No(5);
        arvore.raiz.dir.esq.dir = new No(6);

        
        System.out.print("PreOrder: ");
        arvore.preOrder(arvore.raiz);

        System.out.println();

        System.out.print("PostOrder: ");
        arvore.postOrder(arvore.raiz);

        System.out.println();

        System.out.print("InOrder: ");
        arvore.inOrder(arvore.raiz);
    }
}