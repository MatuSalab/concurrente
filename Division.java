import java.util.concurrent.Semaphore;
public class Division implements Runnable {
    /*String [] mensajes = new String[2];
    String id;
    MonitorArmada monitor; 

    public Division(int i, MonitorArmada monitor, String m1, String m2) {
        this.id = "División " + i;
        this.monitor = monitor;
        mensajes[0] = m1;
        mensajes[1] = m2;
    }

    public void run(){
        
            monitor.mostrarMensaje(id, mensajes[0]);
            monitor.mostrarMensaje(id, mensajes[1]);
        
    }
            
    private final int id;                  // 1..N
    private final int nDivisiones;         // N
    private final MonitorArmada pantalla;

    public Division(int id, int nDivisiones, MonitorArmada pantalla) {
        this.id = id;
        this.nDivisiones = nDivisiones;
        this.pantalla = pantalla;
    }

    @Override
    public void run() {
        // Cada división tiene las oraciones: id  y  (2*N + 1) - id
        int primera  = id;
        int segunda  = (2 * nDivisiones + 1) - id;

        // Pueden llegar en cualquier momento (simulación opcional)
        // try { Thread.sleep(ThreadLocalRandom.current().nextInt(10, 80)); } catch (InterruptedException ignored) {}

        try {
            pantalla.publicar(id, primera);
            Thread.sleep(1000);
            pantalla.publicar(id, segunda);
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
    */
    private final int id;                  // 1..N
    private final int nDivisiones;         // N
    private final Cadena pantalla;

    public Division(int id, int nDivisiones, Cadena pantalla) {
        this.id = id;
        this.nDivisiones = nDivisiones;
        this.pantalla = pantalla;
    }

    @Override
    public void run() {
        int primera  = id;
        int segunda  = (2 * nDivisiones + 1) - id;
        pantalla.imprimirPaso(id, primera);
        pantalla.imprimirPaso(id, segunda); 
    }
}
