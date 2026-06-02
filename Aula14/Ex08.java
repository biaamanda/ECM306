package Aula14;

/*Escrever uma função que retorna a quantidade de nós presentes em
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

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        int nos = arvore.quantidadeNos(arvore.raiz);

        System.out.println("Nós: " + nos);
    }
}