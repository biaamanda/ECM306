package Aula13;

/*Desenvolver um programa em Java que implemente a seguinte Árvore Binária: */


class NodeTree {

    int valor;

    NodeTree parent;
    NodeTree firstChild;
    NodeTree next;

    public NodeTree(int valor) {

        this.valor = valor;

        parent = null;
        firstChild = null;
        next = null;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return firstChild == null;
    }

    public boolean isInternal() {
        return firstChild != null;
    }

    public void imprimeFilhos() {

        System.out.print("Filhos de " + valor + ": ");

        NodeTree trab = firstChild;

        while (trab != null) {
            System.out.print(trab.valor + " ");

            trab = trab.next;
        }

        System.out.println();
    }

    public void descendentes() {

        NodeTree trab = firstChild;

        while (trab != null) {

            System.out.print(trab.valor + " ");

            trab.descendentes();
            trab = trab.next;
        }
    }

    public void preorder() {

        System.out.print(valor + " ");

        NodeTree trab = firstChild;

        while (trab != null) {
            trab.preorder();
            trab = trab.next;
        }
    }

}

public class Ex01 {

    public static void main(String[] args) {

        // raiz
        NodeTree root = new NodeTree(0);

        // nós
        NodeTree no1 = new NodeTree(1);
        NodeTree no2 = new NodeTree(2);
        NodeTree no3 = new NodeTree(3);
        NodeTree no4 = new NodeTree(4);
        NodeTree no5 = new NodeTree(5);
        NodeTree no6 = new NodeTree(6);

        // relacionamentos

        // filhos de 0
        root.firstChild = no1;

        no1.parent = root;
        no1.next = no2;

        no2.parent = root;

        // filhos de 2
        no2.firstChild = no3;

        no3.parent = no2;
        no3.next = no4;

        no4.parent = no2;

        // filhos de 3
        no3.firstChild = no5;

        no5.parent = no3;
        no5.next = no6;

        no6.parent = no3;

        // raiz
        System.out.println("Raiz: " + root.valor);

        // folhas
        System.out.println("Nó 1 é folha? " + no1.isLeaf());

        System.out.println("Nó 2 é folha? " + no2.isLeaf());

        // internos
        System.out.println("Nó 3 é interno? " + no3.isInternal());

        // pai
        System.out.println("Pai de 6: " + no6.parent.valor);

        // filhos
        no2.imprimeFilhos();

        // descendentes
        System.out.print("Descendentes de 2: ");

        no2.descendentes();

        System.out.println();

        // preorder
        System.out.print("Preorder: ");

        root.preorder();

        System.out.println();
    }
}