package br.ucsal.so.trabalho01;


public class Refeicao {
    public static void main(String[] args) {

        executar();
    }

    public static void executar() {
        int qtd_comendo = 2;
        int qtd_filosofos = 5;
        Mesa mesa = new Mesa();



        new Filosofo("Aristóteles",mesa,0).start();
        new Filosofo("Platão",mesa,1).start();
        new Filosofo("Descartes",mesa,2).start();
        new Filosofo("Sócrates",mesa,3).start();
        new Filosofo("Nietzsche",mesa,4).start();
    }
}
