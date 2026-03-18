package Aula5;

public class ex04 {
    public static int T (int n){
        int abc = 30; //1
        int i = 1; //2
        do {
            abc *= 2; //3
            abc++; //4
            i = i + 1; //5
        } while (i < n-1); //6 
        return abc;
    }
    public static void main(String[] args) {
        int resposta = T(5) ;
        System.out.println("resposta = " + resposta);
    }
}

/*
| Linha | Instrução | Frequência |

| 1 | int abc = 30 | 1 vez |
| 2 | int i = 1 | 1 vez |
| 3 | abc *= 2 | n-2 vezes |
| 4 | abc++ | n-2 vezes |
| 5 | i = i + 1 | n-2 vezes |
| 6 | i < n-1 (teste) | n-2 vezes |

O(n) = 1 + 1 + (n-2) + (n-2) + (n-2) + (n-2)

O(n) = 2 + 4(n-2) = 2 + 4n - 8

O(n) = 4n -> linear */