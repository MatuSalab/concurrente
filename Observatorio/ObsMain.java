package Observatorio;

public class ObsMain {
    public static void main(String[] args) {
        SalaObsMoni sala = new SalaObsMoni();

        /*
         * for (int i = 1; i <= 100; i++) {
         * new Thread(new VisitanteObs(i, sala, false)).start();
         * }
         * new Thread(new Mantenimiento(1, sala)).start();
         * new Thread(new Mantenimiento(2, sala)).start();
         * new Thread(new VisitanteObs(13, sala, true)).start();
         * new Thread(new Investigador(1, sala)).start();
         */

        for (int i = 1; i <= 10; i++) {
            boolean silla = (i % 5 == 0); // cada 5º usa silla
            new Thread(new VisitanteObs(i, sala, silla), "Vis-" + i).start();
        }

        // Un investigador
        new Thread(new Investigador(1, sala), "Inv-1").start();

        // Personal de mantenimiento (2 personas que pueden entrar a la vez)
        new Thread(new Mantenimiento(1, sala), "Mto-1").start();
        new Thread(new Mantenimiento(2, sala), "Mto-2").start();

        /*
         * ejercicio babuinos. aviones es simil(tiempo en tierra indeterminado)
         * casos a tener en cuenta: de donde vienen y cuantos, poner no multiplos de 5.
         * Variables esperandoI y esperandoD (contadores con mutex)/ var prioridad=(izq,
         * der, neutro)
         * 
         * 
         */
        

    }
}
