import java.util.concurrent.Semaphore;
public class Imagen implements Runnable {
    String id; EsperaEditor e;

    public Imagen (int id, EsperaEditor e){
        this.id="Imágen "+id; this.e=e;
    }

    public void run(){
        System.out.println(this.id+" intenta usar el editor");
        e.poner(this);
        System.out.println(this.id+" puesto en espera");
    }
}
