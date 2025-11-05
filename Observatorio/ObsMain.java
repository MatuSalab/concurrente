package Observatorio;

public class ObsMain {
    public static void main(String[] args) {
        SalaObs sala = new SalaObs();

        /* */
        for (int i = 1; i <= 100; i++) {
                new Thread(new VisitanteObs(i, sala, false)).start();
        }
        new Thread(new Mantenimiento(1, sala)).start();
        new Thread(new Mantenimiento(2, sala)).start();
        new Thread(new VisitanteObs(13, sala, true)).start();
        new Thread(new Investigador(1, sala)).start();

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
