package Aula14;

/*Inserir (pedir ao usuario) um elemento inteiro k em uma dada árvore binária de busca; */

import java.util.Scanner;

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
    No raiz;

     public void inserir(No no, int k) {

        if (k < no.valor) {

            if (no.esq == null) {
                no.esq = new No(k);
            } else {
                inserir(no.esq, k);
            }

        } else {

            if (no.dir == null) {
                no.dir = new No(k);
            } else {
                inserir(no.dir, k);
            }
        }
    }

    public void inOrder(No no) {

        if (no != null) {

            inOrder(no.esq);

            System.out.print(no.valor + " ");

            inOrder(no.dir);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Ex02 arvore = new Ex02();

        // ABB inicial
        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        System.out.print("Digite o valor a inserir: ");
        int k = leia.nextInt();
        arvore.inserir(arvore.raiz, k);

        System.out.println("Arvore em ordem:");
        arvore.inOrder(arvore.raiz);

        scanner.close();
    }
}
}
