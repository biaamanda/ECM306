
// Elaborar um programa, em linguagem Java, capaz copiar o conteúdo de um vetor do tipo int de N elementos para outro vetor de mesmo tamanho, utilizando laços, onde N deve ser: 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000 e 500000

public class Ex02_CopiarVetor {
    public static void main(String[] args) {
        // Vetor original com 10 elementos
        int[] vetorOrigem = new int[500000];

        // Inicializando o vetor origem com valores (exemplo)
        for (int i = 0; i < vetorOrigem.length; i++) {
            vetorOrigem[i] = i + 1;
        }

        // Vetor destino com o mesmo tamanho
        int[] vetorDestino = new int[500000];

        // Copiando os elementos usando laço
        for (int i = 0; i < vetorOrigem.length; i++) {
            vetorDestino[i] = vetorOrigem[i];
        }

        System.out.println("Vetor inicializado");
    }
}

