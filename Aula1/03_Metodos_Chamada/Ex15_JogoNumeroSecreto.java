// ENUNCIADO:
// Utilizando programação por chamada de métodos, elaborar um programa em linguagem Java, capaz de criar um menu em tela, com as seguintes opções: 
// A- Inserir número secreto; 
// B- Jogar; 
// C- Apresentar resultados; 
// D- Sair; 

// - Se a opção A for digitada, o programa deverá fornecer instruções ao operador e receber, via teclado, um número inteiro e positivo, secreto, objetivo de adivinhação do jogo. Após isto, o programa deverá voltar ao menu principal; 
// - Se a opção B for digitada, o jogo se iniciará e será colocado 0 no contador de palpites. O programa fornecerá instruções ao operador e receberá, via teclado, um número inteiro e positivo, a ser comparado com o número secreto. Se o número digitado for maior que o secreto, o programa informará ao operador a palavra ALTO, incrementará o contador de palpites e aguardará o próximo palpite; Se o número digitado for menor que o secreto, o programa informará ao operador a palavra BAIXO, incrementará o contador de palpites e aguardará o próximo palpite; Se o número digitado for igual ao secreto, o programa informará ao operador a palavra ACERTOU e retornará ao menu principal; Se o número digitado for negativo, o programa deverá retornar ao menu principal. 
// - Se a opção C for digitada, o programa apresentará a quantidade de palpites que foram necessários para acontecer último acerto. Após isto, o programa deverá retornar ao menu principal.
// - Se a opção D for digitada, o programa deve encerrar-se

import java.util.Scanner;

public class Ex15_JogoNumeroSecreto {

    static int secretNumber = -1;
    static int lastGuessCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char option;

        do { 
            menu();
            option = scanner.next().toUpperCase().charAt(0);
            
            switch (option) {
                case 'A':
                    secretNumber(scanner);
                    break;
                case 'B':
                    play(scanner);
                    break;
                case 'C':
                    showResults();
                    break;
                case 'D':
                    System.out.println("Encerrando o programa.");
                    return;
        
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
            }
        } while (option != 'D');
        scanner.close();
    }

    public static void menu() {
        System.out.println("Menu:");
        System.out.println("A - Inserir número secreto");
        System.out.println("B - Jogar");
        System.out.println("C - Apresentar resultados");
        System.out.println("D - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void secretNumber(Scanner scanner) {
        System.out.print("Digite o número secreto (inteiro e positivo): ");
        int number = scanner.nextInt();

        if (number > 0){
            secretNumber = number;
            System.out.println("Número secreto definido com sucesso.");
        } else {
            System.out.println("Número inválido. O número deve ser inteiro e positivo.");
        }
    }

    public static void play(Scanner scanner) {
        if (secretNumber <= 0) {
            System.out.println("Número secreto não definido ainda.");
            return;
        }

        int guess;
        int guessCount = 0;

        System.out.println("Inserir um numero positivo para tentar adivinhar ou um numero negativo para retornar ao menu.");

        while (true) {
            System.out.print("Sua resposta: ");
            guess = scanner.nextInt();

            if (guess < 0) {
                System.out.println("Retornando ao menu.");
                return;
            }

            guessCount++;

            if (guess > secretNumber) {
                System.out.println("ALTO");
            } else if (guess < secretNumber) {
                System.out.println("BAIXO");
            } else {
                System.out.println("ACERTOU");
                lastGuessCount = guessCount;
                return;
            }
        }
    }

    public static void showResults() {
        if (lastGuessCount == 0) {
            System.out.println("Sem nenhuma resposta correta.");
        } else {
            System.out.println("Numero de tentativas ate vencer: " + lastGuessCount);
        }
    }
}
