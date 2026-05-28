package Aula13;

/*Escrever um método que irá retornar a altura da árvore. */

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
            return 1;
        }

        int alturaEsq = altura(no.esq);
        int alturaDir = altura(no.dir);

        return (alturaEsq > alturaDir) ? alturaEsq + 1 : alturaDir + 1;
    }

    public static void main(String[] args) {
        Ex10 arvore = new Ex10();

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

        int altura = arvore.altura(arvore.raiz);

        System.out.println("Altura: " + altura);
    }
}