public class TestHash {

    public static int hash(int key) {

        return key % 10;
    }

    public static void main(String[] args) {

        int[] tabKeys = new int[20];

        for (int i = 1; i < tabKeys.length; i++) {

            tabKeys[i] = i;
        }

        SList[] tabHash = new SList[10];


        // Inicializa as listas
        for (int i = 0; i < tabHash.length; i++) {

            tabHash[i] = new SList();
        }

        for (int chave = 1; chave <= 19; chave++) {

            int indice = hash(tabKeys[chave]);
            /*
             * Insere a chave no início da lista.
             *
             * Se já existir uma chave nessa posição,
             * ela será adicionada à mesma lista.
             */

            tabHash[indice].insereInicio(tabKeys[chave]);
        }

        System.out.println("Tabela Hash com encadeamento:");
        System.out.println();

        for (int i = 0; i < tabHash.length; i++) {

            System.out.print("Slot " + i + " -> ");

            tabHash[i].imprime();

            System.out.println();
        }
    }
}