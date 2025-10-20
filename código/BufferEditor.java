import java.util.concurrent.Semaphore;

public class BufferEditor {
    Imagen[] imagenes;
    int cabeza=0, cola=0, contador=0;

    Semaphore mutex= new Semaphore(1,true);
    Semaphore hayEspacio= new Semaphore(1, true);
    Semaphore estaVacio= new Semaphore(0, true);

    public BufferEditor(int n){
        this.imagenes= new Imagen[n];
    }

    public void poner(Imagen img){
        try {
            hayEspacio.acquire();

            mutex.acquire();
            imagenes[cola]=img;
            cola++; contador++;

            if(contador<imagenes.length){
                hayEspacio.release();
            }
            if (contador==1){
                hayEspacio.release();
            }

            mutex.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public Imagen tomar(){
        Imagen aux= new ;
        try {
            estaVacio.acquire();

            mutex.acquire();
            aux= imagenes[cabeza];
            imagenes[cabeza]=null; cabeza++; contador--;

            if(contador>0){
                estaVacio.release();
            }
            if(contador==imagenes.length-1){
                hayEspacio.release();
            }
            mutex.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return aux;
    }
}
