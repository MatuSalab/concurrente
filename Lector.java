
public class Lector implements Runnable{
    int id;
    Libro libro;

    public Lector(int i, Libro l){
        this.id = i;
        this.libro = l;
    }

    public void leer(){
        try {
            System.out.println("El lector "+id+" esta leyendo");
            Thread.sleep(1000);
            System.out.println("El lector " + id + " dejo de leer");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void run(){
        while (true) {
            if(libro.hayEscrito()){
                libro.empezarLeer();
                leer();
                libro.terminarLeer();
            }
        }
    }

    
}
