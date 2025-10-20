import java.util.concurrent.Semaphore;
    /*String id;
    Semaphore pedido;
    Semaphore llegada;

    public Pasajero(String id, Semaphore p, Semaphore l) {
        this.id = id; this.pedido = p; this.llegada = l;
    }

    public void run() {
        try {
            System.out.println("El pasajero " + id + " está esperando un permiso para tomar un taxi.");
            pedido.release();
            llegada.acquire();
            System.out.println("El pasajero " + id + " ha obtenido un permiso y está tomando un taxi.");
            // Simular el tiempo que tarda en tomar un taxi
            Thread.sleep(500);
            System.out.println("El pasajero " + id + " ha tomado un taxi y libera el permiso.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } */

    

public class Pasajero implements Runnable {
    private final int id;
    private final Semaphore pedido;   // para avisar al taxista
    private final Semaphore llegada;  // para esperar la llegada

    public Pasajero(int id, Semaphore pedido, Semaphore llegada) {
        this.id = id;
        this.pedido = pedido;
        this.llegada = llegada;
    }

    @Override
    public void run() {
        try {
            // Simula caminar buscando taxi
            Thread.sleep(500);

            System.out.printf("Pasajero %d: ¡Quiero un taxi!%n", id);
            pedido.release();              // despierta/cola al taxista
            llegada.acquire(); // espera aviso de llegada
            System.out.printf("Pasajero %d: Llegué al destino.%n", id);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

