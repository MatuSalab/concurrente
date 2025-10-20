public class Pizarra {
    boolean ocupada;

    public Pizarra(){
        ocupada=false;
    }

    public synchronized boolean tomar() {
        boolean temp;
        if (ocupada) temp=false;
        else{
            ocupada = true;
            temp=true;
        }
        return temp;
    }

    /** Libera la pizarra. */
    public synchronized void liberar() {
        ocupada = false;
    }
}
