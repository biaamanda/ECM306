// ENUNCIADO:
// Dado o vetor gerado pelo exercício 3 ( [ ‘A’, ‘B’, ‘C’, ‘D’, ... , ‘W’, ‘X’, ‘Y’, ‘Z’ ] ), elaborar um programa em linguagem Java capaz de trocar a ordem de seus elementos, de dois em dois, até o final do mesmo  ( [ ‘B’, ‘A’, ‘D’, ‘C’, ... , ‘X’, ‘W’, ‘Z’, ‘Y’ ] ), utilizando malhas;

public class Ex04_TrocaDoisEmDois {
    public static void main(String[] args) {
        char[] vectorAZ = new char[26];
        char letter = 'a';

        for (int i = 0; i < vectorAZ.length ; i++) { 
            vectorAZ[i] = letter;
            letter++;
        }

        //Trocar elementos
        for (int j = 0; j < vectorAZ.length; j += 2){
            char temp = vectorAZ[j];
            vectorAZ[j] = vectorAZ[j + 1];
            vectorAZ[j + 1] = temp;
        }
        for (int i = 0; i < vectorAZ.length; i++) {
            System.out.print(vectorAZ[i] + " ");
        }
        
    }
}
