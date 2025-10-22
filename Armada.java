public class Armada implements Runnable {
    String[] oraciones;
    Division[] divisiones;
    public Armada(String[] oraciones, int cantDivisiones) {
        this.oraciones = oraciones;
        this.divisiones = new Division[cantDivisiones];
        for (int i = 0; i < cantDivisiones; i++) {
            divisiones[i] = new Division(i);
        }
    }

    public void run() {
        for (int i = 0; i < oraciones.length; i++) {
            divisiones[i].comunicar(oraciones[i], oraciones[oraciones.length - 1 - i]);
        }
        for (Division division : divisiones) {
            new Thread(division).start();
        }
    }

}
