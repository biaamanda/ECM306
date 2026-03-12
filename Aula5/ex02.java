package Aula5;

public class ex02 {
    public static int T (int n) {
        int abc = 30; //1
        for (int i = 1; i < n-1; i++) { //2
            abc *= 2; //3
            abc++; //4
        }
        return abc;
}
    public static void main (String[] args) {
        int resposta = T(5) ;
        System.out.println("resposta = " + resposta);
    }   
}