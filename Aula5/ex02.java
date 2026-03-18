package Aula5;

public class ex02 {
    public static int T (int n) {
        int abc = 30; //1
        for (int i = 1; i < n-1; i++) { //2
            abc *= 2; //3
            abc++; //4
        }
        return abc;
}
    public static void main (String[] args) {
        int resposta = T(5) ;
        System.out.println("resposta = " + resposta);
    }   
}

/*
| Linha | Instrução | Frequência |

| 1 | int abc = 30 | 1 vez |
| 2 | i < n-1 (teste) | n-1 vezes |
| 3 | abc *= 2 | n-2 vezes |
| 4 | abc++ | n-2 vezes |

O(n) = 1 + (n - 1) + 2(n - 2) 
O(n) = 1 + n - 1 + 2n - 4 = 3n

-> cresce linearmente com n. */