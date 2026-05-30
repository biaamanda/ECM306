package Aula14;

/*Escrever uma função que retorna a média aritmética dos valores
armazenados em uma árvore binária de busca */

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

public class Ex09 {
    No raiz;

    public int soma (No no) {
        if (no == null) {
            return 0;
        }
        return no.valor + soma(no.esq) + soma(no.dir);
    }

    public int quantidadeNos(No no) {
        if (no == null) {
            return 0;
        }
        return 1 + quantidadeNos(no.esq) + quantidadeNos(no.dir);
    }

    public double mediaValor(No no) {

        int somaTotal = soma(no);

        int quantidadeNos = quantidadeNos(no);

        return (double) somaTotal / quantidadeNos;

    }

    public static void main(String[] args) {
        Ex09 arvore = new Ex09();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        arvore.raiz.dir.dir.dir.dir.esq = new No(5);

        double media = arvore.mediaValor(arvore.raiz);

        System.out.println("Media: " + media);
    }
}
