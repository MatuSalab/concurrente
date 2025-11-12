package EjParcial;

public class Despachador {
    //Despachador -> vacía el álbum. Debería crear una nueva instancia imagino? o con solo vaciar la mesa anda?
    private final BufferAlbum b2;

    public Despachador(BufferAlbum b2) {this.b2 = b2; }

    public void run() {
        try {
            while (true) {
                
                b2.quitarAlbum(); 
                
            }
        } catch (InterruptedException ignored) {}
    }
}
