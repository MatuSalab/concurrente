package EjParcial;

import java.util.concurrent.Semaphore;

public class BufferContadores {
    //BufferContadores -> 4 contadores segun cada tipo (arrays de tipo, para saber cuántod hay de cada tipo)
    int n = 4;                  // tipos 1 al 4
    int[] cont = new int[n];
    Semaphore[] mutex;          // exclusión por tipo
    Semaphore[] items;          // cuántos ítems disponibles por tipo

    public BufferContadores() {
        mutex = new Semaphore[n];
        items = new Semaphore[n];
        for (int i = 1; i <= n; i++) {
            mutex[i] = new Semaphore(1, true);
            items[i] = new Semaphore(0, true);
        }
    }

    // tipo ∈ {1,2,3,4}
    public void poner(int tipo) throws InterruptedException {
        mutex[tipo].acquire();
        cont[tipo]++;                // suma al contador
        mutex[tipo].release();
        items[tipo].release();       // anuncia que hay un ítem más
    }

    public void quitar(int tipo) throws InterruptedException {
        
        items[tipo].acquire();       
        mutex[tipo].acquire();
        cont[tipo]--;                // consume uno
        mutex[tipo].release();
    }
}
