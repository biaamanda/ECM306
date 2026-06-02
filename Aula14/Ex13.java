package Aula14;

/*Escrever uma função que retorna a soma dos elementos armazenados
em uma árvore binária de busca */
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

public class Ex13 {
    No raiz;

    public int soma(No no) {
        if (no == null) {
            return 0;
        }

        return no.valor + soma(no.esq) + soma(no.dir);
    }
    public static void main(String[] args) {
        
        Ex13 arvore = new Ex13();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        System.out.println("Soma: " + arvore.soma(arvore.raiz));

    }
}
