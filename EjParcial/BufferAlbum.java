package EjParcial;

import java.util.concurrent.Semaphore;

public class BufferAlbum {      
    //BufferAlbum -> Hace de "mesita" donde se ubica el album. El productor pone canciones y según el tipo se le agrega una duración. 
    //Si se pasa de la duración, se le resta esa duración agregada y se despierta al despachador             
    int duracionMax;
    int duracionActual = 0;    
    //ambos funcionarán como binarios
    Semaphore mutex= new Semaphore(1);                
    Semaphore albumLleno= new Semaphore(0);

    public BufferAlbum(int duracion) {this.duracionMax = duracion;}

    // agrega la duracion del tema, si llega a max, cierra el album
    public void poner(int tipo) throws InterruptedException {
        int aux=0;
        mutex.acquire();
        switch (tipo) {
            // Cada tipo de tema tiene una duracion en particular. Podria cambiarse a double
            case 1: aux=1;
                break;
            case 2: aux=3;
                break;
            case 3: aux=2;
                break;
            case 4: aux=4;
                break;
        }
        duracionActual+=aux;
        while(duracionActual > duracionMax) {
            duracionActual-=aux;   // cierra un album
            albumLleno.release();   // hay 1 album listo
        }
        mutex.release();
    }

    public void quitarAlbum() throws InterruptedException {
        mutex.acquire();
        albumLleno.acquire();       // espera una caja completa
        duracionActual=0;
        mutex.release();
        
    }
}
