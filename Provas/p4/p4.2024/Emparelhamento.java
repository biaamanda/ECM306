/*Atributos:

GrafoBipartido g
int[] professorDoDia: professorDoDia[d] guarda qual professor ficou com o dia d. Começa tudo com -1 (dia livre).

Métodos:

Método	O que faz
boolean tenta(int p, boolean[] visitado)	tenta arranjar um dia para o professor p (detalhe abaixo)
int resolver()	chama tenta para cada professor e conta quantos conseguiram um dia
int[] getProfessorDoDia()	devolve o resultado para imprimir

Como o tenta(p) funciona (é o teorema de Berge):
Para cada dia d em que o professor p está disponível:

Se já olhou esse dia nesta tentativa (visitado[d]), pula. Isso evita andar em círculos.
Marca visitado[d] = true.
Se o dia está livre (professorDoDia[d] == -1): fica com ele e retorna true.
Se o dia está ocupado por outro professor q: chama tenta(q), ou seja, pede para q se mudar para outro dia. Se q conseguir, p fica com d e retorna true.
Se nenhum dia deu certo, retorna false.

O passo 4 é o caminho aumentante: “P1 quer quinta, que está com P3; P3 se muda para segunda; aí P1 fica com quinta”. A recursão faz a inversão das arestas sozinha.

Em resolver(), crie um boolean[] visitado novo (tudo false) para cada professor. */

import java.util.Arrays;

public class Emparelhamento {
    GrafoBipartido g;
    int[] professorDoDia;

    public Emparelhamento(GrafoBipartido g) {
        this.g = g;
        professorDoDia = new int[g.qtdDias()];   // um espaço por dia
        Arrays.fill(professorDoDia, -1);         // -1 = dia livre
    }

    private boolean tenta(int p, boolean[] visitado) { //tenta arranjar um dia para o professor p
        for (int d : g.vizinhos(p)) {              // para cada dia em que p pode
            if (visitado[d]) continue;             // já olhei esse dia? pula
            visitado[d] = true;                    // marca como olhado

            int dono = professorDoDia[d];          // quem está com esse dia agora?
            if (dono == -1 || tenta(dono, visitado)) { // o dia está livre OU o dono conseguiu se mudar para outro dia
                professorDoDia[d] = p;             // então p fica com o dia d
                return true;
            }
        }
        return false;                              // nenhum dia deu certo
    }

    public int resolver() { //passa por cada professor e conta quantos conseguiram dia.
        int total = 0;
        for (int p = 0; p < g.qtdProfessores(); p++) {
            boolean[] visitado = new boolean[g.qtdDias()];   // "ainda não olhei nenhum dia"
            if (tenta(p, visitado)) {
                total++;
            }
        }
        return total;
    }

    public int[] getProfessorDoDia() {
        return professorDoDia;
    }
    
}
