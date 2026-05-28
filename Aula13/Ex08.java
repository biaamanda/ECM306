package Aula13;

/*Escrever um método que irá retornar a quantidade de nós armazenados
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

public class Ex08 {
    No raiz;

    public int quantidadeNos(No no) {
        if (no == null) {
            return 0;
        }

        return 1 + quantidadeNos(no.esq) + quantidadeNos(no.dir);
    }

    public static void main(String[] args) {
        Ex08 arvore = new Ex08();

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

        int nos = arvore.quantidadeNos(arvore.raiz);

        System.out.println("Nós: " + nos);
    }
}
