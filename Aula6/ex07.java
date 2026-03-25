/*No algoritmo a seguir, informe a quantidade de vezes que a Linha 1 será executada,
em tempo de execução e em função de n */

package Aula6;

import java.util.Scanner;

public class ex07 {
    public static void main(String[] args) {
        Scanner in = new Scanner (System. in);
        int n = in.nextInt();
        System.out.println(Func(n)); in.close();
    }
    public static int Func (int n) {
        int m = 0;
        for (int i=1; i <= n; i++) 
            for (int j = i; j <= n; j++ ) {
                m = m + 1;// Linha 1
        }
        return m;
    }
}

//A linha 1 será executada (n(n + 1))/2, tendo sua função de complexidade O(n^2).