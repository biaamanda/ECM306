/* Unidade 15 - Heaps e Filas de Prioridade
 *
 * APLICACAO: fila de um laboratorio / hospital usando heap de prioridade.
 * Consolida os exercicios Ex02_1 a Ex02_4 da Aula 15.
 *
 * Regras de prioridade (menor numero = atende primeiro):
 *   0 -> idoso acima de 80 anos          (maior prioridade)
 *   1 -> idoso acima de 60 anos
 *   2 -> gestante ou puerpera
 *   3 -> demais pacientes                 (menor prioridade)
 *
 * Criterio de desempate: quem chegou primeiro (menor senha) e atendido antes.
 *
 * Como 0 e o "mais prioritario", usamos um MIN-HEAP sobre o par
 * (prioridade, senha).
 */

import java.util.Scanner;

public class FilaPrioridadeHospital {

    static class Paciente {
        String nome;
        int idade;
        boolean gestante;
        boolean puerpera;
        int prioridade;
        int senha;

        Paciente(String nome, int idade, boolean gestante, boolean puerpera,
                 int prioridade, int senha) {
            this.nome = nome;
            this.idade = idade;
            this.gestante = gestante;
            this.puerpera = puerpera;
            this.prioridade = prioridade;
            this.senha = senha;
        }

        String situacao() {
            switch (prioridade) {
                case 0:  return "Idoso acima de 80";
                case 1:  return "Idoso acima de 60";
                case 2:  return "Gestante/Puerpera";
                default: return "Demais pacientes";
            }
        }

        @Override
        public String toString() {
            return "Senha " + senha + " | " + nome + " | idade " + idade
                    + " | prioridade " + prioridade + " (" + situacao() + ")";
        }
    }

    // ---------------- Heap de prioridade (min-heap 0-indexado) ----------------
    static class FilaHeap {
        Paciente[] heap;
        int tamanho;

        FilaHeap(int capacidade) {
            heap = new Paciente[capacidade];
            tamanho = 0;
        }

        // a "vence" b se tem prioridade menor; empate -> menor senha (chegou antes).
        private boolean maisPrioritario(Paciente a, Paciente b) {
            if (a.prioridade != b.prioridade) {
                return a.prioridade < b.prioridade;
            }
            return a.senha < b.senha;
        }

        void inserir(Paciente p) {
            if (tamanho == heap.length) {
                System.out.println("Fila cheia!");
                return;
            }
            heap[tamanho] = p;
            int i = tamanho;
            tamanho++;

            while (i > 0) {
                int pai = (i - 1) / 2;
                if (maisPrioritario(heap[i], heap[pai])) {
                    trocar(i, pai);
                    i = pai;
                } else {
                    break;
                }
            }
        }

        Paciente remover() {
            if (tamanho == 0) {
                return null;
            }
            Paciente topo = heap[0];
            heap[0] = heap[tamanho - 1];
            heap[tamanho - 1] = null;
            tamanho--;
            heapify(0);
            return topo;
        }

        private void heapify(int i) {
            while (true) {
                int e = 2 * i + 1;
                int d = 2 * i + 2;
                int melhor = i;

                if (e < tamanho && maisPrioritario(heap[e], heap[melhor])) melhor = e;
                if (d < tamanho && maisPrioritario(heap[d], heap[melhor])) melhor = d;

                if (melhor == i) return;

                trocar(i, melhor);
                i = melhor;
            }
        }

        private void trocar(int i, int j) {
            Paciente aux = heap[i];
            heap[i] = heap[j];
            heap[j] = aux;
        }

        boolean vazia() {
            return tamanho == 0;
        }

        void mostrar() {
            if (tamanho == 0) {
                System.out.println("Fila vazia!");
                return;
            }
            System.out.println("--- FILA (ordem interna do heap) ---");
            for (int i = 0; i < tamanho; i++) {
                System.out.println("  " + heap[i]);
            }
        }
    }

    // ---------------- Regra de classificacao ----------------
    static int calcularPrioridade(int idade, boolean gestante, boolean puerpera) {
        if (idade > 80) return 0;
        if (idade > 60) return 1;
        if (gestante || puerpera) return 2;
        return 3;
    }

    // ---------------- Programa com menu ----------------
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FilaHeap fila = new FilaHeap(100);
        int proximaSenha = 1;
        int opcao;

        do {
            System.out.println("\n=== LABORATORIO DE EXAMES ===");
            System.out.println("1 - Adicionar paciente");
            System.out.println("2 - Atender proximo");
            System.out.println("3 - Mostrar fila");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            opcao = in.nextInt();
            in.nextLine();

            switch (opcao) {
                case 1: {
                    System.out.print("Nome: ");
                    String nome = in.nextLine();
                    System.out.print("Idade: ");
                    int idade = in.nextInt();
                    in.nextLine();

                    boolean gestante = false;
                    boolean puerpera = false;
                    if (idade <= 60) {
                        System.out.print("Gestante? (s/n): ");
                        gestante = in.nextLine().equalsIgnoreCase("s");
                        if (!gestante) {
                            System.out.print("Puerpera? (s/n): ");
                            puerpera = in.nextLine().equalsIgnoreCase("s");
                        }
                    }

                    int prioridade = calcularPrioridade(idade, gestante, puerpera);
                    Paciente p = new Paciente(nome, idade, gestante, puerpera,
                                              prioridade, proximaSenha);
                    fila.inserir(p);
                    System.out.println("Cadastrado -> " + p);
                    proximaSenha++;
                    break;
                }
                case 2: {
                    Paciente atendido = fila.remover();
                    if (atendido == null) {
                        System.out.println("Nao ha pacientes na fila.");
                    } else {
                        System.out.println("Chamando: " + atendido);
                    }
                    break;
                }
                case 3:
                    fila.mostrar();
                    break;
                case 0:
                    System.out.println("Encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        in.close();
    }
}
