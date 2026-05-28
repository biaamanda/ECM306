package Aula13;

/*Escrever um método que irá retornar a quantidade de folhas
armazenadas na árvore; */

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

    public int quantidadeFolhas(No no) {
        if (no == null) {
            return 0;
        }

        if (no.esq == null && no.dir == null) {
            return 1;
        }

        return quantidadeFolhas(no.esq) + quantidadeFolhas(no.dir);
    }

    public static void main(String[] args) {
        Ex09 arvore = new Ex09();

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

        int folhas = arvore.quantidadeFolhas(arvore.raiz);

        System.out.println("Folhas: " + folhas);
    }
}
