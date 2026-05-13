/*1. De acordo com Cormen - Segunda Edição, Página 170, Capítulo 10 - Item 10.3 – Implementação de Ponteiros e Objetos, pode-se implementar estruturas de dados ligadas sem um tipo de dados
ponteiro explícito.
• Pode-se representar uma coleção de objetos que têm os mesmos campos usando-se um arranjo para cada campo;
• Como exemplo, a figura abaixo mostra como se pode implementar uma lista ligada com três arranjos:
• O objeto com chave 4 segue o objeto com chave 16 na lista ligada;
A chave 4 aparece em chave[2]; e a chave 16 aparece em chave[5];
assim, tem-se: próximo[5] = 2 e anterior[2] = 5
• A chave (key) do arranjo contém os valores das chaves  presentes atualmente no conjunto dinâmico e os ponteiros são armazenados no indice do próximo elemento (next) e anterior (prev);
• Para um dado índice de arranjo x, chave[x], próximo[x] e anterior[x] representam um objeto na lista ligada;
• Sob essa interpretação, um ponteiro x é simplesmente um índice comum para os arranjos chave, próximo e anterior;
• Na figura, uma variável L contém o índice do início da lista(cabeça).
• Escrever, em Java, um programa que implementa as operações básicas de Listas Ligadas com o esquema proposto:
O valor NIL (0) é usado para representar ponteiro nulo (sentinela).
*/

public class LinkedListArrays {

    private static final int NIL = 0;       // ponteiro nulo
    private static final int MAX = 20;      // tamanho máximo dos arranjos

    private int[] key  = new int[MAX + 1];  // índices 1..MAX são usados
    private int[] next = new int[MAX + 1];
    private int[] prev = new int[MAX + 1];

    private int L;          // cabeça
    private int free;       // início da lista de elementos livres
    private int size;       // número de elementos atualmente na lista

    // Construtor — inicializa a estrutura vazia

    public LinkedListArrays() {
        L    = NIL;
        size = 0;
        // Encadeia todos os slots como livres (free list)
        for (int i = 1; i < MAX; i++) {
            next[i] = i + 1;
        }
        next[MAX] = NIL;
        free = 1;
    }

    // 1. Criação da estrutura de lista ligada a partir de uma lista de valores;

    public void criarLista(int[] valores) {
        // Reinicia tudo
        L    = NIL;
        size = 0;
        for (int i = 1; i < MAX; i++) next[i] = i + 1;
        next[MAX] = NIL;
        free = 1;

        for (int v : valores) {
            inserir(v);
        }
    }

    // Aloca um slot livre e retorna seu índice
    private int alocar() {
        if (free == NIL) {
            throw new RuntimeException("Lista cheia — sem espaço disponível.");
        }
        int x = free;
        free  = next[free];
        return x;
    }

    // Libera o slot de índice x (devolve à free list)
    private void liberar(int x) {
        next[x] = free;
        free    = x;
    }

    // 2. Inserção de um elemento em uma lista ligada existente; 

    public void inserir(int valor) {
        int x   = alocar();
        key[x]  = valor;
        next[x] = L;
        prev[x] = NIL;

        if (L != NIL) {
            prev[L] = x;
        }
        L = x;
        size++;
    }

    // Busca pelo primeiro elemento com chave == valor; retorna índice ou NIL
    // Equivale a LIST-SEARCH de Cormen
    public int buscar(int valor) {
        int x = L;
        while (x != NIL && key[x] != valor) {
            x = next[x];
        }
        return x;   // NIL se não encontrado
    }

    // 3. Deleção de um valor de uma lista ligada existente;

    public boolean deletar(int valor) {
        int x = buscar(valor);
        if (x == NIL) {
            System.out.println("Valor " + valor + " nao encontrado na lista.");
            return false;
        }
        // Reconecta vizinhos
        if (prev[x] != NIL) {
            next[prev[x]] = next[x];
        } else {
            // x é a cabeça
            L = next[x];
        }

        if (next[x] != NIL) {
            prev[next[x]] = prev[x];
        }

        liberar(x);
        size--;
        return true;
    }

    // 4. Uma função que imprime os valores existentes em uma dada lista ligada 

    public void imprimir() {
        if (L == NIL) {
            System.out.println("Lista vazia.");
            return;
        }

        System.out.print("Lista (cabeça -> cauda): ");
        int x = L;
        while (x != NIL) {
            System.out.print(key[x]);
            if (next[x] != NIL) System.out.print(" ↔ ");
            x = next[x];
        }
        System.out.println("  (tamanho: " + size + ")");
    }

    // Exibe o estado interno dos arranjos
    public void imprimirArranjos() {
        // Coleta índices em uso
        int[] usados  = new int[size];
        int   cnt     = 0;
        int   x       = L;
        while (x != NIL) { usados[cnt++] = x; x = next[x]; }

        System.out.println("\n--- Estado dos arranjos ---");
        System.out.printf("%-6s", "idx");
        for (int i = 0; i < cnt; i++) System.out.printf("%-6d", usados[i]);
        System.out.println();

        System.out.printf("%-6s", "key");
        for (int i = 0; i < cnt; i++) System.out.printf("%-6d", key[usados[i]]);
        System.out.println();

        System.out.printf("%-6s", "next");
        for (int i = 0; i < cnt; i++) {
            String v = next[usados[i]] == NIL ? "NIL" : String.valueOf(next[usados[i]]);
            System.out.printf("%-6s", v);
        }
        System.out.println();

        System.out.printf("%-6s", "prev");
        for (int i = 0; i < cnt; i++) {
            String v = prev[usados[i]] == NIL ? "NIL" : String.valueOf(prev[usados[i]]);
            System.out.printf("%-6s", v);
        }
        System.out.println();

        System.out.println("L (cabeça) = " + L);
        System.out.println("free       = " + free);
    }

    public static void main(String[] args) {

        LinkedListArrays lista = new LinkedListArrays();

        System.out.println("\n 1. Criaçao da lista com valores");
        // 9 - 16 - 4 - 1
        lista.criarLista(new int[]{1, 4, 16, 9});
        lista.imprimir();
        lista.imprimirArranjos();

        System.out.println("\n 2. Inserçao do valor 25");
        lista.inserir(25);
        lista.imprimir();
        lista.imprimirArranjos();

        System.out.println("\n 3. Deleçao do valor 16");
        lista.deletar(16);
        lista.imprimir();
        lista.imprimirArranjos();

        System.out.println("\n 3b. Tentativa de deletar valor inexistente (99)");
        lista.deletar(99);

        System.out.println("\n 4. Estado final da lista");
        lista.imprimir();
    }
}