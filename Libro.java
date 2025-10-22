import java.util.concurrent.Semaphore;

public class Libro {
    boolean escrito, fin;
    Semaphore mutexLeer, mutexEscribir;
    int nLectores, nEscritores;

    public Libro(int l, int e){
        this.nLectores=l; this.nEscritores=e;
        escrito=false; fin=false;
        mutexLeer= new Semaphore(0);
        mutexEscribir= new Semaphore(1);
    }

    public boolean finalizado(){
        return fin;
    }

    public void empezarLeer(){
        try {
            mutexLeer.acquire();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


}
