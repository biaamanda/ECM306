package Aula5;

public class ex01 {
    public static int Func (int n){
        int x = 30; //1
        for (int i = 0; i < n; ++i) { //2
            x = x + 2 - i; //3
        }
        return x;
    }
    public static void main (String[] args) {
        int resposta = Func (10) ;
        System.out.println("resposta = " + resposta);
    }
}

/*
| Linha | Operação | Frequência |

| 1 | int x = 30 | 1 vez |
| 2 | i < n (laço) | n + 1 vezes |
| 3 | x = x + 2 - i | n vezes |

O(n) = 1 + (n + 1) + n = 2 + 2n = 2n

O(n) —> complexidade linear: tempo cresce proporcionalmente ao tamanho da entrada n. */