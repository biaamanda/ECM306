// Elaborar um programa, em linguagem Java, capaz de informar quando uma matriz N por N, do tipo int, é simétrica (quando a matriz analisada for igual à sua transposta), onde N deve ser: 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000 e 500000.


public class Ex04matrizSimetrica {
    public static void main(String[] args){
        int N = 10;
        int M = 10;
        int[][] matriz = new int[N][M];

        boolean simetrica = true;                               

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                simetrica = false;
                }
            }
        }
        System.out.println("A matriz é simétrica: " + simetrica);
    }
}