public class Escritor implements Runnable {
    int id;
    Libro libro;

    public Escritor(int i, Libro l) {
        this.id = i;
        this.libro = l;
    }

    public void escribir() {
        try {
            System.out.println("El escritor " + id + " esta escribiendo");
            Thread.sleep(2000);
            System.out.println("El escritor " + id + " termino de escribir");

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void run() {
        // while (!libro.finalizado()) {
        libro.empezarEscribir();
        escribir();
        libro.terminarEscribir();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        // }
    }
}
