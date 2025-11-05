package Babuinos;
import java.util.concurrent.*;

public class Cuerda {
    int limite=5, contadorCuerda=0, esperandoD=0, esperandoI=0;
    Semaphore cupo= new Semaphore(5);
    Semaphore mutex= new Semaphore(1);
    int sentido=0; //0 ninguno, 1 izq-der, 2 der-izq
    Semaphore colaI= new Semaphore(0);
    Semaphore colaD= new Semaphore(0);
    int contadorIzqDer=0, contadorDerIzq=0;

    public Cuerda(){}

    public void subir(int lado){
        try {
            while (true) {
                mutex.acquire();
                if(sentido==0 && (esperandoD==0 && esperandoI==0)){
                    sentido=lado;
                }

                boolean turno = (sentido==lado || sentido==0);
                boolean hayEspacio = cupo.availablePermits()>0;

                if(turno && hayEspacio){
                    cupo.acquire();
                    contadorCuerda++;
                    sentido=lado;
                    mutex.release();
                } else{
                    if(lado==1){esperandoI++;}
                    else{esperandoD++;}
                    Semaphore cola;
                    if(lado==1){
                        cola= colaI;
                    } else{
                        cola=colaD;
                    }
                    mutex.release();
                    cola.acquire();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void bajar(int lado){
        try {
            mutex.acquire();
            contadorCuerda--;

            if(lado==1){contadorIzqDer++;}
            else{contadorDerIzq++;}

            if(contadorCuerda==0){
                int nuevoLado= definirPaso();
                sentido=nuevoLado;

                //

                if(nuevoLado==1 && esperandoI>0){
                    colaI.release(esperandoI); esperandoI=0;
                } else if(nuevoLado==2 && esperandoD>0){
                    colaD.release(nuevoLado); esperandoD=0;
                }
                
            } else{
                if(sentido==1 && esperandoI>0 && cupo.availablePermits()>0){
                    
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    /* 
    
    
    
    
    
    
    
    
    productor consumidor (prod hornos, cons empaquet)
    Caja(c)(pesomax), Horno(tipo), Mostrador(c)(frente, cola)
    Empaquetador(), MesaCaja(caja), Brazo()
    lock.acquire para checkeo de peso
     * if(>pesoMax){SemaphoreBrazo.acquire}
     * 
     * 
     * locks: 2 separados, cada uno con sus dos condition(consumirx2, producirx2)
     * monitores: 2, dos recursos compartidos (caja, mostrador)
     * ciclo de vida: se produce un pastel, se pone en
     * la ult pos de la cola, esta se va consumiendo.
     * Una vez que el prod llega al empaquetador, el
     * empaquetador checkea el peso, si entra en la
     * caja que se esta llenando, se coloca en la 
     * misma, si no, se notifica al brazo que retire
     * la caja llena y ponga una nueva. Por ultimo, 
     * se coloca el pastel
     */
}
