package Aula14;

/*Escrever uma função que retorna a altura de uma árvore binária de
busca; */

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

public class Ex10 {
    No raiz;

    public int altura(No no) {
        if (no == null) {
            return 0;
        }

        if (no.esq == null && no.dir == null) {
            return 0;
        }

        int alturaEsq = altura(no.esq);
        int alturaDir = altura(no.dir);

        return (alturaEsq > alturaDir) ? alturaEsq + 1 : alturaDir + 1;
    }

    public static void main(String[] args) {
        Ex10 arvore = new Ex10();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        int altura = arvore.altura(arvore.raiz);

        System.out.println("Altura: " + altura);
    }
}