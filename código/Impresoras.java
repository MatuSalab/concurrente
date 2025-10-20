import java.util.concurrent.Semaphore;
public class Impresoras {
    private final Semaphore[] imp;          // binario por impresora
    private final Semaphore libres;         // cuántas impresoras libres

    public Impresoras(int n) {
        this.imp = new Semaphore[n];
        for (int i = 0; i < n; i++) imp[i] = new Semaphore(1, true); // binario, justo
        this.libres = new Semaphore(n, true);                        // contable, justo
    }

    /** Reserva una impresora y devuelve su índice [0..n-1] */
    public int tomarImpresora() throws InterruptedException {
        // Bloquea si no hay ninguna libre
        libres.acquire();

        // Debe existir al menos una impresora cuyo binario esté disponible
        for (int i = 0; i < imp.length; i++) {
            if (imp[i].tryAcquire()) {
                return i; // tomamos esta impresora
            }
        }

        // En teoría no debería pasar (libres garantizó que había al menos una)
        // Si ocurre por una carrera improbable, devolvemos el permiso y reintentamos.
        libres.release();
        throw new IllegalStateException("Inconsistencia: no se encontró impresora libre");
    }

    /** Libera la impresora 'id' */
    public void liberarImpresora(int id) {
        imp[id].release();   // libera el binario de esa impresora
        libres.release();    // aumenta el conteo de impresoras libres
    }
}
