package EjParcial;

public class Generador {
    //Generador -> canciones (en realidad, va tirando numeros del 1 al 4 y los pone en el BufferContadores segun la pila correspondiente)
    private final BufferContadores b1;
    public Generador(BufferContadores b1) { this.b1 = b1; }

    private int producirTipo() throws InterruptedException {
        Thread.sleep(10);
        return 1 + (int)(Math.random() * 4); // tira numero del 1 al 4. Simula género
    }

    //canciones se van ubicando en BufferContadores donde les corresponde
    public void run() {
        try {
            while (true) {
                b1.poner(producirTipo());
            }
        } catch (InterruptedException ignored) {}
    }
}
