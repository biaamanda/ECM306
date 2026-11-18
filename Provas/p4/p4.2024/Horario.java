/*Cria um Scanner.
Para cada professor, pede a disponibilidade (ex.: digita 3 5), converte para índices de dia e chama addAresta.
Cria o Emparelhamento e chama resolver().
Se o resultado for menor que 5 → imprime “sem solução”.
Senão, percorre getProfessorDoDia() e imprime 2a → P2, 3a → P1 e assim por diante. */

import java.util.Scanner;

public class Horario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] DIAS = {"2a", "3a", "4a", "5a", "6a"};

        GrafoBipartido g = new GrafoBipartido(5, 5);

        System.out.println("Dias: 2=segunda 3=terca 4=quarta 5=quinta 6=sexta");
        System.out.print("Digite a disponibilidade dos professores:");

        for(int p = 0; p < 5; p++) {
            System.out.print("Disponibilidade P" + (p + 1) + ": ");
            String linha = scanner.nextLine();
            String[] partes = linha.trim().split(" ");

            for (String parte : partes) {
                int dia = Integer.parseInt(parte) - 2;
                g.addAresta(p, dia); // converte para índice de dia
            }
        }

        Emparelhamento m = new Emparelhamento(g);    // entrega o grafo pronto
        int total = m.resolver();                    // quantos professores ganharam dia

        if (total < 5) {
            System.out.println("Sem solucao");
        } else {
            int[] professorDoDia = m.getProfessorDoDia();
            for (int d = 0; d < 5; d++) {
                System.out.println(DIAS[d] + " -> P" + (professorDoDia[d] + 1));
            }
        }

        scanner.close();
    }
}
