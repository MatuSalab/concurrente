import java.util.concurrent.Semaphore;

public class Libro {
    boolean escrito, fin;
    Semaphore mutexLeer, mutexEscribir, mutex1, mutex2;
    int nLectores, nEscritores;

    public Libro(Semaphore l, Semaphore e, Semaphore m1, Semaphore m2) {
        escrito=false; fin=false;
        mutexLeer = l;
        mutexEscribir = e;
        mutex1 = m1;
        mutex2 = m2;

    }

    public boolean finalizado(){
        return fin;
    }

    public void empezarLeer(){
        try {
            mutexLeer.acquire();
            mutex1.acquire();
            nLectores++;
            if (nLectores == 1) {
                mutexEscribir.acquire();
            }

            mutex1.release();
            mutexLeer.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void terminarLeer() {
        try {
            mutex1.acquire();
            nLectores--;
            if (nLectores == 0) {
                mutexEscribir.release();
            }

            mutex1.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void empezarEscribir() {
        try {

            mutex2.acquire();
            nEscritores++;

            if (nEscritores == 1) {
                this.escrito = true;
                mutexLeer.acquire();
            }

            mutex2.release();
            mutexEscribir.acquire();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void terminarEscribir() {
        try {
            mutexEscribir.release();
            mutex2.acquire();
            nEscritores--;
            if (nEscritores == 0) {
                mutexLeer.release();
            }
            mutex2.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public boolean hayEscrito() {
        return this.escrito;
    }

}
