package Aula12;

/*Considere uma árvore que armazena valores inteiros em um nó
com a estrutura de nós definida em aula.
a. Escrever um método chamado imprimeFilhos() que imprime
os valores inteiros armazenados nos filhos de um determinado nó;
b. Escrever um método chamado pai() que é aplicado a um nó e
retorna a referência do nó que corresponde ao seu pai;
c. Escrever um método chamado imprimePai() que imprime o
elemento inteiro armazenado no pai de um determinado nó;
d. Escrever um método chamado ehInterno() que retorna um
valor booleano true se o nó foi interno, ou false se o nó for externo;
e. Escrever um método chamado imprimeFilhosFolhas() que
imprime os valores inteiros armazenados nos filhos de um
determinado nó e que também são folhas;
f. Escrever um método chamado preorder() que quando
aplicado à um determinado nó da árvore imprime o percurso
preorder a partir do nó considerado;
g. Escrever um método chamado posorder() que quando
aplicado à um determinado nó da árvore imprime o percurso
posorder a partir do nó considerado. */

class No {
    int valor;
    No esq;
    No dir;

    public No(int valor) {
        this.valor = valor;
        this.esq = null;
        this.dir = null;
    }
}

public class Ex01 {

    No raiz;

    public void imprimeFilhos(No no) {
        //valores inteiros armazenados nos filhos

        System.out.print("Filhos " + no.valor + ": ");

        if(no.esq != null) {
            System.out.print(no.esq.valor + " ");
        }

        if(no.dir != null) {
            System.out.print(no.dir.valor + " ");
        }

        System.out.println();
    }

    public No pai(No atual, No procurado) {
        //retorna a referência do nó
        if (atual == null) {
            return null;
        }

        if (atual.esq == procurado || atual.dir == procurado) {
            return atual;
        }

        No p = pai(atual.esq, procurado);

        if (p != null) {
            return p;
        }

        return pai(atual.dir, procurado);        
    }

    public void imprimePai(No no) {
        //elemento que armazena o pai
        No p = pai(raiz, no);

        if (p != null) {
            System.out.println("Pai de " + no.valor + ": " + p.valor);
        } else {
            System.out.println("Nó " + no.valor + " não tem pai.");
        }
    }

    public boolean ehInterno(No no) {
        //true se o nó for interno, ou false se o nó for externo
        if (no == null) {
            return false;
        }

        return no.esq != null || no.dir != null;
    }

    public void imprimeFilhosFolhas(No no) {
        //imprime os valores inteiros armazenados nos filhos que são folhas
        System.out.print("Filhos folhas de " + no.valor + ": ");

        if (no.esq != null && no.esq.esq == null && no.esq.dir == null) {
            System.out.print(no.esq.valor + " ");
        }

        if (no.dir != null && no.dir.esq == null && no.dir.dir == null) {
            System.out.print(no.dir.valor + " ");
        }

        System.out.println();
    }

    public void preorder(No no) {
        //imprime o percurso preorder a partir do nó
        if (no != null) {
            System.out.print(no.valor + " ");
            preorder(no.esq);
            preorder(no.dir);
        }
    }

    public void posorder(No no) {
        //imprime o percurso posorder a partir do nó
        if (no != null) {
            posorder(no.esq);
            posorder(no.dir);
            System.out.print(no.valor + " ");
        }
    }

    public static void main(String[] args) {
        
        Ex01 arvore = new Ex01();

        arvore.raiz = new No(0);

        arvore.raiz.esq = new No(1);
        arvore.raiz.dir = new No(2);

        arvore.raiz.dir.esq = new No(3);
        arvore.raiz.dir.dir = new No(4);

        arvore.raiz.dir.esq.esq = new No(5);
        arvore.raiz.dir.esq.dir = new No(6);

        arvore.imprimeFilhos(arvore.raiz);
        arvore.imprimePai(arvore.raiz.dir.esq);

        System.out.println(arvore.ehInterno(arvore.raiz.dir));

        arvore.imprimeFilhosFolhas(arvore.raiz.dir.esq);

        System.out.print("Preorder: ");
        arvore.preorder(arvore.raiz);
        System.out.println();
        
        System.out.print("Posorder: ");
        arvore.posorder(arvore.raiz);
        System.out.println();
    }
}
