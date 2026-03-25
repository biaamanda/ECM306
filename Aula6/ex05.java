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
            i = i + 2;
        }
        return m;
    }
}

/*Sendo que i inicia em 4, ou seja o primeiro termo fica n - 4. Como a variável cresce de 2 em 2, tem-se que (n - 2)/2, portanto pode se concluir que a complexidade é linear (O(n)). */
