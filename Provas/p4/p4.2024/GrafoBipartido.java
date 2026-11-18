/*Atributos:

List<List<Integer>> adj: uma lista por professor, com os dias em que ele pode. Exemplo: adj.get(0) = [1, 3] quer dizer que P1 pode terça e quinta.
int qtdDias: quantos vértices tem o lado direito (5).

Métodos:

Método	O que faz
GrafoBipartido(int qtdProfessores, int qtdDias)	cria uma lista vazia para cada professor
addAresta(int professor, int dia)	adiciona o dia na lista do professor
vizinhos(int professor)	devolve a lista de dias daquele professor
qtdProfessores() / qtdDias()	devolvem os tamanhos

Use números: professor 0 a 4 (P1 a P5) e dia 0 a 4 (segunda a sexta). Só na hora de imprimir você converte para nomes. */

import java.util.List;
import java.util.ArrayList;

public class GrafoBipartido {
    List<List<Integer>> adj = new ArrayList<>();
    int qtdDias;
    int qtdProfessores;

    public GrafoBipartido(int qtdProfessores, int qtdDias) { //cria uma lista vazia para cada professor
        this.qtdProfessores = qtdProfessores;
        this.qtdDias = qtdDias;

        for (int i = 0; i < qtdProfessores; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addAresta(int professor, int dia) { //adiciona o dia na lista do professor
        adj.get(professor).add(dia);
    }


    public List<Integer> vizinhos(int professor) { //devolve a lista de dias daquele professor
        return adj.get(professor);
    }

    public int qtdProfessores() {
        return qtdProfessores;
    }

    public int qtdDias(){
        return qtdDias;
    }
}
