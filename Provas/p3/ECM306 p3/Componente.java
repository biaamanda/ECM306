public class Componente {
    private int codigo;
    private String descricao;

    public Componente (int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }


    @Override
        public String toString() {
            return "Codigo: " + codigo + "Descricao: " + descricao;
        }
}
