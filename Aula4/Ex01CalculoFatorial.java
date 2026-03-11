//Cálculo do Fatorial de n.

public class Ex01CalculoFatorial {
    public static int fatorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * fatorial(n - 1);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int result = fatorial(n);
        System.out.println("O fatorial de " + n + " é " + result);
    }
}
