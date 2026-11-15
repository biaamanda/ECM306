public class TabelaHash {
    private Componente[] tabela;

    private int tamanho = [TAMANHO];



    
    public void inserir() {
        
    }

    public Componente hash(){
        return codigo % 11;
    }

    public Componente busca(int codigo) {
    
        int indiceInicial = hash(codigo);

        for (int i = 0; i < TAMANHO; i++) {
            int indice = (indiceInicial + i) % TAMANHO;

            if (tabela[indice] == null) {
                return null;
            }
            if (tabela[indice].getCodigo() == codigo) {
                return tabela[indice];
            }
        }

        return null;
    }

    public void mostrar() {
        System.out.println("Tabela Hash: ");
    }
}
