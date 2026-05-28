package Aula13;

/* Escrever um método que irá retornar a média aritmética dos valores
armazenados na árvore; */

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

public class Ex06 {
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
        Ex06 arvore = new Ex06();

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

        double media = arvore.mediaValor(arvore.raiz);

        System.out.println("Media: " + media);
    }
}