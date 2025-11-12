package Observatorio;

public class SalaObsMoni {
    // Estado compartido
    private int contador = 0;
    private int limite = 10;

    // Silla de ruedas
    private boolean sillaEspera = false;
    private boolean sillaRecorriendo = false;

    // Mantenimiento
    private int contadorMantenimiento = 0;
    private boolean mantenimientoEspera = false;
    private boolean mantenimientoEnCurso = false;

    // Investigador
    private boolean investigadorEspera = false;
    private boolean investigadorEnCurso = false;

    // --- utilidades opcionales
    public synchronized void limitar()     { this.limite = 5; }
    public synchronized void quitarLimite(){ this.limite = 10; }

    // ================= Visitantes comunes =================
    public synchronized void ingresar(VisitanteObs vis) throws InterruptedException {
        while (contador == limite
                || sillaEspera
                || mantenimientoEnCurso
                || mantenimientoEspera
                || investigadorEspera
                || investigadorEnCurso) {
            wait();
        }
        contador++;
        vis.visitanteIngresado();
    }

    public synchronized void salir() {
        contador--;
        // Preferencias se imponen vía predicados en los while de cada actor
        notifyAll();
    }

    // ================= Visitante en silla de ruedas =================
    public synchronized void ingresarSilla(VisitanteObs vis) throws InterruptedException {
        // Anuncia prioridad y baja temporalmente el límite
        this.limite = 5;
        this.sillaEspera = true;

        while (contador >= 5
                || mantenimientoEnCurso || mantenimientoEspera
                || investigadorEnCurso || investigadorEspera) {
            wait();
        }

        contador++;
        vis.visitanteIngresado();

        this.sillaEspera = false;
        this.sillaRecorriendo = true;

        notifyAll(); // habilita a otros a reevaluar
    }

    public synchronized void salirSilla() {
        sillaRecorriendo = false;
        limite = 10;
        contador--;
        notifyAll();
    }

    // ================= Mantenimiento =================
    public synchronized void iniciarMantenimiento() throws InterruptedException {
        mantenimientoEspera = true;
        // Solo si sala vacía y sin investigación/pedidos del investigador
        while (contador > 0 || investigadorEspera || investigadorEnCurso) {
            wait();
        }
        mantenimientoEspera = false;
        mantenimientoEnCurso = true;
        contadorMantenimiento++;
    }

    public synchronized void finalizarMantenimiento() {
        contadorMantenimiento--;
        if (contadorMantenimiento == 0) {
            mantenimientoEnCurso = false;
            notifyAll();
        }
    }

    // ================= Investigador =================
    public synchronized void iniciarInvestigacion() throws InterruptedException {
        investigadorEspera = true;

        while (contador > 0
                || sillaRecorriendo
                || mantenimientoEnCurso) {
            wait();
        }

        investigadorEspera = false;
        investigadorEnCurso = true;
    }

    public synchronized void salirInvestigacion() {
        investigadorEnCurso = false;
        notifyAll();
    }
}

