public class HiloLetra implements Runnable {
    private final Secuenciador seq;
    private final int id;
    private final char letra;

    public HiloLetra(Secuenciador s, int id, char letra) { this.seq=s; this.id=id; this.letra=letra; }

    public void run() {
        try {
            while (seq.imprimirSiEsMiTurno(id, letra)) { /* sigo hasta terminar */ }
        } catch (InterruptedException ignored) {}
    }
}
