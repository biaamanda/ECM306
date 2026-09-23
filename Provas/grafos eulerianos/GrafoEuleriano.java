/* Unidade 18 - Grafos Eulerianos
 *
 * TEOREMA DE EULER: um grafo CONEXO G e Euleriano
 * se e somente se o grau de TODO vertice de G for PAR.
 *
 * Extensao (semi-euleriano): existe trilha Euleriana ABERTA
 * se e somente se G for conexo e tiver EXATAMENTE 0 ou 2 vertices de grau
 * impar (0 -> na verdade e Euleriano/fechada; 2 -> so aberta).
 */

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GrafoEuleriano {

    // Grafo de Euler "de verdade": conexo + todos os graus pares.
    public static boolean eEuleriano(Grafo g) {
        return g.eConexo() && g.verticesGrauImpar().isEmpty();
    }

    // Semi-euleriano: conexo + exatamente 2 vertices de grau impar
    // (tem trilha Euleriana aberta, mas nao circuito).
    public static boolean eSemiEuleriano(Grafo g) {
        return g.eConexo() && g.verticesGrauImpar().size() == 2;
    }

    public static String classificar(Grafo g) {
        if (!g.eConexo()) {
            return "NAO euleriano (grafo desconexo)";
        }
        int impares = g.verticesGrauImpar().size();
        if (impares == 0) {
            return "EULERIANO (circuito fechado existe)";
        }
        if (impares == 2) {
            return "SEMI-EULERIANO (so trilha aberta existe, entre os 2 vertices de grau impar)";
        }
        return "NAO euleriano (" + impares + " vertices de grau impar; precisa ser 0 ou 2)";
    }

    // Confere se uma sequencia de vertices e de fato uma trilha Euleriana
    // FECHADA: usa cada aresta do grafo exatamente uma vez e comeca/termina
    // no mesmo vertice. Util para validar um circuito "na mao".
    public static boolean verificarCircuitoEuleriano(Grafo g, String... sequenciaVertices) {
        if (sequenciaVertices.length < 2) return false;
        if (!sequenciaVertices[0].equals(sequenciaVertices[sequenciaVertices.length - 1])) {
            System.out.println("  -> nao comeca e termina no mesmo vertice.");
            return false;
        }

        Set<Grafo.Aresta> naoUsadas = new LinkedHashSet<>(g.getArestas());

        for (int i = 0; i < sequenciaVertices.length - 1; i++) {
            String de = sequenciaVertices[i];
            String para = sequenciaVertices[i + 1];

            Grafo.Aresta usada = null;
            for (Grafo.Aresta e : naoUsadas) {
                boolean liga = (e.a.equals(de) && e.b.equals(para))
                        || (e.a.equals(para) && e.b.equals(de));
                if (liga) {
                    usada = e;
                    break;
                }
            }
            if (usada == null) {
                System.out.println("  -> nao ha aresta livre entre " + de + " e " + para
                        + " no passo " + (i + 1) + ".");
                return false;
            }
            naoUsadas.remove(usada);
        }

        if (!naoUsadas.isEmpty()) {
            System.out.println("  -> sobraram arestas nao usadas: " + naoUsadas);
            return false;
        }
        return true;
    }

    // ------------------------------------------------------------------
    // Testes - grafos G1..G4 dos slides 44, 65-70.
    // ------------------------------------------------------------------

    // G1 (slide 44): caminho v1-v2-...-v8 (so tem trilha Euleriana ABERTA).
    static Grafo grafoG1Caminho() {
        Grafo g = new Grafo();
        String[] v = {"v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"};
        for (int i = 0; i < v.length - 1; i++) {
            g.adicionarAresta("e" + (i + 1), v[i], v[i + 1]);
        }
        return g;
    }

    // G2 (slides 65-66): reconstruido a partir do proprio circuito do slide
    // "V1-V9-V2-V4-V3-V7-V9-V8-V7-V6-V5-V4-V7-V2-V1" (cada par vira uma aresta).
    static Grafo grafoG2Grade() {
        Grafo g = new Grafo();
        String[] circuito = {
                "V1", "V9", "V2", "V4", "V3", "V7", "V9", "V8",
                "V7", "V6", "V5", "V4", "V7", "V2", "V1"
        };
        for (int i = 0; i < circuito.length - 1; i++) {
            g.adicionarAresta("e" + (i + 1), circuito[i], circuito[i + 1]);
        }
        return g;
    }

    // G3 (slides 67-68): K5 - grafo completo com 5 vertices.
    static Grafo grafoG3K5() {
        Grafo g = new Grafo();
        String[] v = {"V1", "V2", "V3", "V4", "V5"};
        int id = 1;
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                g.adicionarAresta("e" + (id++), v[i], v[j]);
            }
        }
        return g;
    }

    // G4 (slides 69-70): K3,3 - bipartido completo, todo vertice grau 3 (impar).
    static Grafo grafoG4K33() {
        Grafo g = new Grafo();
        String[] x = {"x1", "x2", "x3"};
        String[] y = {"y1", "y2", "y3"};
        int id = 1;
        for (String xi : x) {
            for (String yj : y) {
                g.adicionarAresta("e" + (id++), xi, yj);
            }
        }
        return g;
    }

    public static void main(String[] args) {
        Grafo g1 = grafoG1Caminho();
        System.out.println("--- G1: caminho v1..v8 ---");
        System.out.println("graus impares = " + g1.verticesGrauImpar() + " (esperado: v1 e v8, extremos)");
        System.out.println("classificacao = " + classificar(g1));
        System.out.println("Esperado: SEMI-EULERIANO");

        Grafo g2 = grafoG2Grade();
        System.out.println("\n--- G2: grade (slides 65-66) ---");
        System.out.println("graus impares = " + g2.verticesGrauImpar() + " (esperado: nenhum)");
        System.out.println("classificacao = " + classificar(g2));
        System.out.println("Esperado: EULERIANO");
        System.out.println("Confere o circuito do slide 66:");
        boolean ok = verificarCircuitoEuleriano(g2, "V1", "V9", "V2", "V4", "V3", "V7",
                "V9", "V8", "V7", "V6", "V5", "V4", "V7", "V2", "V1");
        System.out.println("  circuito valido? " + ok);

        Grafo g3 = grafoG3K5();
        System.out.println("\n--- G3: K5 (slides 67-68) ---");
        System.out.println("graus impares = " + g3.verticesGrauImpar() + " (esperado: nenhum, K5 e 4-regular)");
        System.out.println("classificacao = " + classificar(g3));
        System.out.println("Esperado: EULERIANO");
        System.out.println("Confere o circuito do slide 68:");
        boolean okK5 = verificarCircuitoEuleriano(g3, "V1", "V2", "V3", "V1", "V4", "V2",
                "V5", "V3", "V4", "V5", "V1");
        System.out.println("  circuito valido? " + okK5);

        Grafo g4 = grafoG4K33();
        System.out.println("\n--- G4: K3,3 (slides 69-70) ---");
        System.out.println("graus impares = " + g4.verticesGrauImpar()
                + " (esperado: todos os 6, K3,3 e 3-regular)");
        System.out.println("classificacao = " + classificar(g4));
        System.out.println("Esperado: NAO euleriano (6 vertices de grau impar)");
    }
}
