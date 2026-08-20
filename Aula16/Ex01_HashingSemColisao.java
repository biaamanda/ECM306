/*
 * PARTE A - Hashing Interno sem Tratamento de Colisão
 *
 * ENUNCIADO:
 * 1. Completar o método hash() no programa, utilizando a função
 *    hash h = chave mod n.
 * 2. Executar o código e avaliar sua execução.
 * 3. Responder: houve colisões? Em caso afirmativo, quantas e quais?
 * 4. Como foi feito o tratamento de colisões?
 * 5. Que sugestões apresentar para o tratamento das colisões?
 *
 * 
 */

public class Ex01_HashingSemColisao {
    public static void main(String[] args) {
        Aluno[] tabAluno = new Aluno[10];

        tabAluno[0] = new Aluno(10, "Ana");
        tabAluno[1] = new Aluno(21, "Silas");
        tabAluno[2] = new Aluno(22, "Ari");
        tabAluno[3] = new Aluno(24, "Pedro");
        tabAluno[4] = new Aluno(35, "Jonas");
        tabAluno[5] = new Aluno(60, "Saul");
        tabAluno[6] = new Aluno(44, "Josue");
        tabAluno[7] = new Aluno(57, "Paulo");
        tabAluno[8] = new Aluno(80, "Sara");
        tabAluno[9] = new Aluno(90, "Davi");

        int hashCode = null, chave;
        Aluno[] tabHash = new Aluno[10];
        for(int i = 0; i < tabAluno.length; i++) {
            chave = (tabAluno[i].getcodAluno());
            hashCode = hash(chave);
            System.out.println("Chave: " + chave + " Hash: " + hashCode);

            if(tabHash[hashCode] == null) {
                tabHash[hashCode] = tabAluno[i];
            } else {
                System.out.println("Colisao no slot da Tabela Hash: ");
                System.out.println("Chave: " + tabAluno[i].getcodAluno() + "NAO ARMAZENA NA TABELA HASH \n");
            }
        }

        System.out.println("\n Tabela Aluno: ");

        for(int i = 0; i < tabAluno.length; i++) {
            System.out.println("Slot: " + i + " ---> " + tabAluno[i].getcodAluno() + " " + tabAluno[i].getnomeAluno());
        }

        System.out.println("\n Tabela Hash: ");
        for(int i = 0; i < tabHash.length; i++) {
            if(tabHash[i] == null) {
                System.out.println("Slot: " + i + " ---> valor nulo");
            } else {
                System.out.println("Slot: " + i + " ---> " + tabHash[i].getcodAluno() + " " + tabHash[i].getnomeAluno() +"\n");
            }
        }
    }

    public static int hash(int key) {
        
    }
}
