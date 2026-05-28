package Aula13;

/*Escrever um método que irá retornar o maior valor armazenado na
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

public class Ex04 {
    No raiz;

    public int maiorValor(No no) {
        if (no == null) {
            return Integer.MIN_VALUE;
        }

        int maiorEsq = maiorValor(no.esq);
        int maiorDir = maiorValor(no.dir);

        int maior = no.valor; //compara o valor do nó atual com os maiores valores encontrados nas subárvores esquerda e direita

        if (maiorEsq > maior) {
            maior = maiorEsq;
        }
        if (maiorDir > maior) {
            maior = maiorDir;
        }

        return maior;

    }

    public static void main(String[] args) {
        Ex04 arvore = new Ex04();

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

        int maior = arvore.maiorValor(arvore.raiz);

        System.out.println("Maior valor: " + maior);
    }
}
