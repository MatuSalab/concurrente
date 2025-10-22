import java.util.concurrent.Semaphore;

public class Estudiante implements Runnable{
    Cafetera cafetera; String id;
    Semaphore mutex; 
    Semaphore semPedido;
    Semaphore semAviso;

    public Estudiante(Cafetera cafetera, String id, Semaphore pedido, Semaphore aviso, Semaphore mutex) {
        this.cafetera = cafetera; this.id=id; this.semPedido=pedido; this.semAviso=aviso; this.mutex=mutex;
    }

    public void run() {
        System.out.println(id + " quiere usar la cafetera");
        try {                               // captura InterruptedException
            mutex.acquire();                 // si falla aquí, no se libera después
            try {                            // sección crítica protegida
                cafetera.usar(id);
                semPedido.release();
                semAviso.acquire();          // puede interrumpirse
            } finally {
                mutex.release();             // se libera SOLO si se adquirió
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
