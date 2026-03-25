/*No algoritmo a seguir, informe a quantidade de vezes que a Linha 1 será executada,
em tempo de execução e em função de n. */

package Aula6;

import java.util.Scanner;

public class ex04 {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        int n = in.nextInt();
        System.out.println(Func(n)); in.close();
    }
    public static int Func(int n) {
        int m = 0;
        for (int i=2; i < n; i++)
            for (int j = 2; j < n; j++){
                m= m + 1; // Linha 1
            }
        return m;
    }
}

/*Considerando que i e j inicia em 2 e termina em i e j menor que n,  cada for será executado n - 2 vezes e como são dois fors ((n - 2) x (n - 2))a função de complexidade fica em O(n) = n² */