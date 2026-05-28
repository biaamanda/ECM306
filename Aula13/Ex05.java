package Aula13;

/*Escrever um método que irá retornar o menor valor armazenado na
árvore; */


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

public class Ex05 {
    No raiz;

    public int menorValor(No no) {
        if (no == null) {
            return Integer.MAX_VALUE;
        }

        int menorEsq = menorValor(no.esq);
        int menorDir = menorValor(no.dir);

        int menor = no.valor; //compara o valor do nó atual com os menores valores encontrados nas subárvores esquerda e direita

        if (menorEsq < menor) {
            menor = menorEsq;
        }
        if (menorDir < menor) {
            menor = menorDir;
        }

        return menor;

    }

    public static void main(String[] args) {
        Ex05 arvore = new Ex05();

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

        int menor = arvore.menorValor(arvore.raiz);

        System.out.println("Menor valor: " + menor);
    }
}
