package Babuinos;

import java.util.concurrent.ThreadLocalRandom;

public class MainBabuinos {
    public static void main(String[] args) {
        Cuerda cuerda = new Cuerda();

        new Thread(new Babuino(1, 2, cuerda)).start();
        new Thread(new Babuino(2, 1, cuerda)).start();
        new Thread(new Babuino(3, 2, cuerda)).start();
        new Thread(new Babuino(4, 1, cuerda)).start();
        new Thread(new Babuino(5, 2, cuerda)).start();
        new Thread(new Babuino(6, 2, cuerda)).start();
        new Thread(new Babuino(7, 1, cuerda)).start();
        new Thread(new Babuino(8, 2, cuerda)).start();
        new Thread(new Babuino(9, 1, cuerda)).start();
        new Thread(new Babuino(10, 2, cuerda)).start();
        new Thread(new Babuino(11, 1, cuerda)).start();
        new Thread(new Babuino(12, 2, cuerda)).start();
        new Thread(new Babuino(13, 1, cuerda)).start();
    }
}
