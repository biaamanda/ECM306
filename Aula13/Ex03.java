package Aula13;

/*Escrever um método que verifica se um dado valor inteiro K está
presente na árvore */

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

public class Ex03 {
    No raiz;

    public boolean busca(No no,int k) {
        if(no == null) {
            return false;
        }
        
        if(no.valor == k) {
            return true;
        }
        return busca(no.esq, k) || busca(no.dir, k);
    }

    public static void main(String[] args) {

        Ex03 arvore = new Ex03();

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

        int k = 3;

        if (arvore.busca(arvore.raiz, k)) {
            System.out.println("Valor " + k + " encontrado na árvore.");
        } else {
            System.out.println("Valor " + k + " não encontrado na árvore.");
        }
    }
}