package Babuinos;
import java.util.concurrent.*;

public class Cuerda {
    int limite=5, contadorCuerda=0, esperandoD=0, esperandoI=0;
    Semaphore cupo = new Semaphore(5, true);
    Semaphore mutex = new Semaphore(1, true);
    int sentido = 0, ladoAux = 0; // 0 ninguno, 1 izq-der, 2 der-izq
    Semaphore colaI = new Semaphore(0, true);
    Semaphore colaD = new Semaphore(0, true);
    int contadorIzqDer=0, contadorDerIzq=0;
    int lote = 0;
    boolean pendientes = false;

    public Cuerda(){}

    public void subir(int lado){
        boolean bandera = true;
        try {
            while (bandera) {
                mutex.acquire(); // Adquiero la cuerda (para uno de los lados)
                if(sentido==0 && (esperandoD==0 && esperandoI==0)){
                    sentido = lado; // Defino el sentido a seguir para el resto de monos
                }
                // si coincide el lado pasado por el mono o este es nulo, y hay cupos de la
                // cuerda disponibles
                if ((sentido == lado || sentido == 0) && cupo.availablePermits() > 0) {
                    cupo.acquire(); // adquiero un cupo dentro de la cuerda y sumo al contador
                    contadorCuerda++;
                    // sentido=lado; //por si no se seteo el lado en las anteriores?
                    // bandera para salir del while. Mono cruza una unica vez
                    bandera = false;
                    mutex.release();

                } else{
                    // acumulo esperas segun el lado en que se encuentre el mono
                    if(lado==1){esperandoI++;}
                    else{esperandoD++;}
                    Semaphore cola;
                    // encolo segun lado, mantiene fairness?
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
        } //
    }

    public void bajar(int lado){
        try {
            mutex.acquire();
            contadorCuerda--; // libero contador de la cuerda
            cupo.release();

            if (lado == 1) {
                contadorIzqDer++;
            } // llevo cuenta de los cruces para la alternancia mas adelante
            else{contadorDerIzq++;}

            if (contadorCuerda == 0) { // cuerda vacia
                int nuevoLado = definirPaso(); // se define la direccion a seguir segun una logica

                sentido = nuevoLado;

                abrir(nuevoLado); // con este nuevo lado, se libera la cuerda

            } else { // si la cuerda no esta vacia
                // si vengo de izq a der y quedan monos esperando en la izquierda y hay lugar
                if (sentido == 1 && cupo.availablePermits() > 0) {
                    abrir(2);
                } else if (sentido == 2 && cupo.availablePermits() > 0) {
                    abrir(1);
                }
            }
            mutex.release();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public int definirPaso() {
        int p = sentido;

        if (sentido == 0 || (sentido == 2 && ((contadorDerIzq % 5 == 0) || pendientes))) {
            p = 1; // Paso izq-der
            pendientes = false;
        } else if ((sentido == 1 && ((contadorIzqDer % 5 == 0) || pendientes))) { // cada 5 monos cambio de cuerda
            p = 2; // paso der-izq
            pendientes = false;
        } else {
            p = 0;
        }
        return p;
    }

    public void abrir(int lado) {
        if (lado != 0) {
            int libres = cupo.availablePermits();
            if (libres > 0) {
                if (lado == 1) {
                    int n = Math.min(libres, esperandoI);
                    if (n > 0) {
                        pendientes = true;
                        colaI.release(n);
                        esperandoI -= n;

                    }
                } else {
                    int n = Math.min(esperandoD, libres);
                    if (n > 0) {
                        pendientes = true;
                        colaD.release(n);
                        esperandoD -= n;

                    }
                }
            }
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
