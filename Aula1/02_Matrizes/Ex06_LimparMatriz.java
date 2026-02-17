// ENUNCIADO:
// Elaborar um programa em Java capaz de limpar (colocar -1 em todas as posições) qualquer tipo de matriz, inteira e positiva e que seja declarada globalmente. O programador deverá definir o tamanho da matriz (linhas e colunas), antes de compilar o programa. 

public class Ex06_LimparMatriz {

    static int[][] matrix = new int[3][3]; //declarar globalmente = fora do main

    public static void main(String[] args) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        for(int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                matrix[i][j] = -1;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); //quebra a linha
        }
    }
}
