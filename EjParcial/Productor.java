package EjParcial;

public class Productor {
    //Productor -> Agarra canciones de la pila que le corresponde (segun el tipo de productor que sea) y las coloca en el segundo buffer, 
    //que hace de "album"
    private final int tipo;                   // su categoría fija 1 al 4
    private final BufferContadores b1;
    private final BufferAlbum b2;

    public Productor(int tipo, BufferContadores b1, BufferAlbum b2) {
        this.tipo = tipo; this.b1 = b1; this.b2 = b2;
    }

    public void run() {
        try {
            while (true) {
                b1.quitar(tipo);
                b2.poner(tipo);
            }
        } catch (InterruptedException ignored) {}
    }
}
