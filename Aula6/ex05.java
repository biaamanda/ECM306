/*No algoritmo a seguir, informe a quantidade de vezes que a Linha 1 será executada,
em tempo de execução e em função de n. */

package Aula6;

import java.util.Scanner;

public class ex05 {
    public static void main (String[] args) {
        Scanner in = new Scanner (System. in);
        int n = in.nextInt();
        System.out.println(Func(n)); 
        in.close();
    }
    public static int Func(int n) {
    int i = 4;
    int m = 0;
    while (i <= n) {
        m = m + 1; // Linha 1
        i = 1 + 2;
    }
    return m;
    }
}
