ECM306 - TÓPICOS AVANÇADOS EM ESTRUTURA DE DADOS
Aula 16 - Hashing
Prof. Calvetti - 2026

ARQUIVOS

Ex01_HashingSemColisao.java
Ex02_Encadeamento.java
Ex03_Rehashing.java

OBSERVAÇÃO
O PDF fornecido contém trechos de código nas páginas 2 a 5 que não
aparecem integralmente no texto extraído. Por isso, o Ex01 contém uma
implementação-base para a função hash e teste de colisões, em vez de
reproduzir código que não está disponível integralmente.

ENUNCIADOS

EXERCÍCIO 1 - HASHING INTERNO SEM TRATAMENTO DE COLISÃO
1. Completar o método hash() utilizando h = chave mod n.
2. Executar o código e avaliar a execução.
3. Responder se houve colisões e, se sim, quantas e quais.
4. Explicar como foi feito o tratamento de colisões.
5. Sugerir formas de tratamento de colisões.

EXERCÍCIO 2 - HASHING INTERNO COM TRATAMENTO DE COLISÃO - ENCADEAMENTO
1. Criar TestHash e SList no mesmo pacote.
2. Criar tabKeys com capacidade 20, desconsiderando a posição 0;
   chaves válidas de 1 a 19.
3. Criar tabHash com capacidade 10 e listas ligadas.
4. Escrever hash().
5. Carregar tabHash usando insereInicio().
6. Imprimir as listas e colisões.
7. Alterar para 100.000 chaves (1 a 99.999) e tabela com 1000 posições.

EXERCÍCIO 3 - HASHING INTERNO COM TRATAMENTO DE COLISÃO - REHASHING
1. Criar TestHash com main().
2. Usar as chaves 23, 45, 77, 11, 33, 49, 10, 4, 89 e 14.
3. Criar tabHash.
4. Escrever hash() pelo método da divisão.
5. Escrever rehashing() para encontrar a primeira posição livre.
6. Inserir usando hash() e, em caso de colisão, rehashing().
7. Imprimir todas as chaves.

Fonte: IMT-2026-ECM306-P16-ExercíciosPropostos-ProfCalvetti.pdf
