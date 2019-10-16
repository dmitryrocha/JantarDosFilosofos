package br.ucsal.so.trabalho01;


import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    final static int LIMITE_TEMPO = 100;
    final static int TEMPO_DE_ACAO = 100;
    private String nome;
    private Mesa mesa;
    private int filosofo;



    public Filosofo(String nome, Mesa mesaDeRefeicoes, int filosofo) {
        super(nome);
        this.mesa = mesaDeRefeicoes;
        this.filosofo = filosofo;
    }


    public void run() {
        int tempo = 0;
        while(true){
            tempo = (int) (Math.random() * TEMPO_DE_ACAO);
            filosofar(tempo);
            getGarfos();
            tempo = (int) (Math.random() * LIMITE_TEMPO);
            comer(tempo);
            devolverGarfos();
        }

    }

    public void filosofar (int tempo) {
        try {
            mesa.devolverOsGarfos(filosofo);
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("Este filósofo filosofou demais. Deu dor de cabeça");
        }
    }

    public void comer (int tempo) {
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println("Este filósofo comeu demais. Está com indigestão");
        }
    }

    public void getGarfos() {

        mesa.pegarOsGarfos(filosofo);
    }

    public void devolverGarfos() {

        mesa.devolverOsGarfos(filosofo);
    }
}
