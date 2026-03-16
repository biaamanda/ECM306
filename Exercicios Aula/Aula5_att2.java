//Um algoritmo tem complexidade 2n^2. Num certo computador, num tempo t, o algoritmo resolve um problema de tamanho 25. Imagine agora que se tenha disponível um computador 100 vezes mais rápido. Qual o tamanho máximo de problema que o mesmo algoritmo resolve no mesmo tempo t no computador mais rápido?
//Considere o mesmo problema para um algoritmo de complexidade 2n.

public class Aula5_att2 {
    
}

//f(n) = 2n^2
//f = 2*25^2 = 1250 operacoes

//f(x) = 1250 * 100 = 125000 operacoes
//125000 = 2x^2
//x = 250 entradas
//25 -> 250 entradas => 10x mais entradas => O(f(n)) = n^2 => 10^2 = 100x mais operacoes

//parte 2
//f(n) = 2^n
//f = 2^25 operacoes = 3.355.443.200 operacoes

//log(2) 2^n = log(2) 3.355.443.200
//x ≈ 31.6 entradas = 31 entradas