/*2.2 – Uma fila de Prioridade 1 (menos prioritária que a anterior) para
idosos acima de 60 anos; */

import java.util.Scanner;

public class Ex02_2 {

    static class Paciente {
        String nome;
        int idade;
        int prioridade;
        int senha;

        public Paciente(String nome, int idade, int prioridade, int senha) {
            this.nome = nome;
            this.idade = idade;
            this.prioridade = prioridade;
            this.senha = senha;
        }

        @Override
        public String toString() {
            return "Senha: " + senha +
                   " Nome: " + nome +
                   " Idade: " + idade +
                   " Prioridade: " + prioridade;
        }
    }

    static class Heap {

        Paciente[] heap;
        int tamanho;

        public Heap(int capacidade) {
            heap = new Paciente[capacidade];
            tamanho = 0;
        }

        public void inserir(Paciente paciente) {

            if (tamanho == heap.length) {
                System.out.println("Fila cheia!");
                return;
            }

            heap[tamanho] = paciente;

            int atual = tamanho;
            tamanho++;

            while (atual > 0) {

                int pai = (atual - 1) / 2;

                if (heap[atual].prioridade >= heap[pai].prioridade) {
                    break;
                }

                trocar(atual, pai);
                atual = pai;
            }
        }

        public Paciente remover() {

            if (tamanho == 0) {
                return null;
            }

            Paciente paciente = heap[0];

            heap[0] = heap[tamanho - 1];
            heap[tamanho - 1] = null;

            tamanho--;

            heapify(0);

            return paciente;
        }

        private void heapify(int i) {

            while (true) {
                int esquerda = 2 * i + 1;
                int direita = 2 * i + 2;
                int menor = i;

                if (esquerda < tamanho &&
                    heap[esquerda].prioridade < heap[menor].prioridade) {

                    menor = esquerda;
                }

                if (direita < tamanho &&
                    heap[direita].prioridade < heap[menor].prioridade) {

                    menor = direita;
                }

                if (menor == i) {
                    break;
                }

                trocar(i, menor);

                i = menor;
            }
        }

        private void trocar(int i, int j) {

            Paciente aux = heap[i];
            heap[i] = heap[j];
            heap[j] = aux;
        }

        public void mostrar() {

            if (tamanho == 0) {
                System.out.println("\nFila vazia!");
                return;
            }

            System.out.println("\nFILA DE PRIORIDADE 1");

            for (int i = 0; i < tamanho; i++) {
                System.out.println(heap[i]);
            }

            System.out.println();
        }

        public boolean vazia() {
            return tamanho == 0;
        }
    }

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        Heap fila = new Heap(100);

        int proximaSenha = 1;
        int opcao;

        do {
            System.out.println("    LABORATORIO DE EXAMES");
            System.out.println("1 - Adicionar paciente");
            System.out.println("2 - Atender paciente");
            System.out.println("3 - Mostrar fila");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- NOVO PACIENTE ---");

                    System.out.print("Nome: ");
                    String nome = entrada.nextLine();

                    System.out.print("Idade: ");
                    int idade = entrada.nextInt();
                    entrada.nextLine();

                    if (idade > 60) {
                        Paciente paciente = new Paciente(nome, idade, 1, proximaSenha);

                        fila.inserir(paciente);

                        System.out.println("\nPaciente cadastrado!");
                        System.out.println("Senha: " + proximaSenha);
                        System.out.println("Prioridade: 1");

                        proximaSenha++;
                    } 
                    break;

                case 2:
                    if (fila.vazia()) {
                        System.out.println("\nNao ha pacientes na fila.");
                    } else {
                        Paciente atendido = fila.remover();

                        System.out.println("\n--- ATENDIMENTO ---");
                        System.out.println("Paciente atendido:");
                        System.out.println(atendido);
                    }
                    break;

                case 3:
                    fila.mostrar();
                    break;

                case 0:
                    System.out.println("\nPrograma encerrado.");
                    break;

                default:
                    System.out.println("\nOpcao invalida!");
            }

        } while (opcao != 0);

        entrada.close();
    }
}