/*No algoritmo a seguir, informe a quantidade de vezes que a Linha 1 será executada,
em tempo de execução e em função de n. */

package Aula6;

import java.util.Scanner;

public class ex06 {
    public static void main(String[] args) {
        Scanner in = new Scanner (System. in);
        int n = in.nextInt();
        System.out.println(Func(n)); 
        in.close();
    }
    public static int Func(int n) {
        int i = 1;
        int m = 0;
        while (i <= n) {
            m = m + 1;
            i = i * 2; // Linha 1
        }
        return m;
    }
}

/*Considerando que i dobra a cada iteração e que o laço para em i maior que n, tem-se 2^k > n. Sendo assim, o programa executará T(n) = log2(n) + 1, logo é uma função O(log n). */