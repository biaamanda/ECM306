//Elaborar um programa, em linguagem Java, capaz inicializar com 0 (zero) um vetor do tipo int de N elementos, utilizando laços, onde N deve ser: 10, 50, 100, 500, 1000, 5000, 10000, 50000, 100000 e 500000

public class Ex01_InciarVetor{
    public static void main(String[] args) {
        
        int[] vector = new int[500000];
        
        for (int i = 0; i < vector.length; i++) {
            vector[i] = 0;
        }
        System.out.println("Vetor de 500000 elementos inicializado.");

    }
}