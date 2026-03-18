package Aula5;

public class ex05 {
    public static final int lista[] = {2,6,5,1,4};
        public static void main (String[] args) {
            int n = lista.length;
            int x = 2;
            int Resposta = Func (lista, x, n);
            System.out.println("Resultado = " + Resposta);
        }
        
        public static int Func(int[] a, int x, int n ) {
            int resultado = a[n-1]; //1
            for (int i = 0; i < a.length; i++) //2
                resultado = resultado * x + a[i]; //3
            return resultado;
        }
}

/*
| Linha | Instrução | Frequência |

| 1 | int resultado = a[n-1] | 1 vez |
| 2 | i < a.length (teste) | n + 1 vezes |
| 3 | resultado = resultado * x + a[i] | n vezes |

O(n) = 1 + (n+1) + n

O(n) = 2n -> linear */