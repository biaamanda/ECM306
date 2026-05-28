package Aula13;

/*Escrever um método que irá retornar o número de Null’s armazenados
na árvore; */

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

    public int quantidadeNulls(No no) {
        if (no == null) {
            return 1;
        }

        return quantidadeNulls(no.esq) + quantidadeNulls(no.dir);
    }

    public static void main(String[] args) {
        Ex07 arvore = new Ex07();

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

        int quantidade = arvore.quantidadeNulls(arvore.raiz);

        System.out.println("Nulls: " + quantidade);
    }
}