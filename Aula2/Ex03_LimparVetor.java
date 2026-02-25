//Elaborar um programa, em linguagem Java, capaz de limpar (colocar -1 em todas as posições) matrizes de dimensões N por M, do tipo double, onde N e M devem ser, respectivamente: 10 e 10; 50 e 75; 100 e 300; 500 e 200; 1000 e 1000; 5000 e 7000; 10000 e 1; 50000 e 25000; 100000 e 100000; e 500000 e 1000.

public class Ex03_LimparVetor {
    public static void main(String[] args) {
            double[][] matrix = new double[500000][500000];
            cleanMatrix(matrix);
            System.out.println("Matriz limpa.");
    }

    public static void cleanMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = -1;
            }
        }
    }    
}