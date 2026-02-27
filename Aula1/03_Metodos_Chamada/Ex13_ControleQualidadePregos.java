// ENUNCIADO:
// Da linha de produção de uma metalúrgica serão pegos, aleatoriamente, no decorrer de um dia, pelo controle de qualidade da empresa, 10 pregos sem cabeça para análise.Para cada amostra de prego pego, serão medidos seu comprimento e diâmetro, ambos em milímetros. Durante a medição, esses dados serão inseridos, um a um, em um programa de computador desenvolvido especialmente para isso. Após a digitação dos 10 pares de valores (comprimento e diâmetro), o programa deverá fornecer: 
// a) Comprimento e Diâmetro Médios das amostras verificadas; 
// b) O número e o comprimento da amostra mais longa (comprimento maior); 
// c) O número e o diâmetro da amostra mais fina (diâmetro menor). 
// Obs.: Valor médio do comprimento das amostras: Cmédio = (C1+C2+ ... + C9 + C10) / 10 
// Valor médio do diâmetro das amostras: Dmédio = (D1+D2+ ... + D9 + D10) / 10 

import java.util.Scanner;

public class Ex13_ControleQualidadePregos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] lenght = new double[10];
        double[] diameter = new double[10];

        double sumLength = 0;
        double sumDiameter = 0;

        int longestNail = 0;
        int thinnestDiameter = 0;

        for (int number = 0; number < 10; number++) {
            System.out.print("Comprimento do prego " + number + ": ");
            lenght[number] = scanner.nextDouble();
            sumLength += lenght[number];

            System.out.print("Diâmetro do prego  " + number + ": ");
            diameter[number] = scanner.nextDouble();
            sumDiameter += diameter[number];

            if(lenght[number] > lenght[longestNail]) {
                longestNail = number;
            }
            if(diameter[number] < diameter[thinnestDiameter]) {
                thinnestDiameter = number;
            }
        }
        
        double avgLenght = sumLength / 10;
        double avgDiameter = sumDiameter / 10;

        System.out.println("Comprimento e Diâmetro Médios das amostras: " +avgLenght + " mm e " + avgDiameter + " mm");
        System.out.println("Número e comprimento da amostra mais longa: " + (longestNail + 1) + " e " + lenght[longestNail] + " mm");
        System.out.println("Número e diâmetro da amostra mais fina: " + (thinnestDiameter + 1) + " e " + diameter[thinnestDiameter] + " mm");

        scanner.close();
    }
}
