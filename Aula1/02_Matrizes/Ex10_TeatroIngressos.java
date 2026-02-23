// ENUNCIADO:
//  Um teatro possui 3000 lugares divididos em 30 fileiras, cada uma com 100 cadeiras. Elaborar um programa em linguagem Java, capaz de gerenciar a venda dos ingressos para este teatro. Cada lugar poderá estar Livre (0), Reservado (1) ou Vendido (2).  

import java.util.Scanner;

public class Ex10_TeatroIngressos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] theater = new int[30][100];

        int option;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Reservar lugar");
            System.out.println("2 - Vender lugar");
            System.out.println("3 - Mostrar fileira");
            System.out.println("0 - Sair");
            System.out.print("Opçao: ");
            option = scanner.nextInt();

            if (option == 1 || option == 2) {

                System.out.print("Digite a fileira (1 a 30): ");
                int row = scanner.nextInt() - 1;

                System.out.print("Digite a cadeira (1 a 100): ");
                int seat = scanner.nextInt() - 1;

                if (row >= 0 && row < 30 &&
                    seat >= 0 && seat < 100) {

                    if (option == 1) { // Reservar
                        if (theater[row][seat] == 0) {
                            theater[row][seat] = 1;
                            System.out.println("Lugar reservado com sucesso.");
                        } else {
                            System.out.println("Lugar não está livre.");
                        }
                    }

                    if (option == 2) { // Vender
                        if (theater[row][seat] == 0 ||
                            theater[row][seat] == 1) {

                            theater[row][seat] = 2;
                            System.out.println("Lugar vendido com sucesso.");
                        } else {
                            System.out.println("Lugar já está vendido.");
                        }
                    }
                } else {
                    System.out.println("Fileira ou cadeira inválida.");
                }
            }

            if (option == 3) {
                System.out.print("Digite a fileira (1 a 30): ");
                int row = scanner.nextInt() - 1;

                if (row >= 0 && row < 30) {
                    for (int i = 0; i < 100; i++) {
                        System.out.print(theater[row][i] + " ");
                    }
                    System.out.println("\nLegenda: 0=Livre  1=Reservado  2=Vendido");
                } else {
                    System.out.println("Fileira invalida.");
                }
            }

        } while (option != 0);

        System.out.println("Programa encerrado.");
        scanner.close();
    }
}
