package Aula14;

/*Escrever uma função que retorna o menor elemento de uma árvore
binária de busca; */

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

public class Ex07 {
    No raiz;

    public int menorValor(No no) {
        if (no == null) {
            return Integer.MAX_VALUE;
        }

        int menorEsq = menorValor(no.esq);
        int menorDir = menorValor(no.dir);

        int menor = no.valor;

        if (menorEsq < menor) {
            menor = menorEsq;
        }
        if (menorDir < menor) {
            menor = menorDir;
        }

        return menor;

    }

    public static void main(String[] args) {
        Ex07 arvore = new Ex07();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        arvore.raiz.dir.dir.dir.dir.esq = new No(5);

        int menor = arvore.menorValor(arvore.raiz);

        System.out.println("Menor valor: " + menor);
    }
}