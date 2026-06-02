package Aula14;

/*Implementar uma árvore binária de busca, a partir do vetor de inteiros
{3, 7, 8, 9, 10, 5}; */

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

public class Ex01 {
    No raiz;

    public static void main(String[] args) {
        
        Ex01 arvore = new Ex01();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

    }
}
