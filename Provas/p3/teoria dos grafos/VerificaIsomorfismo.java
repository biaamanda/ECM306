/* Unidade 17 - Introducao a Teoria dos Grafos
 *
 * ISOMORFISMO DE GRAFOS - checagem por INVARIANTES (Definicao 3.6 / 3.5).
 *
 * G1 e G2 isomorfos exigem bijecoes f (vertices) e g (arestas) que
 * preservam incidencia. Provar isomorfismo de verdade exige exibir f e g;
 * o que da para automatizar facilmente (e o que a prova costuma cobrar) e
 * conferir os INVARIANTES NECESSARIOS:
 *
 *   1) mesmo numero de vertices;
 *   2) mesmo numero de arestas;
 *   3) mesma sequencia de graus (mesma quantidade de vertices de cada grau).
 *
 * IMPORTANTE: essas condicoes sao necessarias, mas NAO suficientes. Se
 * UMA delas falhar, os grafos com certeza NAO sao isomorfos. Se as tres
 * baterem, ainda pode ser que NAO sejam isomorfos (Exemplo 3.8 dos
 * slides, reproduzido abaixo).
 */

import java.util.List;

public class VerificaIsomorfismo {

    // Confere so os invariantes necessarios (nao decide isomorfismo de fato).
    public static boolean mesmosInvariantes(Grafo g1, Grafo g2) {
        if (g1.numeroDeVertices() != g2.numeroDeVertices()) {
            System.out.println("  -> numero de vertices diferente: "
                    + g1.numeroDeVertices() + " x " + g2.numeroDeVertices());
            return false;
        }
        if (g1.numeroDeArestas() != g2.numeroDeArestas()) {
            System.out.println("  -> numero de arestas diferente: "
                    + g1.numeroDeArestas() + " x " + g2.numeroDeArestas());
            return false;
        }
        List<Integer> seq1 = g1.sequenciaDeGraus();
        List<Integer> seq2 = g2.sequenciaDeGraus();
        if (!seq1.equals(seq2)) {
            System.out.println("  -> sequencia de graus diferente: " + seq1 + " x " + seq2);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("--- Exemplo 3.11: G1 tem 7 arestas, G2 tem 6 (invariante falha rapido) ---");
        Grafo g1a = new Grafo();
        g1a.adicionarAresta("a", "b");
        g1a.adicionarAresta("b", "c");
        g1a.adicionarAresta("c", "d");
        g1a.adicionarAresta("a", "d");
        g1a.adicionarAresta("a", "c");
        g1a.adicionarAresta("b", "d");
        g1a.adicionarAresta("a", "b"); // 7a aresta (paralela)

        Grafo g2a = new Grafo();
        g2a.adicionarAresta("w", "x");
        g2a.adicionarAresta("x", "y");
        g2a.adicionarAresta("y", "z");
        g2a.adicionarAresta("z", "w");
        g2a.adicionarAresta("w", "y");
        g2a.adicionarAresta("x", "z");

        System.out.println("mesmos invariantes? " + mesmosInvariantes(g1a, g2a)
                + "  => NAO isomorfos (esperado)");

        System.out.println("\n--- No espirito do Exemplo 3.8: mesma sequencia (1,1,1,2,2,3), NAO isomorfos ---");
        // Grafo A: vertice 'a' (grau 3) com DOIS vizinhos de grau 1 (l1,l2)
        // e uma "cauda" a-m1-m2-l3 (m1 e m2 de grau 2, l3 de grau 1).
        Grafo grafoA = new Grafo();
        grafoA.adicionarAresta("a", "l1");
        grafoA.adicionarAresta("a", "l2");
        grafoA.adicionarAresta("a", "m1");
        grafoA.adicionarAresta("m1", "m2");
        grafoA.adicionarAresta("m2", "l3");

        // Grafo B: vertice 'b' (grau 3) com apenas UM vizinho de grau 1 (z)
        // e DUAS "caudas" curtas separadas: b-x-w e b-y-v (x,y de grau 2).
        Grafo grafoB = new Grafo();
        grafoB.adicionarAresta("b", "z");
        grafoB.adicionarAresta("b", "x");
        grafoB.adicionarAresta("x", "w");
        grafoB.adicionarAresta("b", "y");
        grafoB.adicionarAresta("y", "v");

        boolean invariantesIguais = mesmosInvariantes(grafoA, grafoB);
        System.out.println("mesmos invariantes? " + invariantesIguais + "  (esperado true)");
        System.out.println("sequencia de graus (ambos) = " + grafoA.sequenciaDeGraus());
        System.out.println("MAS nao sao isomorfos: em A, o vertice de grau 3 tem DOIS vizinhos");
        System.out.println("de grau 1 diretamente (l1,l2); em B, o vertice de grau 3 tem so UM");
        System.out.println("vizinho de grau 1 direto (z) - as outras duas ligacoes passam por");
        System.out.println("vertices de grau 2 antes de chegar numa folha. Estruturas diferentes.");
        System.out.println("=> invariantes batendo NAO prova isomorfismo, so a ausencia deles prova a negativa.");
    }
}
