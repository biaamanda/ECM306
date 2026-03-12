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
