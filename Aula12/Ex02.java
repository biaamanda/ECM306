package Aula12;

/*Dada a árvore abaixo esquematizada:
a. Executar o método que imprime os filhos do nó 5;
b. Executar o método que imprime o valor armazenado no pai do nó 8;
c. Escrever o método chamado dobraFilhos() que multiplica
por 2 todos os nós de um determinado filho e executar essa
função para o nó 8;
d. Escrever um método chamado dobraPai() que multiplica por 2
o valor armazenado no pai de um determinado nó e executar
essa função para o nó 2. */

import java.util.ArrayList;

class No {
    int valor;
    ArrayList<No> filhos;

    public No(int valor) {
        this.valor = valor;
        filhos = new ArrayList<>();
    }
}

public class Ex02 {

    No raiz;

    public void imprimeFilhos(No no) {
        //imprime os filhos de 5 -> 4 3 7
        System.out.print("Filhos " + no.valor + ": ");

        for (No filho : no.filhos) {
            System.out.print(filho.valor + " ");
        }

        System.out.println();
    } 

    public No pai(No atual, No procurado) {

        for (No filho : atual.filhos) {
            if (filho == procurado) {
                return atual;
            }
        }

        for (No filho : atual.filhos) {
            No p = pai(filho, procurado);
            if (p != null) {
                return p;
            }
        }

        return null;        
    }

    public void imprimePai(No no) {
        //imprime o valor armazenado no pai do nó 8 -> 3
        No p = pai(raiz, no);

        if (p != null) {
            System.out.println("Pai de " + no.valor + ": " + p.valor);
        } 
    }

    public void dobrarFilhos(No no) {
        for (No filho : no.filhos) {
            filho.valor *= 2;
        }

    }

    public void dobrarPai(No no) {
        No p = pai(raiz, no);
        if (p != null) {
            p.valor *= 2;
        }
    }



    public static void main(String[] args) {

        Ex02 arvore = new Ex02();

        No n5 = new No(5);
        arvore.raiz = n5;

        //nivel 1
        No n4 = new No(4);
        No n3 = new No(3);
        No n7 = new No(7);

        n5.filhos.add(n4);
        n5.filhos.add(n3);
        n5.filhos.add(n7);

        //nivel 2
        //filhos de 4
        No n1 = new No(1);
        No n2 = new No(2);

        n4.filhos.add(n1);
        n4.filhos.add(n2);

        //filhos de 3
        No n8 = new No(8);

        n3.filhos.add(n8);

        //filhos de 7
        No n0 = new No(0);
        No n6 = new No(6);

        n7.filhos.add(n0);
        n7.filhos.add(n6);

        //nivel 3: filhos de 8
        No n21 = new No(21);
        No n12 = new No(12);

        n8.filhos.add(n21);
        n8.filhos.add(n12);

        //imprimir resultados
        arvore.imprimeFilhos(n5);

        arvore.imprimePai(n8);

        arvore.dobrarFilhos(n8);
        System.out.println("Filhos de " + n8.valor + " após dobrar: ");
        arvore.imprimeFilhos(n8);

        arvore.dobrarPai(n2);
        System.out.print("Valor do pai de " + n2.valor + " após dobrar: ");
        System.out.println(n4.valor);

    }
}
