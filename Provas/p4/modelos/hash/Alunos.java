// Lista encadeada feita a mao (nao pode usar java.util)
class No {
    String nome; No prox;
    No(String nome) { this.nome = nome; }
}

class TabelaHash {
    private final No[] tabela = new No[26];

    private int hash(String nome) { return Character.toUpperCase(nome.charAt(0)) - 'A'; }

    void inserir(String nome) {            // encadeamento separado: insere no FIM
        int i = hash(nome);
        No novo = new No(nome);
        if (tabela[i] == null) { tabela[i] = novo; return; }
        No p = tabela[i];
        while (p.prox != null) p = p.prox;
        p.prox = novo;
    }

    int buscar(String nome) {              // retorna nr de tentativas (-1 se nao achou)
        int tentativas = 0;
        for (No p = tabela[hash(nome)]; p != null; p = p.prox) {
            tentativas++;
            if (p.nome.equals(nome)) return tentativas;
        }
        return -1;
    }

    void imprimir() {
        boolean vazia = true;
        StringBuilder sb = new StringBuilder("Indice Nome\n");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] == null) continue;
            vazia = false;
            sb.append(i).append("  ");
            for (No p = tabela[i]; p != null; p = p.prox)
                sb.append(p.nome).append(p.prox == null ? ";" : ", ");
            sb.append('\n');
        }
        System.out.print(vazia ? "VAZIA\n" : sb.toString());
    }
}

public class Alunos {
    public static void main(String[] args) {
        TabelaHash t = new TabelaHash();
        t.imprimir();
        for (String n : new String[]{"PAULO", "ZULEIDE", "ANTONIO", "PEDRO", "PALOMA"}) { t.inserir(n); t.imprimir(); }
        for (String n : new String[]{"PAULO", "ZULEIDE", "PALOMA"})
            System.out.println(n + " localizado em " + t.buscar(n) + " tentativa(s)");
    }
}
