import java.util.concurrent.Semaphore;

public class Lector implements Runnable{
    int id; Libro libro; boolean leyendo;

    public Lector(int i, Libro l){
        this.id=i; this.libro=l; this.leyendo=false;
    }

    public void leer(){
        try {
            System.out.println("El lector "+id+" esta leyendo");
            Thread.sleep(1000);
            System.out.println("El lector "+id+" dejo de leer");
            cambiarEstado();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public boolean estado(){
        return this.leyendo;
    }

    public void cambiarEstado(){
        this.leyendo=true;
    }

    public void run(){
        while(!estado()){
            if(libro.hayEscrito()){
                libro.empezarLeer();
                leer();
                libro.terminarLeer();
            }
        }
    }

    
}
