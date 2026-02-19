// ENUNCIADO:
// Criar um método, que se utilize de malhas, capaz de informar se o número inteiro e maior que zero digitado pelo operador é ou não primo. Este método deverá ser utilizado por um programa em linguagem Java, que pedirá ao operador a digitação do número, verificará se o mesmo é ou não primo e apresentará a conclusão em tela; 

import java.util.Scanner;

public class Ex12_NumeroPrimo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um numero:  ");
        int number = scanner.nextInt();

        if(PrimeNumber(number)){
            System.out.print("O numero e primo.");
        } else{
            System.out.print("Numero nao e primo.");
        }
        scanner.close();
    }

    public static boolean PrimeNumber(int number) {
        if (number <= 1){
            return false;
        }
        for (int i = 2; i < number; i++){
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }
}
