// ENUNCIADO:
// Elaborar um programa em Java capaz de carregar um vetor char de 26 elementos com os caracteres de A até Z pelo próprio programa, sem digitação, utilizando malhas.

public class Ex03_VetorAZ {
    public static void main(String[] args) {
        char[] vectorAZ = new char[26];
        char letter = 'a';

        for (int i = 0; i < vectorAZ.length ; i++) { 
            vectorAZ[i] = letter;
            letter++;
            System.out.print(vectorAZ[i] + " ");
        }
    }
}
