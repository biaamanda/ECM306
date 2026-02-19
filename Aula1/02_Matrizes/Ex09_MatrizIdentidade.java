// ENUNCIADO:
// Determinar se uma matriz é identidade (ou unidade), através de um programa em linguagem Java. A matriz deverá ser global e todos os seus elementos digitados. Uma matriz é dita identidade (ou unidade) quando ela for quadrada (no. de linhas iguais ao no. de colunas), tiver todos os elementos de sua diagonal principal (no. da linha igual ao no. da coluna) iguais a 1 (um) e todos os demais elementos iguais a 0 (zero). 

import java.util.Scanner;

public class Ex09_MatrizIdentidade {

    //static int[][] matrix = new int[][]
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Numero de linhas: ");
        int rows = sc.nextInt();
        System.out.print("Numero de colunas: ");
        int columns = sc.nextInt();

        int[][] matrix = new int[rows][columns];
        
        System.out.println("Digite os elementos da matriz:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Elemento [" + i + "][" + j + "]: ");
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println("Matriz Digitada:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}
