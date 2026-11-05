/**
 * Aresta de um grafo de fluxo.
 *
 * Toda aresta tem uma "reversa" (a aresta que vai no sentido contrário).
 * Quando mandamos fluxo f por esta aresta, a reversa recebe -f.
 * Isso permite que o algoritmo "desfaça" fluxo mandado por um caminho ruim.
 */
public class Aresta {
    final int destino;
    final int capacidade;
    int fluxo;
    Aresta reversa;

    Aresta(int destino, int capacidade) {
        this.destino = destino;
        this.capacidade = capacidade;
    }

    /** Quanto ainda cabe passar por esta aresta (capacidade residual). */
    int residual() {
        return capacidade - fluxo;
    }

    /** Empurra f unidades de fluxo por esta aresta e atualiza a reversa. */
    void empurrar(int f) {
        fluxo += f;
        reversa.fluxo -= f;
    }
}
