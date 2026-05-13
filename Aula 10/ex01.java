/*1. De acordo com Cormen - Segunda Edição, Página 170, Capítulo 10
- Item 10.3 – Implementação de Ponteiros e Objetos, pode-se
implementar estruturas de dados ligadas sem um tipo de dados
ponteiro explícito.
• Ou seja, pode-se representar uma coleção de objetos que têm os
mesmos campos usando-se um arranjo para cada campo;
• Como exemplo, a figura abaixo mostra como se pode implementar
uma lista ligada com três arranjos:
• O objeto com chave 4 segue o objeto com chave 16 na lista ligada;
A chave 4 aparece em chave[2]; e a chave 16 aparece em chave[5];
assim, tem-se: próximo[5] = 2 e anterior[2] = 5
• A chave (key) do arranjo contém os valores das chaves presentes
atualmente no conjunto dinâmico e os ponteiros são armazenados
nos arranjos próximo (next) e anterior (prev);
• Para um dado índice de arranjo x, chave[x], próximo[x] e anterior[x]
representam um objeto na lista ligada;
• Sob essa interpretação, um ponteiro x é simplesmente um índice
comum para os arranjos chave, próximo e anterior;
• Na figura, uma variável L contém o índice do início da lista.
• Escrever, em Java, um programa que implementa as operações
básicas de Listas Ligadas com o esquema proposto:
1. Criação da estrutura de lista ligada a partir de uma lista de
valores;
2. Inserção de um elemento em uma lista ligada existente;
3. Deleção de um valor de uma lista ligada existente; e
4. Uma função que imprime os valores existentes em uma dada
lista ligada */