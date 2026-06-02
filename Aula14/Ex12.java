package Aula14;

/*Escrever uma função que imprime os valores da árvore binária de busca
que são múltiplos de dois; */

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

public class Ex12 {
    No raiz;

    public void multiplosDois(No no) {
        if (no != null) {
            if (no.valor % 2 == 0) {
                System.out.print(no.valor + " ");
            }
            multiplosDois(no.esq);
            multiplosDois(no.dir);
        }
    }
    public static void main(String[] args) {
        
        Ex12 arvore = new Ex12();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        System.out.print("Múltiplos de 2: ");
        arvore.multiplosDois(arvore.raiz);

    }
}
