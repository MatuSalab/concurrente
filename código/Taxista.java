import java.util.concurrent.Semaphore;

/*public class Taxista implements Runnable {
    String id;
    Semaphore pedido;
    Semaphore llegada;

    public Taxista(String id, Semaphore p, Semaphore l) {
        this.id = id;
        this.pedido = p;
        this.llegada = l;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("El taxista " + id + " está esperando un pedido.");
                pedido.acquire();
                System.out.println("El taxista " + id + " ha recibido un pedido y está llegando al pasajero.");
                // Simular el tiempo que tarda en llegar al pasajero
                Thread.sleep(1000);
                System.out.println("El taxista " + id + " ha llegado al pasajero y libera el permiso.");
                llegada.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
} */

public class Taxista implements Runnable {
    private final Semaphore pedido;   // espera pasajeros
    private final Semaphore llegada;  // avisa llegada

    public Taxista(Semaphore pedido, Semaphore llegada) {
        this.pedido = pedido;
        this.llegada = llegada;
    }

    @Override
    public void run() {
        while (true) {
            try {
                
                System.out.println("Taxista: durmiendo...");
                pedido.acquire(); // se bloquea hasta que haya pasajero
                System.out.println("Taxista: ¡Suba! Conduciendo...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            finally {
                llegada.release(); // avisa al pasajero
                System.out.println("Taxista: Llegamos a destino.");
                
            }
        }
    }
}


