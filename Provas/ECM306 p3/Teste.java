/*Crie uma classe de execução que realize, obrigatoriamente, nesta ordem:
Inserir (22, "Resistor")
Inserir (13, "Capacitor")
Inserir (35, "Diodo")
Inserir (24, "Transistor")
Inserir (46, "Sensor")
Inserir (57, "Microcontrolador")
Buscar 46
Inserir novamente o código 35
Buscar 99
Exibir toda a tabela

A aplicação deverá produzir resultados que permitam verificar
claramente:
 A posição final de cada componente;
 O sucesso da busca por 46;
 A rejeição do código duplicado 35;
 O resultado negativo da busca por 99. */

public class Teste {
    public static void main(String[] args) {

    tabela.inserir(new Componente(22, "Resistor"));
    tabela.inserir(new Componente(13, "Capacitor"));
    tabela.inserir(new Componente(35, "Diodo"));
    tabela.inserir(new Componente(24, "Transistor"));
    tabela.inserir(new Componente(46, "Sensor"));
    tabela.inserir(new Componente(57, "Microcontrolador"));


    System.out.println("Buscar codigo 46: ");
    Componente c46 = tabela.buscar(46);
        System.out.println(c46 != null ? "Encontrado -> " + c46 : "Nao encontrado.");

    System.out.println("Inserir codigo 35: ");
    Componente c35 = tabela.buscar(35);
        System.out.println(c35 != null ? "Encontrado -> " + c35 : "Nao encontrado.");

    System.out.println("Buscar codigo 99: ");
    Componente c99 = tabela.buscar(99);
        System.out.println(c99 != null ? "Encontrado -> " + c99 : "Nao encontrado.");
        //deve dar erro - nao encontrado


    tabela.mostrar();
    }
}
