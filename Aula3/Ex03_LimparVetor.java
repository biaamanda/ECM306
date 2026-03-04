//Elaborar um programa, em linguagem Java, capaz de limpar (colocar -1 em todas as posições) matrizes de dimensões N por M, do tipo double, onde N e M devem ser, respectivamente: 10 e 10; 50 e 75; 100 e 300; 500 e 200; 1000 e 1000; 5000 e 7000; 10000 e 1; 50000 e 25000; 100000 e 100000; e 500000 e 1000

public class Ex03_LimparVetor {
    public static void main(String[] args){
        int[][] dimension = {{10, 10}, {50, 75}, {100, 300}, {500, 200}, {1000, 1000}, {5000, 7000}, {10000, 1}, {50000, 25000}, {100000, 100000}, {500000, 1000}};
        for (int[] dim : dimension) {
            int n = dim[0];
            int m = dim[1];
            double[][] matrix = new double[n][m];
            long startTime = System.nanoTime();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = -1;
                }
            }
            long endTime = System.nanoTime();
            System.out.println("Dimensoes: " + n + "x" + m + " - Tempo: " + (endTime - startTime) + " ns");
        }
    }
}
