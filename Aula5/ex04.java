package Aula5;

public class ex04 {
    public static int T (int n){
        int abc = 30; //1
        int i = 1; //2
        do {
            abc *= 2; //3
            abc++; //4
            i = i + 1; //5
        } while (i < n-1); //6 
        return abc;
    }
    public static void main(String[] args) {
        int resposta = T(5) ;
        System.out.println("resposta = " + resposta);
    }
}