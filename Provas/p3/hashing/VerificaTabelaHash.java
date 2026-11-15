/* Unidade 16 - Hashing
 *
 * Utilitario para responder rapido perguntas do tipo:
 *   "Calcule h(k) para as chaves ... numa tabela de tamanho m.
 *    Quais colidem? Qual o fator de carga?"
 *
 * Nao guarda nada de fato - so calcula e imprime um relatorio.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerificaTabelaHash {

    public static int hash(int chave, int m) {
        return chave % m;
    }

    public static void analisar(int[] chaves, int m) {
        System.out.println("Tabela com m = " + m + " slots. h(k) = k mod " + m);
        System.out.println();

        // slot -> lista de chaves que cairam nele
        Map<Integer, java.util.List<Integer>> porSlot = new HashMap<>();

        for (int chave : chaves) {
            int slot = hash(chave, m);
            porSlot.computeIfAbsent(slot, k -> new java.util.ArrayList<>()).add(chave);
            System.out.println("h(" + chave + ") = " + slot);
        }

        System.out.println();
        int colisoes = 0;
        for (Map.Entry<Integer, List<Integer>> entrada : porSlot.entrySet()) {
            List<Integer> nesteSlot = entrada.getValue();
            if (nesteSlot.size() > 1) {
                colisoes += nesteSlot.size() - 1;
                System.out.println("COLISAO no slot " + entrada.getKey() + ": " + nesteSlot);
            }
        }
        if (colisoes == 0) {
            System.out.println("Nenhuma colisao.");
        } else {
            System.out.println("\nTotal de colisoes: " + colisoes);
        }

        System.out.printf("Fator de carga (n/m) = %d/%d = %.2f%n",
                chaves.length, m, (double) chaves.length / m);
        System.out.println("Slots ocupados: " + porSlot.size() + " de " + m);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== Exemplo do slide 46 (m = 80) ===");
        analisar(new int[]{383, 487, 235, 527, 510, 564, 103, 66, 14}, 80);

        System.out.println("\n=== Exemplo do slide 49 (colisao: 100, 180, 260) ===");
        analisar(new int[]{100, 180, 260}, 80);

        System.out.println("\n=== Exercicio 1/3 (m = 10) ===");
        analisar(new int[]{23, 45, 77, 11, 33, 49, 10, 4, 89, 14}, 10);

        System.out.println("\n=== Exercicio 4 (m = 11) ===");
        analisar(new int[]{73, 15, 44, 37, 30, 59, 49, 99}, 11);
    }
}
