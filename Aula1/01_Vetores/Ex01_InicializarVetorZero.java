// ENUNCIADO:
// Elaborar um programa, em linguagem Java, capaz inicializar com 0 (zero) um vetor do tipo int de 100 elementos, utilizando-se das malhas (laços, loops, etc.) while, do-while e for;

public class Ex01_InicializarVetorZero {
    public static void main(String[] args) {
        int MaxVector = 100;
        int[] vector = new int[MaxVector];

        for (int i =0; i < MaxVector; i++){
            vector[i] = i; //vector[i] = 0; se fosse para ser uma lista de zeros
            System.out.print(vector[i] + " ");
        }
    }
}