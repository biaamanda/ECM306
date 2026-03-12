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
            int resultado = a[n-1];
            for (int i = 0; i < a.length; i++)
                resultado = resultado * x + a[i];
            return resultado;
        }
}