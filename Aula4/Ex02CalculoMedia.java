//Cálculo da Média Total dos elementos de um vetor de tamanho n

public class Ex02CalculoMedia {
    public static double mediaTotal(int[] vetor) {
        int sum = 0;
        for (int num : vetor) {
            sum += num; 
        }
        if (vetor.length == 0) {
            return 0; 
        }
        return (double) sum / vetor.length;
    }

    public static void main(String[] args) {
        int[] vector = {10, 20, 30, 40, 50};
        double result = mediaTotal(vector);
        System.out.println("Média total dos elementos: " + result );
    }
}