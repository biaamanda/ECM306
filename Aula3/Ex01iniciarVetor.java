//Elaborar um programa, em linguagem Java, capaz inicializar com 0 (zero) um vetor do tipo int de N elementos, utilizando laços, onde N deve ser: 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000 e 500000.

public class Ex01iniciarVetor {
    public static void main(String[] args){
        int N = 50000; 
        int[] vetor = new int[N];

        for (int i = 0; i < N; i++) {
            vetor[i] = 0;
        }
        System.out.println("Vetor inicializado com 0:");
    }
}
