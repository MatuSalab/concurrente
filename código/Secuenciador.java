public class Secuenciador {
    private final char[] letras = {'A','B','C'};
    private final int[] cuota;          // p.ej. {1,2,3} -> ABBCCC
    private int turno = 0;              // índice en letras
    private int restantes = 0;          // cuánto falta en el turno actual
    private int ciclosRestantes;        // cuántas veces repetir el patrón
    private boolean terminado = false;

    public Secuenciador(int[] cuotaPorCiclo, int ciclos) {
        this.cuota = cuotaPorCiclo.clone();
        this.ciclosRestantes = ciclos;
        this.restantes = cuota[turno];
    }

    public synchronized boolean imprimirSiEsMiTurno(int id, char letra) throws InterruptedException {
        while (!terminado && turno != id) wait();
        if (terminado) return false;

        // es mi turno
        System.out.print(letra);
        restantes--;

        // si agoté mi cuota, paso el turno
        if (restantes == 0) {
            turno = (turno + 1) % letras.length;
            restantes = cuota[turno];

            // si volvemos a A, terminó un ciclo
            if (turno == 0) {
                ciclosRestantes--;
                if (ciclosRestantes == 0) {
                    terminado = true;
                }
            }
        }
        notifyAll();
        return !terminado;
    }
}
