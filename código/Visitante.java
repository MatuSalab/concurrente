import java.util.concurrent.Semaphore;

public class Visitante implements Runnable {
    String id;
    Semaphore semaforo;
    Tobogan tobogan;

    public Visitante(String id, Semaphore semaforo, Tobogan tobogan) {
        this.id = id;
        this.semaforo = semaforo;
        this.tobogan = tobogan;
    }

    public void run(){
        try {
            System.out.println("Visitante " + id + " subiendo la escalera.");
            semaforo.acquire(); // Espera para entrar al tobogán
            System.out.println("Visitante " + id + " está usando el tobogán.");
            Thread.sleep( 1000); // Simula el tiempo en el tobogán
            System.out.println("Visitante " + id + " ha salido del tobogán.");
            semaforo.release(); // Libera el tobogán
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
