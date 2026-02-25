//Elaborar um programa, em linguagem Java, capaz de informar quando uma matriz N por N, do tipo int, é simétrica (quando a matriz analisada for igual à sua transposta), onde N deve ser: 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000 e 500000.

public class Ex04_MatrizSimetrica {

    public static void main(String[] args) {

        int[][] m = SymmetricMatrix(500000);
        VerifySymmetric(m, 500000);
    }

    // Cria uma matriz simétrica
    public static int[][] SymmetricMatrix(int n) {
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                matrix[i][j] = i + j;
                matrix[j][i] = matrix[i][j];
            }
        }
        return matrix;
    }

    // Verifica se a matriz é simétrica
    public static void VerifySymmetric(int[][] matriz, int n) {
        boolean symmetric = true;

        for (int i = 0; i < n && symmetric; i++) {
            for (int j = i + 1; j < n; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    symmetric = false;
                    break;
                }
            }
        }

        if (symmetric) {
            System.out.println("Matriz " + n + "x" + n + " é simétrica.");
        } else {
            System.out.println("Matriz " + n + "x" + n + " NÃO é simétrica.");
        }
    }
}