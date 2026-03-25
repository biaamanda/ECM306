/*Considere dois computadores C1 e C2 que executam 10^8 e 10^10 operações por
segundo e dois algoritmos de ordenação A e B que necessitam 5n^2 e 40n log10 n
operações com entrada de tamanho n, respectivamente. Qual o tempo de execução
de cada algoritmo em cada um dos computadores C1 e C2 para ordenar 10^8
elementos? */

/*C1 = 10^8 operações /seg e C2 = 10^10 operações/seg

Algoritmo A = 5n^2 e Algoritmo B = 40n x log10(n)

Para o Algoritmo A:

5 x (10^8)^2 ⇒ 5 x 10^16 operações

40x10^8 x log10(10^8) ⇒ 3,2 x 10^10 operações

Para calcular o tempo de cada um:

**Algoritmo A no C1: (**5×10^16)/10^8 = 5×10^8 seg = 15,8 anos

**Algoritmo A no C2: (**5 × 10^16)/10^10 = 5 × 10^6 segundos = 57,8 dias

**Algoritmo B no C1: (**3,2 × 10^10)/10^8 = 320 segundos = 5,3 min

**Algoritmo B no C2: (**3,2 × 10^10)/10^10 = 3,2 seg */
