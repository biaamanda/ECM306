package Aula14;

/*Escrever uma função que retorna a quantidade de NULL’s presentes em
uma árvore binária de busca; */

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

public class Ex11 {
    No raiz;

    public int quantidadeNulls(No no) {
        if (no == null) {
            return 1;
        }

        return quantidadeNulls(no.esq) + quantidadeNulls(no.dir);
    }

    public static void main(String[] args) {
        Ex11 arvore = new Ex11();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        int quantidade = arvore.quantidadeNulls(arvore.raiz);

        System.out.println("Nulls: " + quantidade);
    }
}