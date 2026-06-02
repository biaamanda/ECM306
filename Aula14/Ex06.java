package Aula14;

/*Escrever uma função que verifica se um dado inteiro K está presente na
árvore binária de busca; */

import java.util.Scanner;

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

public class Ex06 {
    No raiz;

    public boolean verificaPresenca(No no, int k) {
        if (no == null) {
            return false;
        }
        if (no.valor == k) {
            return true;
        }
        if (k < no.valor) {
            return verificaPresenca(no.esq, k);
        } else {
            return verificaPresenca(no.dir, k);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Ex06 arvore = new Ex06();

        arvore.raiz = new No(3);

        arvore.raiz.dir = new No(7);

        arvore.raiz.dir.esq = new No(5);
        arvore.raiz.dir.dir = new No(8);

        arvore.raiz.dir.dir.dir = new No(9);

        arvore.raiz.dir.dir.dir.dir = new No(10);

        System.out.print("Digite um número para procurar: ");
        int k = scanner.nextInt();

        boolean presente = arvore.verificaPresenca(arvore.raiz, k);

        if (presente) {
            System.out.println("O número " + k + " está presente na árvore.");
        } else {
            System.out.println("O número " + k + " nao está presente na árvore.");
        }

        scanner.close();
    }
}
