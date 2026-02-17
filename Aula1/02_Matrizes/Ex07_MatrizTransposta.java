// ENUNCIADO:
// Determinar a matriz transposta de uma matriz global qualquer e digitada, através de um programa em linguagem Java. Uma matriz é dita transposta quando a matriz original tiver suas linhas transformadas em colunas e suas colunas transformadas em linhas. 

public class Ex07_MatrizTransposta {

    static int[][]matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    public static void main(String[] args) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int[][] transposeMatrix = new int [columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposeMatrix[j][i] = matrix[i][j];
            }
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                System.out.print(transposeMatrix[i][j] + " ");
            }
        System.out.println();
        }
        
    }
}
