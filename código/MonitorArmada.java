public class MonitorArmada {

    private int next = 1;          // próxima oración a publicar
    private final int ultima;      // total de oraciones (= 2*N)

    public MonitorArmada(int totalOraciones) {
        this.ultima = totalOraciones;
    }

    // Publica la oración 'num' cuando sea el turno exacto
    public synchronized void publicar(int idDivision, int num) {
        while (num != next) {               
            try { wait(); } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        // --- sección crítica: imprimir en orden ---
        System.out.printf("Division %d:"+" Oracion %d%n", idDivision, num);
        next++;
        if (next <= ultima) notifyAll();    // puede haber alguien esperando el siguiente
    }
}
