// ENUNCIADO:
// Elaborar um programa, em linguagem Java, capaz de informar quando uma matriz qualquer é simétrica. Essa matriz deverá ser global e todos os seus elementos digitados. Uma matriz é dita simétrica quando ela for igual à sua transposta. 

public class Ex08_MatrizSimetrica {
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

        if (matrix == transposeMatrix){
            System.out.println("Matriz é simétrica");
        } else {
        System.out.println("Matriz não é simétrica");
        }

    }
}
