/*
 * BASE: TABELA HASH (sem usar estruturas prontas do Java)
 * ---------------------------------------------------------------------------
 * USE QUANDO a questao pedir: tabela hash / hashing / dispersao, guardar nomes
 * ou numeros, buscar contando TENTATIVAS, tratar COLISOES.
 *
 * DUAS FORMAS DE TRATAR COLISAO (os dois estao aqui):
 *   1) ENCADEAMENTO SEPARADO  (o professor chama de "HASHING ABERTO")
 *      -> cada posicao tem uma LISTA; quem colide entra no fim da lista.
 *   2) ENDERECAMENTO ABERTO / SONDAGEM LINEAR  ("hashing fechado")
 *      -> quem colide procura a PROXIMA posicao livre: (h + i) % tamanho.
 *
 * PARA ADAPTAR, procure os comentarios "ALTERE".
 *
 * GERAR O JAR:
 *   javac --release 8 -Xlint:-options *.java
 *   jar cfe Hash.jar BaseHash *.class
 *   java -jar Hash.jar
 */

import java.util.Scanner;

// No da lista encadeada feita a mao
class No {
    String chave;
    No prox;
    No(String chave) { this.chave = chave; }
}

// ---------------------------------------------------------------------------
// 1) ENCADEAMENTO SEPARADO ("hashing aberto")
// ---------------------------------------------------------------------------
class HashEncadeado {
    // ALTERE: tamanho da tabela. Letra inicial -> 26 posicoes (A..Z).
    private final No[] tabela = new No[26];

    // ALTERE: FUNCAO HASH. Opcoes comuns:
    //   letra inicial:     Character.toUpperCase(chave.charAt(0)) - 'A'
    //   numero (divisao):  Integer.parseInt(chave) % tabela.length
    //   soma dos chars:    somaDosCaracteres(chave) % tabela.length
    int hash(String chave) {
        return Character.toUpperCase(chave.charAt(0)) - 'A';
    }

    void inserir(String chave) {                 // insere no FIM da lista
        int i = hash(chave);
        No novo = new No(chave);
        if (tabela[i] == null) { tabela[i] = novo; return; }
        No p = tabela[i];
        while (p.prox != null) p = p.prox;
        p.prox = novo;
    }

    // Devolve o numero de TENTATIVAS (comparacoes) ate achar, ou -1 se nao achou.
    int buscar(String chave) {
        int tentativas = 0;
        for (No p = tabela[hash(chave)]; p != null; p = p.prox) {
            tentativas++;
            if (p.chave.equals(chave)) return tentativas;
        }
        return -1;
    }

    boolean remover(String chave) {
        int i = hash(chave);
        No ant = null;
        for (No p = tabela[i]; p != null; ant = p, p = p.prox) {
            if (p.chave.equals(chave)) {
                if (ant == null) tabela[i] = p.prox; else ant.prox = p.prox;
                return true;
            }
        }
        return false;
    }

    // ALTERE: formato de saida pedido pela questao (aqui: "15 PAULO, PEDRO;")
    String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] == null) continue;          // so mostra posicoes ocupadas
            sb.append(i).append("  ");
            for (No p = tabela[i]; p != null; p = p.prox)
                sb.append(p.chave).append(p.prox == null ? ";" : ", ");
            sb.append('\n');
        }
        return sb.length() == 0 ? "VAZIA\n" : "Indice Nome\n" + sb;
    }
}

// ---------------------------------------------------------------------------
// 2) ENDERECAMENTO ABERTO com SONDAGEM LINEAR ("hashing fechado")
// ---------------------------------------------------------------------------
class HashSondagemLinear {
    // ALTERE: tamanho da tabela (de preferencia um numero primo, ex.: 11, 13)
    private final String[] tabela = new String[11];

    // ALTERE: FUNCAO HASH
    int hash(String chave) {
        int soma = 0;
        for (char c : chave.toCharArray()) soma += c;
        return soma % tabela.length;
    }

    // Devolve a posicao onde guardou, ou -1 se a tabela esta cheia.
    int inserir(String chave) {
        int h = hash(chave);
        for (int i = 0; i < tabela.length; i++) {
            int pos = (h + i) % tabela.length;        // ALTERE: quadratica = (h + i*i) % tamanho
            if (tabela[pos] == null) { tabela[pos] = chave; return pos; }
        }
        return -1;
    }

    // Devolve o numero de TENTATIVAS ate achar, ou -1.
    int buscar(String chave) {
        int h = hash(chave);
        for (int i = 0; i < tabela.length; i++) {
            int pos = (h + i) % tabela.length;
            if (tabela[pos] == null) return -1;       // achou buraco -> nao existe
            if (tabela[pos].equals(chave)) return i + 1;
        }
        return -1;
    }

    String mostrar() {
        StringBuilder sb = new StringBuilder("Indice Valor\n");
        for (int i = 0; i < tabela.length; i++)
            sb.append(i).append("  ").append(tabela[i] == null ? "-" : tabela[i]).append('\n');
        return sb.toString();
    }
}

public class BaseHash {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ALTERE: troque por "new HashSondagemLinear()" se a questao pedir enderecamento aberto
        // (e ajuste o tipo da variavel).
        HashEncadeado t = new HashEncadeado();

        System.out.print(t.mostrar());
        while (true) {
            System.out.print("\n1-Inserir  2-Buscar  3-Remover  0-Sair: ");
            String op = sc.nextLine().trim();
            if (op.equals("0")) break;
            System.out.print("Nome: ");
            String nome = sc.nextLine().trim().toUpperCase();   // ALTERE: tire o toUpperCase se precisar
            if (nome.isEmpty()) continue;

            if (op.equals("1")) {
                t.inserir(nome);
                System.out.print(t.mostrar());                  // mostra a tabela apos cada insercao
            } else if (op.equals("2")) {
                int tent = t.buscar(nome);
                System.out.println(tent == -1 ? "Nao encontrado"
                        : "Localizado em " + tent + " tentativa(s)");
            } else if (op.equals("3")) {
                System.out.println(t.remover(nome) ? "Removido" : "Nao encontrado");
                System.out.print(t.mostrar());
            }
        }
        sc.close();
    }
}
