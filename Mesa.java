package br.ucsal.so.trabalho01;

public class Mesa {

    final static int FILOSOFANDO = 1;
    final static int COMENDO = 2;
    final static int QTD_FILOSOFOS = 5;
    final static int PRIMEIRO_FILOSOFO = 0;
    final static int ULTIMO_FILOSOFO = QTD_FILOSOFOS - 1;

    boolean[] garfos = new boolean[QTD_FILOSOFOS];
    int[] filosofos = new int[QTD_FILOSOFOS];
    int[] qtdTentativas = new int[QTD_FILOSOFOS];

    public Mesa() {
        for(int i = 0; i < QTD_FILOSOFOS; i++) {
            garfos[i] = true;
            filosofos[i] = FILOSOFANDO;
            qtdTentativas[i] = 0;
        }
    }

    public synchronized void pegarOsGarfos(int filosofo) {
        filosofos[filosofo] = FILOSOFANDO;
        while(filosofos[esquerda(filosofo)] == COMENDO || filosofos[direita(filosofo)] == COMENDO) {
            try {
                qtdTentativas[filosofo]++;
                garfos[garfoEsquerdo(filosofo)] = true;
                garfos[garfoEsquerdo(filosofo)] = true;
                wait();
            } catch (InterruptedException e) {

            }
        }

        qtdTentativas[filosofo] = 0;
        garfos[garfoEsquerdo(filosofo)] = false;
        garfos[garfoDireito(filosofo)] = false;
        filosofos[filosofo] = COMENDO;
        estadoDosFilosofos();
        imprimirGarfos();
        imprimirQtdTentativas();
    }

    public synchronized void devolverOsGarfos(int filosofo){
        garfos[garfoEsquerdo(filosofo)] = true;
        garfos[garfoEsquerdo(filosofo)] = true;
        if(filosofos[esquerda(filosofo)] == FILOSOFANDO || filosofos[direita(filosofo)] == FILOSOFANDO) {
            notifyAll();
        }
        filosofos[filosofo] = FILOSOFANDO;
        estadoDosFilosofos();
        imprimirGarfos();
        imprimirQtdTentativas();

    }

    public int direita (int filosofo) {
        int ladoDireito;
        if(filosofo == ULTIMO_FILOSOFO) {
            ladoDireito = PRIMEIRO_FILOSOFO;
        } else {
            ladoDireito = filosofo + 1;
        }
        return ladoDireito;
    }

    public int esquerda (int filosofo) {
        int ladoEsquerdo;
        if(filosofo == PRIMEIRO_FILOSOFO) {
            ladoEsquerdo = ULTIMO_FILOSOFO;
        } else {
            ladoEsquerdo = filosofo - 1;
        }
        return ladoEsquerdo;
    }

    public int garfoEsquerdo (int filosofo) {
        int garfoEsquerdo = filosofo;
        return garfoEsquerdo;
    }

    public int garfoDireito (int filosofo) {
        int garfoDireito;
        if(filosofo == ULTIMO_FILOSOFO) {
            garfoDireito = 0;
        } else {
            garfoDireito = filosofo + 1;
        }
        return garfoDireito;
    }

    public void estadoDosFilosofos() {
        String texto = " ";
        System.out.print("Estado dos filósofos = { ");
        for(int i = 0; i < QTD_FILOSOFOS; i++) {
            System.out.print("\nFilósofo "+i+" está:");
            switch(filosofos[i]){
                case FILOSOFANDO:
                    texto = "Filosofando";
                    break;
                case COMENDO:
                    texto = "Comendo";
                    break;
            }
            System.out.print(texto);
        }
        System.out.println(" \n}\n");
    }

    public void imprimirGarfos() {
        String garfo = " ";
        System.out.print("Estado dos garfos = {\n");
        for(int i = 0; i < QTD_FILOSOFOS; i++){
            if(garfos[i]) {
                garfo = "Livre";
            } else {
                garfo = "ocupado";
            }
            System.out.println("O garfo "+i+" está "+garfo );
        }
        System.out.println(" }\n");
    }

    public void imprimirQtdTentativas() {
        System.out.print("Quantas vezes cada filósofo tentou comer: {\n");
        for(int i = 0; i < QTD_FILOSOFOS; i++) {
            System.out.println("Filósofo "+i+" tentou comer "+ qtdTentativas[i]+ " vezes");
        }
        System.out.println(" }\n");
    }
}
