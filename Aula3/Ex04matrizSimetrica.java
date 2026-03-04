// Elaborar um programa, em linguagem Java, capaz de informar quando uma matriz N por N, do tipo int, é simétrica (quando a matriz analisada for igual à sua transposta), onde N deve ser: 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000 e 500000.


public class Ex04matrizSimetrica {
    public static void main(String[] args){
        int[] size = {10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000};
        for (int n : size) {
            int[][] matrix = new int[n][n];
        
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = (i == j) ? 1 : 0;
                }
            }
            long startTime = System.nanoTime();
            boolean symmetric = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] != matrix[j][i]) {
                        symmetric = false;
                        break;
                    }
                }
            }
            long endTime = System.nanoTime();
            System.out.println("Tamanho: " + n + " - Simétrica: " + symmetric + " - Tempo: " + (endTime - startTime) + " ns");
        }
    }
}