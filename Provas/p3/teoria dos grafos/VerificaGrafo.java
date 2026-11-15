/* Unidade 17 - Introducao a Teoria dos Grafos
 *
 * Roda os Exercicios 1 a 6 dos slides ("Grafos - Exemplo"), que pedem:
 *   a) a quantidade de arestas do grafo
 *   b) a soma dos graus de todos os vertices
 * e (nos exercicios 4, 5 e 6, sobre os MESMOS tres grafos) quantos e quais
 * sao os vertices de grau impar.
 *
 * Os tres grafos abaixo sao exatamente os resolvidos (na mao) em
 * Aula17/Grafos_Exercicios_Resolucao.md - aqui a gente confere tudo com
 * codigo, usando a classe Grafo.java.
 */

import java.util.List;

public class VerificaGrafo {

    // Grafo do Exercicio 1 / 4: V={v1..v5}
    static Grafo grafoExercicio1() {
        Grafo g = new Grafo();
        g.adicionarAresta("v1", "v5"); // e1
        g.adicionarAresta("v1", "v2"); // e2
        g.adicionarAresta("v1", "v2"); // e3 (paralela)
        g.adicionarAresta("v5", "v4"); // e4
        g.adicionarAresta("v2", "v4"); // e5
        g.adicionarAresta("v2", "v3"); // e6
        g.adicionarAresta("v4", "v3"); // e7
        g.adicionarAresta("v3", "v3"); // e8 (laco)
        return g;
    }

    // Grafo do Exercicio 2 / 5: V={V1..V9}
    static Grafo grafoExercicio2() {
        Grafo g = new Grafo();
        g.adicionarAresta("V1", "V2");
        g.adicionarAresta("V1", "V4");
        g.adicionarAresta("V1", "V4"); // paralela
        g.adicionarAresta("V2", "V3");
        g.adicionarAresta("V2", "V7");
        g.adicionarAresta("V5", "V7");
        g.adicionarAresta("V5", "V6");
        g.adicionarAresta("V6", "V7");
        g.adicionarAresta("V3", "V8");
        g.adicionarAresta("V8", "V9");
        g.adicionarAresta("V8", "V9"); // paralela
        return g;
    }

    // Grafo do Exercicio 3 / 6: igual ao Exercicio 2, com lacos em V1, V9
    // e mais uma paralela em V2-V7 e V5-V6.
    static Grafo grafoExercicio3() {
        Grafo g = new Grafo();
        g.adicionarAresta("V1", "V1"); // laco
        g.adicionarAresta("V2", "V2"); // laco
        g.adicionarAresta("V1", "V2");
        g.adicionarAresta("V1", "V4");
        g.adicionarAresta("V1", "V4"); // paralela
        g.adicionarAresta("V2", "V7");
        g.adicionarAresta("V2", "V7"); // paralela
        g.adicionarAresta("V5", "V7");
        g.adicionarAresta("V5", "V6");
        g.adicionarAresta("V5", "V6"); // paralela
        g.adicionarAresta("V6", "V7");
        g.adicionarAresta("V2", "V3");
        g.adicionarAresta("V3", "V8");
        g.adicionarAresta("V8", "V9");
        g.adicionarAresta("V8", "V9"); // paralela
        g.adicionarAresta("V9", "V9"); // laco
        return g;
    }

    static void relatorio(String nome, Grafo g, int arestasEsperadas, int somaEsperada) {
        System.out.println("=== " + nome + " ===");
        System.out.println("arestas          = " + g.numeroDeArestas()
                + "  (esperado " + arestasEsperadas + ")");
        System.out.println("soma dos graus   = " + g.somaDosGraus()
                + "  (esperado " + somaEsperada + ")");
        System.out.println("Teorema 1 ok?    = " + g.verificaTeorema1());

        List<String> impares = g.verticesImpares();
        System.out.println("vertices impares = " + impares + "  (" + impares.size() + ")");
        System.out.println("Teorema 2 ok?    = " + g.verificaTeorema2());
        System.out.println();
    }

    public static void main(String[] args) {
        relatorio("Exercicio 1/4 (v1..v5)", grafoExercicio1(), 8, 16);
        relatorio("Exercicio 2/5 (V1..V9)", grafoExercicio2(), 11, 22);
        relatorio("Exercicio 3/6 (V1..V9 com lacos)", grafoExercicio3(), 16, 32);

        System.out.println("Esperado (do gabarito em Grafos_Exercicios_Resolucao.md):");
        System.out.println("  Ex.1: 8 arestas, soma 16, impares {v1,v4} -> 2");
        System.out.println("  Ex.2: 11 arestas, soma 22, impares {V1,V2,V7,V8} -> 4");
        System.out.println("  Ex.3: 16 arestas, soma 32, impares {V1,V5,V6,V8} -> 4");
    }
}
