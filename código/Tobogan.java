import java.util.concurrent.Semaphore;

public class Tobogan {
    String id; int capacidad; Semaphore semaforo;

    public Tobogan(String id, int capacidad, Semaphore semaforo) {
        this.id = id;
        this.capacidad = capacidad;
        this.semaforo = semaforo;
    }

    public String getId() {
        return id;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
