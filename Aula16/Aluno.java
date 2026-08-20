package Aula16;

public class Aluno {
    private String nome;
    private int codAluno;

    public Aluno () { }

    public Aluno(int codAluno, String nome) {
        this.codAluno = codAluno;
        this.nome = nome;
    }

    public int getcodAluno() {
        return codAluno;
    }

    public void setcodAluno(int codAluno) {
        this.codAluno = codAluno;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
