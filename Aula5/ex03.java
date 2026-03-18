package Aula5;

public class ex03 {
    public static int Func (int n) {
        int x = 30; //1
        int i = 0; //2
        while (i < n) { //3
            x = x + 2 - i; //4
            i = i + 1; //5
        }
        return x;
    }
    public static void main(String[] args) {
        int resposta = Func (10);
        System.out.println("resposta = " + resposta);
    }
}

/*
| Linha | Instrução | Frequência |

| 1 | int x = 30 | 1 vez |
| 2 | int i = 0 | 1 vez |
| 3 | i < n | n + 1 vezes |
| 4 | x = x + 2 - i | n |
| 5 | i = i + 1 | n |

O(n) = 1 + 1 + (n+1) + n + n

O(n) = 3n -> crescimento linear */